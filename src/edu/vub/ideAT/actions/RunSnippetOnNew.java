package edu.vub.ideAT.actions;

import com.intellij.execution.*;
import com.intellij.execution.configurations.*;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Factory;
import com.intellij.openapi.util.TextRange;
import edu.vub.ideAT.ATIcons;
import edu.vub.ideAT.configuration.ATRunConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by flo on 16/12/2016.
 */
public class RunSnippetOnNew extends AnAction {

    public RunSnippetOnNew(){
        super(ATIcons.ATIcon);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        final String highlighted = document.getText(new TextRange(start,end));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RunManager manager = RunManager.getInstance(project);
                RunnerAndConfigurationSettings selectedConfiguration = manager.getSelectedConfiguration();
                ExecutionUtil.runConfiguration(cloneAndUpdate(selectedConfiguration,selectedConfiguration.getConfiguration(),highlighted),DefaultRunExecutor.getRunExecutorInstance());
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }

    private RunnerAndConfigurationSettings cloneAndUpdate(RunnerAndConfigurationSettings original,RunConfiguration originalConfig,String snippet){
        ATRunConfiguration newConfig = (ATRunConfiguration) originalConfig.clone();
        newConfig.setName("AT Snippet");
        File f = new File("snippet.at");
        try {
            FileWriter writer = new FileWriter(f);
            writer.write(snippet);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        newConfig.setScriptPath(f.getAbsolutePath());
        return new RunnerAndConfigurationSettings() {
            @Nullable
            @Override
            public ConfigurationType getType() {
                return original.getType();
            }

            @Nullable
            @Override
            public ConfigurationFactory getFactory() {
                return original.getFactory();
            }

            @Override
            public boolean isTemplate() {
                return original.isTemplate();
            }

            @Override
            public boolean isTemporary() {
                return original.isTemporary();
            }

            @Override
            public void setTemporary(boolean b) {
                original.setTemporary(b);
            }

            @Override
            public RunConfiguration getConfiguration() {
                return newConfig;
            }

            @Override
            public void setName(String s) {
                original.setName(s);
            }

            @Override
            public String getName() {
                return original.getName();
            }

            @Override
            public String getUniqueID() {
                return original.getUniqueID();
            }

            @Nullable
            @Override
            public RunnerSettings getRunnerSettings(@NotNull ProgramRunner programRunner) {
                return original.getRunnerSettings(programRunner);
            }

            @Nullable
            @Override
            public ConfigurationPerRunnerSettings getConfigurationSettings(@NotNull ProgramRunner programRunner) {
                return original.getConfigurationSettings(programRunner);
            }

            @Override
            public void checkSettings() throws RuntimeConfigurationException {
                original.checkSettings();
            }

            @Override
            public void checkSettings(@Nullable Executor executor) throws RuntimeConfigurationException {
                original.checkSettings(executor);
            }

            @Override
            public boolean canRunOn(@NotNull ExecutionTarget executionTarget) {
                return original.canRunOn(executionTarget);
            }

            @Override
            public Factory<RunnerAndConfigurationSettings> createFactory() {
                return original.createFactory();
            }

            @Override
            public void setEditBeforeRun(boolean b) {
                original.setEditBeforeRun(b);
            }

            @Override
            public boolean isEditBeforeRun() {
                return original.isEditBeforeRun();
            }

            @Override
            public void setActivateToolWindowBeforeRun(boolean b) {
                original.setActivateToolWindowBeforeRun(b);
            }

            @Override
            public boolean isActivateToolWindowBeforeRun() {
                return original.isActivateToolWindowBeforeRun();
            }

            @Override
            public void setSingleton(boolean b) {
                original.setSingleton(b);
            }

            @Override
            public boolean isSingleton() {
                return original.isSingleton();
            }

            @Override
            public void setFolderName(@Nullable String s) {
                original.setFolderName(s);
            }

            @Nullable
            @Override
            public String getFolderName() {
                return original.getFolderName();
            }
        };
    }

    @Override
    public void update(final AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible((project != null && editor != null && editor.getSelectionModel().hasSelection()));
    }
}
