package edu.vub.ideAT.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import edu.vub.ideAT.configuration.ATConfigDefaults;
import edu.vub.ideAT.configuration.ATRunConfiguration;

/**
 * Created by flo on 19/12/2016.
 */
public class ATCommandLine extends GeneralCommandLine {
    ATRunConfiguration config;

    public ATCommandLine(ATRunConfiguration config){
        super();
        this.config = config;
        setExePath();
        setWorkDirectory();
        setAtHome();
        setATInit();
        setATLibs();
        setATJar();
        setATArgs();
        setATScript();
    }

    public void setExePath() {
        setExePath("java");
    }

    public void setWorkDirectory(){
        withWorkDirectory(config.getScriptATJarPath());
    }

    public void setAtHome(){
        withParameters("-DAT_HOME=" + config.getScriptATHomePath());
    }

    public void setATInit(){
        withParameters("-DAT_INIT=" + config.getScriptATInitPath());
    }

    public void setATLibs(){
        String libPath = config.getScriptATLibPath();
        withParameters("-DAT_LIBPATH="+ ATConfigDefaults.generateATLibsPath(libPath));
    }

    public void setATJar(){
        withParameters("-cp","./ambienttalk2.jar:./*","edu.vub.at.IAT");
    }

    public void setATArgs(){
        withParameters(config.getScriptATCommandLineArgs());
    }

    public void setATScript(){withParameters(config.getScriptPath());
    }
}
