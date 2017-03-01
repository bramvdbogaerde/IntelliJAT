package edu.vub.ideAT.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import edu.vub.ideAT.actions.RunSnippetFactory;
import edu.vub.ideAT.configuration.ATRunConfiguration;
import org.jetbrains.annotations.NotNull;


import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by flo on 13/12/2016.
 */
public class ATRunnerState extends CommandLineState {
    private ATRunConfiguration config;
    private String toolWindowId = "AmbientTalk";
    private ATProcessHandler myHandler;

    public ATRunnerState(ATRunConfiguration config, ExecutionEnvironment environment) {
        super(environment);
        this.config = config;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        System.out.println("Creating process handler");
        ATCommandLine commandLine = new ATCommandLine(config);
        myHandler = new ATProcessHandler(commandLine.createProcess(),commandLine.getCommandLineString());
        myHandler.startNotify();
        RunSnippetFactory.newRunSnippetAction(config.getName(),myHandler);
        return myHandler;
    }

}
