package LanguageObjects;

import UmlObject.UmlObject;

import java.util.ArrayList;
import java.util.Map;

public class JavaObject extends LanguageObject {
    public ArrayList<String> decodedEntity;

    public JavaObject(UmlObject umlObject) {

        super(umlObject);
        //TODO SKipping for testing purposes
        decodeUmlObject();
        //TODO IN PROGRESS
        decodedEntity = decodeClass();
    }

    public void decodeUmlObject(){
        for (Map<String, Object> stringObjectMap : super.umlObject.parsedObjectArrayList) {
            //System.out.println(stringObjectMap);
        }
    }
    public ArrayList decodeClass(){
        ArrayList<String> classElements = new ArrayList<>();
        //ADD HEADER
        classElements.add(String.valueOf(new StringBuilder("public class" + " " + (String) umlObject.parsedObjectArrayList.get(0).get("className") + " {")));
        //System.out.println(classElements.toString());
        //ADD BODY
        for(int i = 1; i < umlObject.parsedObjectArrayList.size() ; i++){
            //Not the best solution but hopefully it works.. :)
            //System.out.println("testy");
            for (Map.Entry<String, Object> entry : umlObject.parsedObjectArrayList.get(i).entrySet()){
                String key = entry.getKey();
                //System.out.println(key);
                if (key.equals("attribute")){
                    classElements.add(decodeAttribute(umlObject.parsedObjectArrayList.get(i).get(key)));
                }

                //TODO COMMENTS
                else if (key.equals("comment")){
                    classElements.add(decodeComment(umlObject.parsedObjectArrayList.get(i).get(key)));
                }

                else if (key.equals("method")){

                }
            }
        }
        //ADD CLOSURE BRACKET
        classElements.add("}");
        System.out.println(classElements.toString());
        //StringBuilder classBuilt = new StringBuilder();
        return classElements;


    }

    public String decodeComment(Object comment){
        return String.valueOf(new StringBuilder("//" + comment.toString()));
    }

    public String decodeAttribute(Object attributeMap){


        Map<String, Object> attributeNewMap = (Map<String, Object>) attributeMap;
        StringBuilder attributeLine = new StringBuilder();
        //accessModifier
        String accessModifier = (String) attributeNewMap.get("accessModifier");
        if (!accessModifier.equals("default")){
            attributeLine.append(accessModifier);
        }

        //TODO They will be written by default in java so no need to change
        //type
        attributeLine.append(" " + attributeNewMap.get("type"));

        //name
        attributeLine.append(" " +
                 attributeNewMap.get("name")+";");

        attributeMap.toString();
        return String.valueOf(attributeLine);
    }

    public ArrayList<String> getParsedEntity(){
        return decodedEntity;
    }


}
