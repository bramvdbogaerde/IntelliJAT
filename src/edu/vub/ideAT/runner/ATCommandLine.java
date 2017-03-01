package edu.vub.ideAT.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.OrderEnumerator;
import com.intellij.openapi.vfs.VirtualFile;
import edu.vub.ideAT.configuration.ATConfigDefaults;
import edu.vub.ideAT.configuration.ATRunConfiguration;

import java.io.File;
import java.io.FileFilter;
import java.util.Formatter;

/**
 * Created by flo on 19/12/2016.
 */
public class ATCommandLine extends GeneralCommandLine {
    ATRunConfiguration config;

    public ATCommandLine(ATRunConfiguration config){
        super();
        this.config = config;
        setExePath();
        setAtHome();
        setATObjectPath();
        setATInit();
        setATLibs();
        setATJar();
        setATArgs();
        setATScript();
    }

    public void setExePath() {
        setExePath("java");
    }

    public void setAtHome(){
        withParameters("-DAT_HOME=" + config.getScriptATHomePath());
    }

    private String buildDefaultObjectPath(String objectPathLocation)  { 		StringBuffer objectPath = new StringBuffer();
        File dir = new File(objectPathLocation);
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory(); 			} 		};
        File[] children = dir.listFiles(fileFilter);
        if (children == null) {
            // either dir does not exist or is not a directory
            // this should not happen, do nothing
            }
        else
            {
                File currentRoot;
                for (int i = 0; i < children.length; i++) {
                    currentRoot = children[i];
                    if (!currentRoot.getName().startsWith(".") && !currentRoot.getAbsolutePath().equals(dir.getAbsolutePath())) {
                        objectPath.append(currentRoot.getName() + "=" + currentRoot.getAbsolutePath() 	+ File.pathSeparatorChar);
                    }
                }
            } 		return objectPath.toString();
    }

    public void setATObjectPath(){
        withParameters("-DAT_OBJECTPATH=" + buildDefaultObjectPath(config.getScriptATLibPath()) + buildDefaultObjectPath(config.getScriptProject().getBasePath()+"/src"));
    }

    public void setATInit(){
        withParameters("-DAT_INIT=" + config.getScriptATInitPath());
    }

    public void setATLibs(){
        String libPath = config.getScriptATLibPath();
        withParameters("-DAT_ATLIB="+ ATConfigDefaults.generateATLibsPath(libPath));
    }

    public void setATJar(){
        String home = config.getScriptATHomePath();
        String fullClasspath = OrderEnumerator.orderEntries(config.getScriptProject()).recursively().getPathsList().getPathsString();
        withParameters("-classpath", fullClasspath +File.pathSeparatorChar+home+"*","edu.vub.at.IAT");
    }

    public void setATArgs(){
        withParameters(config.getScriptATCommandLineArgs());
    }

    public void setATScript(){
        withParameters(config.getScriptPath());
    }
}
