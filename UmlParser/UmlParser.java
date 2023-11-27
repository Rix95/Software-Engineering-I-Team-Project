package UmlParser;

import LanguageObjects.JavaObject;
import LanguageObjects.LanguageObject;
import UmlObject.UmlObject;

import java.util.*;

public class UmlParser {
    String languageChoice;
    String[] lineArray;
    ArrayList<UmlObject> umlObjectList;
    ArrayList<LanguageObject> languageObjectList;

    public UmlParser(String inputUml, String languageChoice){
        this.languageChoice = languageChoice;
        //Parse Input
        lineArray = parseInput(inputUml);
        //Parse Lines
        umlObjectList = parseLines(lineArray);
        //Parse umlObjects
        //TODO RIGHT NOW JAVA IS ALWAYYS SELECTED
        languageObjectList = parseUmlObject(umlObjectList, "java");
        System.out.println("///");
        System.out.println(endGame());
        System.out.println("///");

//        for (UmlObject umltest : umlObjectArray){
//            System.out.println(umltest);
//            for(String test2: umltest.localClassStringArrayList){
//                System.out.println(test2 + "sad");
//            }
//        }
        //TODO Parse Uml,Language,Decode etc...
    }

    //TODO Eliminate in between blank lines
    /* Convert input to an array of line strings */
    public String[] parseInput(String inputUml) {
        //Split into lines, then remove empty lines
        String[] test = removeEmptyLines(inputUml.split("\n"));

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

    //TODO Add constructor Logic as well. This might be needed in the umlObject Class
    public ArrayList<UmlObject> parseLines(String [] lineArray){
        //categorize each line, could be class, categorize behavior
        ArrayList<String> localUmlElementArray = new ArrayList<>();
        ArrayList<UmlObject> localUmlObjectArray = new ArrayList<>();
        boolean isWithinClass = false;


        for(String line: lineArray){
            String[] words = line.split("\\s+");

            //Found a class
            if(words[0].equals("class")){
                isWithinClass = true;
                localUmlElementArray.add(words[1]);
            }
            //Found a comment
            else if (words[0].startsWith("''")){
                localUmlElementArray.add(line);
                if(isWithinClass){
                    //doNothingSofar just keep going)
                }
                else {

                    localUmlObjectArray.add(new UmlObject(localUmlElementArray,"outer-comment"));
                    localUmlElementArray.clear();
                }
            }
            //End of class, delete localUmlElement, add to class as well
            else if (words[0].equals("}")){
                localUmlObjectArray.add(new UmlObject(localUmlElementArray, "class"));
                localUmlElementArray.clear();
                isWithinClass = false;
            }
            else if (isWithinClass){

                localUmlElementArray.add(line);
            }

            //If not categorzied, this will ignore intentionally done for
        }




        return localUmlObjectArray;
    }

    //TODO//    public LanguageObject[] parseUmlObject(ArrayList<UmlObject> umlObjectArray, String languageChoice){
    ////        //TODO Based on Language
    ////
    ////        return null;
    ////    }


    public ArrayList<LanguageObject> parseUmlObject(ArrayList<UmlObject> umlObjectArray, String languageChoice){

        ArrayList<LanguageObject> languageObjectList = new ArrayList<>();
        //UmlObject testUml = umlObjectArray.get(0);
//        for (Map<String, Object> stringObjectMap : testUml.parsedObjectArrayList) {
//            String classHeader = String.valueOf(new StringBuilder("public class" + " " + (String) stringObjectMap.get("className") + "{"));
//            System.out.println(classHeader);
//        }


        for(UmlObject umlObject : umlObjectArray){
            if (languageChoice.toLowerCase().equals("java")){

                languageObjectList.add(new JavaObject(umlObject));
            }
            else if(languageChoice.toLowerCase().equals("python")){
                //languageObjectList.add(new PythonObject(umlObject));
            }
        }

        return languageObjectList;
    }

    //TODO Cheesy name wont be in prod.. i promise
    public String endGame() {
        StringBuilder builderTest = new StringBuilder();

        for(LanguageObject languageObject : languageObjectList){
            JavaObject actualLanguage = (JavaObject) languageObject;
            //System.out.println("hello" + actualLanguage.getDesiredResult().toString());
            String temp = String.join("\n", actualLanguage.getDesiredResult());
            builderTest.append(temp + "\n");
        }

        return String.valueOf(builderTest);
    }





}
