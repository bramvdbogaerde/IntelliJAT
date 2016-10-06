package edu.vub.ideAT.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import edu.vub.at.exceptions.InterpreterException;
import edu.vub.ideAT.ui.ATREPL;
import edu.vub.ideAT.REPL.ATREPLFactory;
import edu.vub.ideAT.REPL.ConsoleIATIO;
import edu.vub.ideAT.REPL.IntelliJIAT;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by flo on 29/08/16.
 */
public class ATRunnerState implements RunProfileState {
    private ATRunConfiguration config;

    public ATRunnerState(ATRunConfiguration config, ExecutionEnvironment environment){
        this.config = config;
    }

    @Nullable
    @Override
    public ExecutionResult execute(Executor executor, @NotNull ProgramRunner programRunner) throws ExecutionException {
        ToolWindowManager manager = ToolWindowManager.getInstance(config.getScriptProject());
        ToolWindow toolWindow = manager.getToolWindow(ATREPLFactory.REPL_TOOL_ID);
        if(toolWindow == null){
            toolWindow = manager.registerToolWindow(ATREPLFactory.REPL_TOOL_ID,true, ToolWindowAnchor.BOTTOM);
        }
        toolWindow.setIcon(IconLoader.getIcon("/edu/vub/ideAT/ui/atIcon.png"));
        toolWindow.setTitle(config.getScriptName());
        ATREPL repl = ATREPLFactory.newREPL(config.getScriptName(),config.getScriptPath(),toolWindow,this);
        startATREPL(repl);
        return null;
    }

    public void startATREPL(ATREPL repl){
        ConsoleIATIO console = new ConsoleIATIO(repl);
        System.setProperty("AT_INIT",ATConfigDefaults.generateATInitPath(config.getScriptATLibPath()));
        System.setProperty("AT_LIBPATH",ATConfigDefaults.generateATLibsPath(config.getScriptATLibPath()));
        ArrayList<String> args = new ArrayList<String>();
        Iterator<String> it = getCommandLineArgs().iterator();
        while(it.hasNext()){
            String arg = it.next();
            System.out.println("Adding arg: " + arg);
            args.add(arg);
        }
        args.add(config.getScriptPath());
        String[] argsArray = args.toArray(new String[0]);
        try {
            IntelliJIAT interpreter = new IntelliJIAT(argsArray,console);
            repl.setIntelliJIAT(interpreter);
        } catch (InterpreterException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getCommandLineArgs(){
        return new ArrayList<String>(Arrays.asList(config.getScriptATCommandLineArgs().split("(?=-)")));
    }

}
