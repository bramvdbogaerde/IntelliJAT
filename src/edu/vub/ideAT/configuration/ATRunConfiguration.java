package edu.vub.ideAT.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.intellij.openapi.util.WriteExternalException;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import edu.vub.ideAT.runner.ATRunnerState;
import org.jdom.Element;

/**
 * Created by flo on 26/08/16.
 */
public class ATRunConfiguration extends LocatableConfigurationBase {
    private String scriptName;
    private String scriptPath;
    private Project scriptProject;
    private String scriptATLibPath;
    private String scriptATJarPath;
    private String scriptATInitPath;
    private String scriptATHomePath;
    private String scriptATCommandLineArgs;

    protected ATRunConfiguration(Project project, ConfigurationFactory factory, String name){
        super(project,factory,name);
        scriptProject = project;
        scriptATLibPath = ATConfigDefaults.getDefaultATLibPath();
        scriptATJarPath = ATConfigDefaults.getDefaultATHomePath();
        scriptATCommandLineArgs = ATConfigDefaults.getDefaultATCommandLineArgs();
        scriptATInitPath = ATConfigDefaults.generateATInitPath(scriptATLibPath);
        scriptATHomePath = scriptATJarPath;
    }

    public String getScriptName(){return scriptName;}

    public void setScriptName(String name){scriptName = name;}

    public void setScriptPath(String path){scriptPath = path;}

    public String getScriptPath(){return scriptPath;}

    public void setScriptProject(Project project){scriptProject = project;}

    public Project getScriptProject(){return scriptProject;}

    public String getScriptATLibPath(){return scriptATLibPath;}

    public void setScriptATLibPath(String ATLibPath){scriptATLibPath = ATLibPath;}

    public String getScriptATJarPath(){return scriptATJarPath;}

    public void setScriptATJarPath(@NotNull String ATJarPath){scriptATJarPath = ATJarPath;}

    public String getScriptATInitPath(){return scriptATInitPath;}

    public void setScriptATInitPath(String path){scriptATInitPath = path;}

    public String getScriptATHomePath(){return scriptATHomePath;}

    public void setScriptATHomePath(String path){scriptATHomePath = path;}

    public String getScriptATCommandLineArgs(){return scriptATCommandLineArgs;}

    public void setScriptATCommandLineArgs(String ATCommandLineArgs){scriptATCommandLineArgs = ATCommandLineArgs;}


    @NotNull
    @Override
    public SettingsEditor<ATRunConfiguration> getConfigurationEditor(){
        return new ATSettingsEditor(this);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        if(scriptATJarPath == null || scriptATLibPath == null){
            throw new RuntimeConfigurationError("Invalid run configuration");
        }
    }

    @Nullable
    @Override
    public CommandLineState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException{
        return new ATRunnerState(this,executionEnvironment);
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        PathMacroManager.getInstance(getProject()).expandPaths(element);
        super.readExternal(element);
        scriptName = JDOMExternalizerUtil.readField(element, "SCRIPT_NAME");
        scriptPath = JDOMExternalizerUtil.readField(element,"SCRIPT_PATH");
        scriptATLibPath = JDOMExternalizerUtil.readField(element,"SCRIPT_ATLIB_PATH");
        scriptATCommandLineArgs = JDOMExternalizerUtil.readField(element,"SCRIPT_AT_ARGS");
        scriptATJarPath = JDOMExternalizerUtil.readField(element,"SCRIPT_JAR");
        scriptATInitPath = JDOMExternalizerUtil.readField(element,"SCRIPT_INIT");
        scriptATHomePath = JDOMExternalizerUtil.readField(element,"SCRIPT_HOME");
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        super.writeExternal(element);
        JDOMExternalizerUtil.writeField(element, "SCRIPT_NAME", scriptName);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_PATH",scriptPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_ATLIB_PATH",scriptATLibPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_AT_ARGS",scriptATCommandLineArgs);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_JAR",scriptATJarPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_INIT",scriptATInitPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_HOME",scriptATHomePath);
        PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
    }


}
