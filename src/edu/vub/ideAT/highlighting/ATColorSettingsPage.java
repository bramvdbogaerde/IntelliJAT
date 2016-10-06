package edu.vub.ideAT.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import edu.vub.ideAT.ATIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * Created by flo on 03/10/2016.
 */
public class ATColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword",ATHighlighter.KEYWORD),
            new AttributesDescriptor("Constant", ATHighlighter.CONSTANT),
            new AttributesDescriptor("Operator", ATHighlighter.OPERATOR),
    };
    @Nullable
    @Override
    public Icon getIcon() {
        return ATIcons.ATIcon;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ATHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "def x := 5; \n actor:{ //foo \n}";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "AmbientTalk";
    }
}
