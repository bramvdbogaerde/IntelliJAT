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

    public static String getDefaultATJarPath(){
        if(ATConfigDefaults.inProduction()){
            String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
            VirtualFile jarRoot = JarFileSystem.getInstance().getJarRootForLocalFile(LocalFileSystem.getInstance().findFileByPath(classPath));
            String jarPath = jarRoot.getPath().replace("!","");
            return jarPath.replace("/ideAT.jar","");
        }
        else{
            return ATConfigDefaults.class.getClassLoader().getResource("ambienttalk2.jar").getPath().replace("ambienttalk2.jar","");
        }
    }

    public static String getDefaultATLibPath(){
        String classPath = PathUtil.getJarPathForClass(ATConfigDefaults.class);
        if(ATConfigDefaults.inProduction()){
            //Plugin is running in production mode, might need to extract at lib from plugin jar
            VirtualFile jarRoot = JarFileSystem.getInstance().getJarRootForLocalFile(LocalFileSystem.getInstance().findFileByPath(classPath));
            String jarPath = jarRoot.getPath().replace("!","");
            String libPath = jarPath.replace("/ideAT.jar","");
            String atLibPath = libPath + "atlib";
            final File jarFile = new File(jarPath);
//            Notifications.Bus.notify(new Notification("AT Test", "Success", "Checking File System",NotificationType.INFORMATION));
            if(Files.notExists(Paths.get(atLibPath))){
                final JarFile jar;
                try {
                    jar = new JarFile(jarFile);
                    final Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextEntry = entries.nextElement();
                        final String name = nextEntry.getName();
                        if (name.startsWith("atlib/") && name.endsWith(".at")) {
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
            //Plugin is running in development mode, simply get atlib from project directory
            return ATConfigDefaults.class.getClassLoader().getResource("/atlib").getPath();
        }
    }

    public static String generateATInitPath(String libPath){
        return libPath + "/at/init/init.at";
    }

    public static String generateATLibsPath(String libPath){
        return "at="+libPath+"/at:"+ "applications="+libPath+"/applications:"+ "bridges="+libPath+"/bridges:"+ "frameworks="+libPath+"/frameworks:"+ "demo="+libPath+"/demo:"+ "test="+libPath+"/test:";
    }

    public static String getDefaultATCommandLineArgs(){
        return "-l all=FATAL";
    }
}