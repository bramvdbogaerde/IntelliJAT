package edu.vub.ideAT.REPL;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import edu.vub.ideAT.configuration.ATRunnerState;
import edu.vub.ideAT.ui.ATREPL;

/**
 * Created by flo on 30/08/16.
 */
public class ATREPLFactory {
    public static String REPL_TOOL_ID = "AT REPL";

    public static ATREPL newREPL(String fileName, String filePath, ToolWindow toolWindow, ATRunnerState runnerState){
        ContentFactory contentFactory   = ContentFactory.SERVICE.getInstance();
        ATREPL repl                     = new ATREPL(filePath,runnerState,toolWindow);
        Content content                 = contentFactory.createContent(repl.getPanel(),fileName,false);
        repl.setToolWindowContent(content);
        toolWindow.getContentManager().addContent(content);
        return repl;
    }
}

