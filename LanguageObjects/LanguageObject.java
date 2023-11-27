package LanguageObjects;

import UmlObject.UmlObject;

import java.util.ArrayList;

public abstract class LanguageObject {
    UmlObject umlObject;
    public ArrayList<String> desiredResult;

    public LanguageObject(UmlObject umlObject) {
        this.umlObject = umlObject;

    }

    public ArrayList<String> getDesiredResult(){
        return desiredResult;
    }

}
