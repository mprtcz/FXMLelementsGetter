package com.mprtcz.fxmlRefactorer;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-03-08.
 */
class FileOpener {

    static File openFile(Stage stage){
        FileChooser fileChooser = new FileChooser();

         ///*
        String currentDir = "C:\\Users\\Azet\\Documents\\BitbucketREPO\\test\\FXMLelementsGetter\\src\\main\\resources";
        File file = new File(currentDir);
        fileChooser.setInitialDirectory(file);
        //*/

        fileChooser.setTitle("Choose a fxml file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("FXML Only", "*.fxml", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            System.out.println("Sciezka pliku: " +selectedFile.getAbsolutePath());
        }
        return selectedFile;
    }

    static List<String> getFileContent(File currentFile) throws IOException {
        List<String> lines = new ArrayList<String>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile.getAbsolutePath()));

        String line = bufferedReader.readLine();

        while(line!=null){
            lines.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        return lines;
    }


}
