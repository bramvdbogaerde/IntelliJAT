package edu.vub.ideAT.actions;


import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.util.Key;
import edu.vub.ideAT.runner.ATProcessHandler;

/**
 * Created by flo on 19/12/2016.
 */
public class RunSnippetFactory {
    private static Integer idPool  = 0;

    public static void newRunSnippetAction(String name, ATProcessHandler p){
        ActionManager am = ActionManager.getInstance();
        RunSnippetOn action = new RunSnippetOn(name,p);
        am.registerAction(idPool.toString(),action);
        DefaultActionGroup dag = (DefaultActionGroup) am.getAction("RunOnGroup");
        dag.add(action);
        idPool += 1;
        p.addProcessListener(new ProcessListener() {
            @Override
            public void startNotified(ProcessEvent processEvent) {

            }

            @Override
            public void processTerminated(ProcessEvent processEvent) {
                dag.remove(action);
            }

            @Override
            public void processWillTerminate(ProcessEvent processEvent, boolean b) {

            }

            @Override
            public void onTextAvailable(ProcessEvent processEvent, Key key) {

            }
        });
    }
}
