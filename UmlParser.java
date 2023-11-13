import LanguageObjects.JavaObject;
import LanguageObjects.LanguageObject;
import LanguageObjects.PythonObject;
import UmlObject.UmlObject;

import java.util.ArrayList;
import java.util.List;

public class UmlParser {
    String languageChoice;
    String [] lineArray;
    UmlObject[] umlObjectArray ;
    LanguageObject[] languageObjectArray;

    public UmlParser(String inputUml, String languageChoice){
        this.languageChoice = languageChoice;

        //TODO
    }

    /* Convert input to an array of line strings */
    public String[] parseInput(String inputUml) {
        return inputUml.split("\n");
    }

    //TODO
    public UmlObject[] parseLines(String [] lineArray){
        //categorize each line, could be class, categorize behavior
        return null;
    }

    //TODO
    public LanguageObject[] parseObject(UmlObject[] umlObjectArray, String languageChoice){
        return null;
    }
    //TODO Based on Language

    public Object[] decodeLanguageObject(UmlObject[] umlObjectArray, String languageChoice){
        List<LanguageObject> languageObjectList = new ArrayList<>();
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
