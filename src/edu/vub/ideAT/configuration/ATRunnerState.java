package edu.vub.ideAT.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import org.jetbrains.annotations.NotNull;

/**
 * Created by flo on 13/12/2016.
 */
public class ATRunnerState extends CommandLineState {
    private ATRunConfiguration config;

    protected ATRunnerState(ATRunConfiguration config, ExecutionEnvironment environment) {
        super(environment);
        this.config = config;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine commandLine = getCommand();
        return new ATProcessHandler(commandLine.createProcess(), commandLine.getCommandLineString());
    }

    private GeneralCommandLine getCommand() throws ExecutionException {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        setExePath(commandLine);
        setWorkDirectory(commandLine);
        setAtHome(commandLine);
        setATInit(commandLine);
        setATLibs(commandLine);
        setATJar(commandLine);
        setATArgs(commandLine);
        setATScript(commandLine);
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(config.getScriptProject());
        setConsoleBuilder(consoleBuilder);
        return commandLine;
    }

    public void setExePath(GeneralCommandLine commandLine) throws ExecutionException {
        commandLine.setExePath("java");
    }

    public void setWorkDirectory(GeneralCommandLine commandLine){
        commandLine.withWorkDirectory(config.getScriptATJarPath());
    }

    public void setAtHome(GeneralCommandLine commandLine){
        commandLine.withParameters("-DAT_HOME=" + config.getScriptATHomePath());
    }

    public void setATInit(GeneralCommandLine commandLine){
        commandLine.withParameters("-DAT_INIT=" + config.getScriptATInitPath());
    }

    public void setATLibs(GeneralCommandLine commandLine){
        String libPath = config.getScriptATLibPath();
        commandLine.withParameters("-DAT_LIBPATH="+ATConfigDefaults.generateATLibsPath(libPath));
    }

    public void setATJar(GeneralCommandLine commandLine){
        commandLine.withParameters("-cp","./ambienttalk2.jar:./*","edu.vub.at.IAT");
    }

    public void setATArgs(GeneralCommandLine commandLine){
        commandLine.withParameters(config.getScriptATCommandLineArgs());
    }

    public void setATScript(GeneralCommandLine commandLine){
        commandLine.withParameters(config.getScriptPath());
    }

    @NotNull
    public ConsoleView createConsoleView(Executor executor) {
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(config.getScriptProject());
        return consoleBuilder.getConsole();
    }
}
