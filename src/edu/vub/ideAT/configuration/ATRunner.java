package edu.vub.ideAT.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by flo on 29/08/16.
 */
public class ATRunner extends DefaultProgramRunner {
    public static final String AT_RUNNER_ID = "ATRunner";

    @NotNull
    @Override
    public String getRunnerId(){return AT_RUNNER_ID;}

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile){
        return DefaultRunExecutor.EXECUTOR_ID.equals(executorId) && profile instanceof ATRunConfiguration;
    }

}