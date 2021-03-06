package edu.vub.ideAT.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import edu.vub.ideAT.ATIcons;

import java.util.Iterator;

/**
 * Created by flo on 15/12/2016.
 */
public class CommentAction extends AnAction {

    public CommentAction(){
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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String highlighted = document.getText(new TextRange(start,end));
                String[] lines = highlighted.split("\n");
                String replacement = "";
                for(int i = 0;i < lines.length;i++){
                    String nextLine = lines[i];
                    if(i > 0){
                        replacement = replacement.concat("\n" + "//" + nextLine);
                    }
                    else{
                        replacement = replacement.concat("//" + nextLine);
                    }

                }
                document.replaceString(start, end, replacement);
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
        selectionModel.removeSelection();
    }

    @Override
    public void update(final AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible((project != null && editor != null && editor.getSelectionModel().hasSelection()));
    }
}
