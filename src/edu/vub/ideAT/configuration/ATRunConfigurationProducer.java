package edu.vub.ideAT.configuration;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import edu.vub.ideAT.ATFile;
import edu.vub.ideAT.ATFileType;

/**
 * Created by flo on 29/08/16.
 */
public class ATRunConfigurationProducer extends RunConfigurationProducer<ATRunConfiguration> {

    public ATRunConfigurationProducer() {
        super(ATRunConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(ATRunConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        Location location = context.getLocation();
        if (location == null) {
            return false;
        }

        PsiElement psiElement = location.getPsiElement();
        if (!psiElement.isValid()) {
            return false;
        }

        PsiFile psiFile = psiElement.getContainingFile();
        if (psiFile == null || !(psiFile.getVirtualFile().getExtension().equals(ATFileType.EXTENSION))) {
            return false;
        }

        VirtualFile file = location.getVirtualFile();
        if (file == null) {
            return false;
        }

        sourceElement.set(psiFile);

        configuration.setName(location.getVirtualFile().getPresentableName());
        configuration.setScriptName(file.getName());

        if (file.getParent() != null) {
            configuration.setScriptPath(file.getPath());
        }

        Project project = context.getProject();
        if(project != null){
            configuration.setScriptProject(project);
        }

        configuration.setScriptATLibPath(ATConfigDefaults.getDefaultATLibPath());
        configuration.setScriptATCommandLineArgs(ATConfigDefaults.getDefaultATCommandLineArgs());

        return true;
    }

    @Override
    public boolean isConfigurationFromContext(ATRunConfiguration configuration, ConfigurationContext context) {
        Location location = context.getLocation();
        if (location == null) {
            return false;
        }

        VirtualFile file = location.getVirtualFile();

        return file != null && FileUtil.pathsEqual(file.getPath(), configuration.getScriptName());
    }

}
