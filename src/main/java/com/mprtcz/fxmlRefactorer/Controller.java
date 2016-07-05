package com.mprtcz.fxmlRefactorer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-03-08.
 */
public class Controller {

    public TextField pathTextField;
    public Button openButton;
    public TextArea resultsTextArea;
    public Label label;

    public void openButtonClicked(){
        File file = FileOpener.openFile(new Stage());
        if(file!=null) {
            pathTextField.setText(file.getAbsolutePath());

            List<String> lines = new ArrayList<String>();
            try {
                lines = FileOpener.getFileContent(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            RefactorAgent refactorAgent = new RefactorAgent(lines);
            String controls = refactorAgent.getResultControlsString();
            String methods = refactorAgent.getResultMethodsString();

            String result = controls + "\n\n" +methods;

            resultsTextArea.setText(result);
            label.setText("Generated fields and methods:");
        }
    }
}
