package edu.vub.ideAT.REPL;

import edu.vub.at.IAT;
import edu.vub.at.IATIO;
import edu.vub.at.exceptions.InterpreterException;
import edu.vub.at.exceptions.XParseError;
import edu.vub.at.objects.ATObject;

/**
 * Created by flo on 06/09/16.
 */
public class IntelliJIAT extends IAT {

    public IntelliJIAT(String[] args, IATIO iatio) throws InterpreterException {
        super(args, iatio);
    }

    //TODO handle exceptions

    @Override
    protected ATObject handleATException(String script, InterpreterException e) {
        System.out.println("AT Exception caught");
        //DebugCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, DebugCorePlugin.PLUGIN_ID, e.getMessage(), e));
        return super.handleATException(script, e);
    }

    @Override
    protected ATObject handleParseError(String script, XParseError e) {
        System.out.println("Parse error caught");
        //DebugCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, DebugCorePlugin.PLUGIN_ID, e.getMessage(), e));
        return super.handleParseError(script, e);
    }

    @Override
    public void abort(String message, Exception e) {
        //TODO find a way to kill interpreter without it calling System.exit()
        //super.abort(message,e);
        //DebugCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, DebugCorePlugin.PLUGIN_ID, message, e));
    }


}
