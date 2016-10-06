package edu.vub.ideAT.project;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by flo on 29/08/16.
 */
public class ATModuleType extends ModuleType<ATModuleWizardStep> {
    private static final String ID = "AT_MODULE_TYPE";

    public ATModuleType(){
        super(ID);
    }

    public static ATModuleType getInstance() {
        return (ATModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public ATModuleWizardStep createModuleBuilder() {
        return new ATModuleWizardStep();
    }

    @NotNull
    @Override
    public String getName() {
        return "AmbientTalk Module Type";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "AmbientTalk Module Type";
    }

    @Override
    public Icon getBigIcon() {
        return IconLoader.getIcon("/edu/vub/ideAT/ui/atIcon.png");
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return AllIcons.General.Information;
    }

    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ATModuleWizardStep moduleBuilder, @NotNull ModulesProvider modulesProvider) {
        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
    }
}
