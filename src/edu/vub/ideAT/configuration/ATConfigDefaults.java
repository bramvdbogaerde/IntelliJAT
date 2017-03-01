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
        String rootPath = jarRoot.getPath();
        //For some reason even on windows jar path is with slash instead of backslash + adds extra slash at the end of jar path
        if(ATConfigDefaults.isWindows()){
            rootPath = ATConfigDefaults.convertToWindows(rootPath);
        }
        return rootPath.replace("!","");
    }

    public static String getDefaultATHomePath(){
        if(ATConfigDefaults.inProduction()){
            String jarPath = getDefaultATJarPath();
            return jarPath.replace(File.separator+"ideAT.jar","");
        }
        else{
            String path = ATConfigDefaults.class.getClassLoader().getResource("ambienttalk2.jar").getPath().replace("ambienttalk2.jar","");
            if(isWindows()){
                String converted = convertToWindows(path);
                return converted.substring(1,converted.length());
            }
            else{
                return path;
            }
        }
    }

    public static String getDefaultATLibPath(){
        //String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
        if(ATConfigDefaults.inProduction()){
            //Plugin is running in production mode, might need to extract at lib from plugin jar
            String jarPath = getDefaultATJarPath();
            String libPath = ATConfigDefaults.getDefaultATHomePath();
            String atLibPath;
            if(isWindows()){
                //Windows adds trailing backslach at the end of path
                atLibPath = ATConfigDefaults.getDefaultATHomePath() +  "atlib";
            }
            else{
                atLibPath = ATConfigDefaults.getDefaultATHomePath() + File.separator+ "atlib";
            }
            final File jarFile = new File(jarPath);
            //Notifications.Bus.notify(new Notification("AT Test", "Success", "Jar Path: " + jarPath,NotificationType.INFORMATION));
            if(Files.notExists(Paths.get(atLibPath))){
                final JarFile jar;
                try {
                    jar = new JarFile(jarFile);
                    final Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextEntry = entries.nextElement();
                        String name = nextEntry.getName();
                        if(isWindows()){
                            //Same issue as with jar path
                            name = convertToWindows(name);
                        }
                        if (name.startsWith("atlib"+File.separator)&& name.endsWith(".at")) {
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
            String path = ATConfigDefaults.class.getClassLoader().getResource("/atlib").getPath();
            if(isWindows()){
                String converted = convertToWindows(path);
                return converted.substring(1,converted.length());
            }
            else{
                return path;
            }
        }
    }
    public static boolean isWindows(){
        return System.getProperty("os.name").contains("Windows");
    }

    public static String convertToWindows(String path){
        return path.replace("/","\\");
    }

    public static String generateATInitPath(String libPath){
        return libPath + File.separator+"at"+File.separator+"init"+ File.separator+ "init.at";
    }

    public static String generateATLibsPath(String libPath){
        return "at="+libPath+File.separator+"at"+File.pathSeparatorChar+ "applications="+libPath+File.separator+"applications"+File.pathSeparatorChar+ "bridges="+libPath+File.separator+"bridges"+File.pathSeparatorChar+ "frameworks="+libPath+File.separator+"frameworks"+File.pathSeparatorChar+ "demo="+libPath+File.separator+"demo"+File.pathSeparatorChar+ "test="+libPath+File.separator+"test"+File.pathSeparatorChar;
    }

    public static String getDefaultATCommandLineArgs(){
        return "-l all=FATAL";
    }
}