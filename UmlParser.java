import LanguageObjects.JavaObject;
import LanguageObjects.LanguageObject;
import LanguageObjects.PythonObject;
import UmlObject.UmlObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UmlParser {
    String languageChoice;
    String [] lineArray;
    //ArrayList<UmlObject> umlObjectArray Currently not being used, To be deleted;
    LanguageObject[] languageObjectArray;
    HashMap<String, UmlObject> umlObjectHashMap = new HashMap<>();

    public UmlParser(String inputUml, String languageChoice){
        this.languageChoice = languageChoice;

        //TODO
    }

    //TODO Eliminate in between blank lines
    /* Convert input to an array of line strings */
    public String[] parseInput(String inputUml) {
        return inputUml.split("\n");
    }

    //TODO Add constructor Logic as well.
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
            else if (words[0].equals("''")){
                if(isWithinClass){
                    localUmlElementArray.add(line);
                }
                else {
                    localUmlObjectArray.add(new UmlObject(localUmlElementArray,"comment"));
                }
            }


            else if (isWithinClass){
                localUmlElementArray.add(line);
            }
            //End of class, delete localUmlElement, add to class as well
            else if (words[0].equals("}")){
                localUmlObjectArray.add(new UmlObject(localUmlElementArray, "class"));
                localUmlElementArray.clear();
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
