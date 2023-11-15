import LanguageObjects.JavaObject;
import LanguageObjects.LanguageObject;
import LanguageObjects.PythonObject;
import UmlObject.UmlObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UmlParser {
    String languageChoice;
    String[] lineArray;
    ArrayList<UmlObject> umlObjectArray;
    LanguageObject[] languageObjectArray;

    public UmlParser(String inputUml, String languageChoice){
        this.languageChoice = languageChoice;
        //Parse Input
        lineArray = parseInput(inputUml);
        //Parse Lines
        umlObjectArray = parseLines(lineArray);

        //TODO Parse Uml,Language,Decode etc...
    }

    //TODO Eliminate in between blank lines
    /* Convert input to an array of line strings */
    public String[] parseInput(String inputUml) {
        //Split into lines, then remove empty lines
        String[] test = removeEmptyLines(inputUml.split("\n"));
        //System.out.println(Arrays.toString(test));
        String[] trimmedArray = Arrays.stream(test)
                .map(String::trim)
                .toArray(String[]::new);

        return trimmedArray;
    }

    private static String[] removeEmptyLines(String[] lines) {
        // Use a List to dynamically store non-empty lines
        List<String> nonEmptyLinesList = new ArrayList<>();

        // Iterate through the lines and add non-empty lines to the list
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                nonEmptyLinesList.add(line);
            }
        }

        // Convert the list back to an array
        String[] nonEmptyLinesArray = new String[nonEmptyLinesList.size()];
        nonEmptyLinesList.toArray(nonEmptyLinesArray);

        return nonEmptyLinesArray;
    }

    //TODO Add constructor Logic as well.
    public ArrayList<UmlObject> parseLines(String [] lineArray){
        //categorize each line, could be class, categorize behavior
        ArrayList<String> localUmlElementArray = new ArrayList<>();
        ArrayList<UmlObject> localUmlObjectArray = new ArrayList<>();
        boolean isWithinClass = false;

        for(String line: lineArray){
            String[] words = line.split("\\s+");
            System.out.println(Arrays.toString(words));
            //Found a class
            if(words[0].equals("class")){
                isWithinClass = true;
                localUmlElementArray.add(words[1]);
            }
            //Found a comment
            else if (words[0].startsWith("''")){
                if(isWithinClass){
                    localUmlElementArray.add(line);
                }
                else {
                    localUmlObjectArray.add(new UmlObject(localUmlElementArray,"outer-comment"));
                }
            }
            //End of class, delete localUmlElement, add to class as well
            else if (words[0].equals("}")){
                localUmlObjectArray.add(new UmlObject(localUmlElementArray, "class"));
                localUmlElementArray.clear();
            }
            else if (isWithinClass){
                localUmlElementArray.add(line);
            }

            //If not categorzied, this will ignore intentionally done for
        }




        return localUmlObjectArray;
    }

    //TODO
    public LanguageObject[] parseUmlObject(ArrayList<UmlObject> umlObjectArray, String languageChoice){
        //TODO Based on Language

        return null;
    }

    public Object[] decodeLanguageObject(UmlObject[] umlObjectArray, String languageChoice){
        ArrayList<LanguageObject> languageObjectList = new ArrayList<>();
        for(UmlObject umlObject : umlObjectArray){
            if (languageChoice.toLowerCase().equals("java")){
                languageObjectList.add(new JavaObject(umlObject));
            }
            else if(languageChoice.toLowerCase().equals("python")){
                //languageObjectList.add(new PythonObject(umlObject));
            }
        }

        return null;
    }



}
