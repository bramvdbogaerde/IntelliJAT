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
import org.jdom.Element;

/**
 * Created by flo on 26/08/16.
 */
public class ATRunConfiguration extends LocatableConfigurationBase {
    private String scriptName;
    private String scriptPath;
    private Project scriptProject;
    private String scriptATLibPath;
    private String scriptATCommandLineArgs;

    protected ATRunConfiguration(Project project, ConfigurationFactory factory, String name){
        super(project,factory,name);
        scriptProject = project;
    }

    public String getScriptName(){return scriptName;}

    public void setScriptName(String name){scriptName = name;}

    public void setScriptPath(String path){scriptPath = path;}

    public String getScriptPath(){return scriptPath;}

    public void setScriptProject(Project project){scriptProject = project;}

    public Project getScriptProject(){return scriptProject;}

    public String getScriptATLibPath(){return scriptATLibPath;}

    public void setScriptATLibPath(String ATLibPath){scriptATLibPath = ATLibPath;}

    public String getScriptATCommandLineArgs(){return scriptATCommandLineArgs;}

    public void setScriptATCommandLineArgs(String ATCommandLineArgs){scriptATCommandLineArgs = ATCommandLineArgs;}


    @NotNull
    @Override
    public SettingsEditor<ATRunConfiguration> getConfigurationEditor(){
        return new ATSettingsEditor(scriptProject);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        //TODO
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException{
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
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        super.writeExternal(element);
        JDOMExternalizerUtil.writeField(element, "SCRIPT_NAME", scriptName);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_PATH",scriptPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_ATLIB_PATH",scriptATLibPath);
        JDOMExternalizerUtil.writeField(element,"SCRIPT_AT_ARGS",scriptATCommandLineArgs);
        PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
    }


}
