package edu.vub.ideAT;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

/**
 * Created by flo on 26/08/16.
 */
public class ATFileTypeFactory extends FileTypeFactory {

    public void createFileTypes(FileTypeConsumer fileTypeConsumer){
        fileTypeConsumer.consume(ATFileType.INSTANCE,"at");
    }
}
