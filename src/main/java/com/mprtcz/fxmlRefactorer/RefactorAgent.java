package com.mprtcz.fxmlRefactorer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-03-08.
 */
public class RefactorAgent {
    List<String> linesContainingFXID = new ArrayList<String>();
    List<String> linesContainingOnAction = new ArrayList<String>();
    private String resultControlsString;
    private String resultMethodsString;



    public RefactorAgent(List<String> lines){
        for(String line : lines){
            if(line.contains("fx:id=")){
                linesContainingFXID.add(line);
            }
            if(line.contains("onAction=")){
                linesContainingOnAction.add(line);
            }
        }
        generateFields();
        generateMethods();
    }

    private void generateFields(){
        StringBuilder output = new StringBuilder();
        for(String line : linesContainingFXID){
            String[] parts = line.split("fx:id=\"");
            if(parts.length > 2){
                System.err.println("Unexpected result");
            } else {
                String fieldType = parts[0].split("\\<")[1];
                System.out.println("fieldType: " +fieldType);
                System.out.println("parts[1]: " +parts[1]);
                String fieldName = parts[1].split("\"")[0].replace(" ", "");
                System.out.println("fieldName: " +fieldName);

                output.append("public ");
                output.append(fieldType);
                output.append(fieldName);
                output.append(";\n");
            }
        }
        resultControlsString = String.valueOf(output);
    }

    private void generateMethods(){
        StringBuilder stringBuilder = new StringBuilder();

        for(String line : linesContainingOnAction){
            String secondHalfLine = line.split("onAction=")[1];
            String methodName = secondHalfLine.split(" ")[0].replace("\"", "").replace("#", "");

            stringBuilder.append("public void ");
            stringBuilder.append(methodName);
            stringBuilder.append("(){\n\n}\n");
        }
        resultMethodsString = String.valueOf(stringBuilder);
    }

    public String getResultControlsString() {
        return resultControlsString;
    }

    public String getResultMethodsString() {
        return resultMethodsString;
    }
}
