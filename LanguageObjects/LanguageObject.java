package LanguageObjects;

import UmlObject.UmlObject;

import java.util.ArrayList;

public abstract class LanguageObject {
    UmlObject umlObject;


    public LanguageObject(UmlObject umlObject) {
        this.umlObject = umlObject;

    }

    public abstract ArrayList<String> getParsedEntity();


}
