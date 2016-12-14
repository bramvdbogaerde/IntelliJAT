package edu.vub.ideAT.configuration;

import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;

/**
 * Created by flo on 13/12/2016.
 */
public class ATProcessHandler extends ColoredProcessHandler {

    public ATProcessHandler(@NotNull Process process, String commandLine) {
        super(process, commandLine);
    }

    @Override
    public void coloredTextAvailable(String text,Key attributes){
        super.coloredTextAvailable(text,attributes);
    }
}
