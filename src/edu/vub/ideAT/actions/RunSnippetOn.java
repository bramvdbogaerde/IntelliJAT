package edu.vub.ideAT.actions;

import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Key;
import edu.vub.ideAT.ATIcons;
import edu.vub.ideAT.runner.ATProcessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by flo on 19/12/2016.
 */
public class RunSnippetOn extends AnAction {
    private ATProcessHandler p;

    public RunSnippetOn(String name, ATProcessHandler p){
        super(name,"Run selected at snippet on given REPL instance",ATIcons.ATIcon);
        this. p = p;
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
                try {
                    System.out.println("Process dead ? " + p.isProcessTerminated());
                    p.getProcessInput().write((highlighted + "\n").getBytes(StandardCharsets.UTF_8));
                    p.getProcessInput().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }

    @Override
    public void update(final AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible((project != null && editor != null && editor.getSelectionModel().hasSelection()));
    }
}
