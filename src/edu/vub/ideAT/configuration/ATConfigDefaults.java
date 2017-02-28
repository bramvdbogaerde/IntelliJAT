package edu.vub.ideAT.configuration;


import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by flo on 04/10/2016.
 */
public class ATConfigDefaults {

    public static boolean inProduction(){
        String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
        return classPath.endsWith(".jar");
    }

    private static String getDefaultATJarPath(){
        String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
        VirtualFile jarRoot = JarFileSystem.getInstance().getJarRootForLocalFile(LocalFileSystem.getInstance().findFileByPath(classPath));
        return jarRoot.getPath().replace("!","");
    }

    public static String getDefaultATHomePath(){
        if(ATConfigDefaults.inProduction()){
            String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
            VirtualFile jarRoot = JarFileSystem.getInstance().getJarRootForLocalFile(LocalFileSystem.getInstance().findFileByPath(classPath));
            String jarPath = jarRoot.getPath().replace("!","");
            //For some reason even on windows jar path is with slash instead of backslash
            if(ATConfigDefaults.isWindows()){
                jarPath = ATConfigDefaults.convertToWindows(jarPath);
                return jarPath.replace("\\ideAT.jar","");
            }
            else{
                return jarPath.replace("/ideAT.jar","");
            }
        }
        else{
            return ATConfigDefaults.class.getClassLoader().getResource("ambienttalk2.jar").getPath().replace("ambienttalk2.jar","");
        }
    }

    public static String getDefaultATLibPath(){
        //String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
        if(ATConfigDefaults.inProduction()){
            //Plugin is running in production mode, might need to extract at lib from plugin jar
            String jarPath = getDefaultATJarPath();
            String libPath = ATConfigDefaults.getDefaultATHomePath();
            String atLibPath = ATConfigDefaults.getDefaultATHomePath() + File.separator+ "atlib";
            final File jarFile = new File(jarPath);
            //Notifications.Bus.notify(new Notification("AT Test", "Success", "Jar Path: " + libPath,NotificationType.INFORMATION));
            if(Files.notExists(Paths.get(atLibPath))){
                final JarFile jar;
                try {
                    jar = new JarFile(jarFile);
                    final Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextEntry = entries.nextElement();
                        final String name = nextEntry.getName();
                        if (name.startsWith("atlib"+File.separator)&& name.endsWith(".at")) {
                            Notifications.Bus.notify(new Notification("AT Test", "Success", "Adding: " +name ,NotificationType.INFORMATION));
                            InputStream i = jar.getInputStream(nextEntry);
                            BufferedReader buff = new BufferedReader(new InputStreamReader(i, StandardCharsets.UTF_8));
                            ArrayList<String> lines = new ArrayList<String>();
                            String nextLine = buff.readLine();
                            while (nextLine != null) {
                                lines.add(nextLine);
                                nextLine = buff.readLine();
                            }
                            File file = new File(libPath + name);
                            file.getParentFile().mkdirs();
                            FileWriter writer = new FileWriter(file);
                            Iterator<String> it = lines.iterator();
                            while (it.hasNext()) {
                                writer.write(it.next());
                                writer.write("\n");
                            }
                            writer.flush();
                            writer.close();
                        }
                    }
                    jar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return atLibPath;
        }
        else{
            //Notifications.Bus.notify(new Notification("AT Test", "Success", "In Development mode",NotificationType.INFORMATION));
            //Plugin is running in development mode, simply get atlib from project directory
            return ATConfigDefaults.class.getClassLoader().getResource("/atlib").getPath();
        }
    }
    private static boolean isWindows(){
        return System.getProperty("os.name").contains("Windows");
    }

    public static String getPathSeperator(){
        if(isWindows()){
            return ";";
        }
        else{
            return ":";
        }
    }

    public static String convertToWindows(String path){
        return path.replace("/","\\");
    }

    public static String generateATInitPath(String libPath){
        return libPath + File.separator+"at"+File.separator+"init"+ File.separator+ "init.at";
    }

    public static String generateATLibsPath(String libPath){
        String pathSeparator = getPathSeperator();
        return "at="+libPath+File.separator+"at"+pathSeparator+ "applications="+libPath+File.separator+"applications"+pathSeparator+ "bridges="+libPath+File.separator+"bridges"+pathSeparator+ "frameworks="+libPath+File.separator+"frameworks"+pathSeparator+ "demo="+libPath+File.separator+"demo"+pathSeparator+ "test="+libPath+File.separator+"test"+pathSeparator;
    }

    public static String getDefaultATCommandLineArgs(){
        return "-l all=FATAL";
    }
}