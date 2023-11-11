import LanguageObjects.LanguageObject;

public class UmlParser {
    String languageChoice;
    String [] lineArray;
    UmlObject[] languageObjectArray;
    public UmlParser(String inputUml, String languageChoice){

        //TODO
    }

    /* Convert input to an array of strings */
    public String[] parseInput(String inputUml) {
        return inputUml.split("\n");
    }

    //TODO
    public UmlObject[] parseLines(String [] lineArray){
        //categorize each line, could be class
        return null;
    }

    //TODO
    public LanguageObject[] parseObject(LanguageObject[] languageObjectArray, String languageChoice){
        return null;
    }



}
