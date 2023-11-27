package UmlObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//TODO Need to detect comments, class name, variables, methods, return type, access modifier, constructor, etc..
public class UmlObject {

    public String objectType;
    public ArrayList<String> localClassStringArrayList;
    public ArrayList<Map<String, Object>> parsedObjectArrayList;
    public UmlObject(ArrayList<String> classStringArrayList, String type){

        this.localClassStringArrayList = classStringArrayList;

        //This will handle comments outside the class
        if (type.equals("outer-comment")){
            objectType = "outer-comment";
        }

        else if (type.equals("class")) {
            objectType = "class";
        }

        parsedObjectArrayList = parseObject(objectType, classStringArrayList);


    }

    //test = { {comment: comment},{},{},{}}

    public ArrayList<Map<String, Object>> parseObject(String objectType, ArrayList<String> localClassStringArrayList) {
        //Might not be used...
        ArrayList<String> localClassString = new ArrayList<String>(localClassStringArrayList);

        ArrayList<Map<String, Object>> umlParsedObjectArrayList = new ArrayList<>();
        //unique case for outer comments
        if (objectType.equals("outer-comment")) {

            umlParsedObjectArrayList.add(parseComment(localClassString.get(0)));
            ;
        }
        //class general...
        else if (objectType.equals("class")) {
            Map<String, Object> classMap = new HashMap<>();
            classMap.put("className", localClassStringArrayList.get(0));
            umlParsedObjectArrayList.add(classMap);
            for (int i = 1; i < localClassStringArrayList.size(); i++){
                umlParsedObjectArrayList.add(parseClassItem(localClassStringArrayList.get(i)));
            }
        }

        return umlParsedObjectArrayList;
    }
    public Map<String, Object> parseClassItem(String line){
        //check if its a comment
        //if not it should be a method or attribute
        //split by :



        //TODO This should be comment
        if (line.startsWith("''")){
            return parseComment(line);
        }
        //TODO constructor (wont be implemented in second sprint)
        else if (line.startsWith("<<")){
            return parseConstructor(line);
        }
        //TODO Attribute & Method
        else {
            String[] sections = line.split(":");
            //System.out.println(Arrays.toString(sections));
            char lastCharacter = sections[0].charAt(sections[0].length() -1);

            //Method

            if (lastCharacter == ')'){
                return parseMethod(sections);
            }
            //Attribute
            else {
                return parseAttribute(sections);
            }
        }
    }

    public Map<String, Object> parseComment (String line) {
        String[] commentWithoutBacklash = line.split("''");
        Map<String, Object> localComment = new HashMap<>();
        localComment.put("comment", commentWithoutBacklash[1]);

        return localComment;


    }

    public Map<String, Object> parseAttribute (String[] dividedLine) {

        //Map<String, Map<String, String>> attributeMap = new HashMap<>();
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("attribute", new HashMap<String, String>());


        String nameWithModifier = dividedLine[0];
        String type = dividedLine[1];


        //Check modifier add to map
        String accessModifier = checkModifier(nameWithModifier);
        Map<String, String> attributeInnerMap = (Map<String, String>)attributeMap.get("attribute");
        attributeInnerMap.put("accessModifier", accessModifier);


        if (accessModifier.equals("default")){
            attributeInnerMap.put("name", nameWithModifier);
        }
        else {
            String nameWithoutModifier = nameWithModifier.substring(1);
            attributeInnerMap.put("name", nameWithoutModifier);

        }
        attributeInnerMap.put("type", type);

        return attributeMap;
    }
    public Map<String, Object> parseMethod (String[] dividedLine) {


        return null ;
    }

    public String checkModifier(String attributeName) {
        char firstChar = attributeName.charAt(0);

        if (firstChar == '+'){
            return "public";
        }

        else if(firstChar == '-') {
            return "private";
        }
        else if(firstChar == '#') {
            return "protected";
        }
        else {
            return "default";
        }
    }

    public Map<String, Object> parseConstructor (String line) {
        Map<String, Object> betaConstructor = new HashMap<>();
        betaConstructor.put("Do Nothing", "Nothing");

        return betaConstructor;
    }

    }





