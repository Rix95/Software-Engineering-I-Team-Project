package UmlObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//TODO Need to detect comments, class name, variables, methods, return type, access modifier, constructor, etc..
public class UmlObject {

    public String objectType;
    public ArrayList<String> umlObjectList;
    public ArrayList<String> localClassStringArrayList;
    public UmlObject(ArrayList<String> classStringArrayList, String type){

        this.localClassStringArrayList = classStringArrayList;
        //System.out.println(localClassStringArrayList);
        //This will handle comments outside the class
        if (type.equals("outer-comment")){
            objectType = "outer-comment";
        }

        else if (type.equals("class")) {
            objectType = "class";
        }
    }

    //test = { {comment: comment},{},{},{}}

    public ArrayList<Map<String, Object>> parseObject(String objectType, ArrayList<String> localClassStringArrayList) {
        //Might not be used...
        ArrayList<String> localClassString = new ArrayList<String>(localClassStringArrayList);

        ArrayList<Map<String, Object>> desiredOutput = new ArrayList<>();
        //unique case for outer comments
        if (objectType.equals("outer-comment")) {

            desiredOutput.add(parseComment(localClassString.get(0)));
            ;
        }
        //class general...
        else if (objectType.equals("class")) {
            Map<String, Object> classMap = new HashMap<>();
            classMap.put("className", localClassStringArrayList.get(0));
            for (int i = 1; i < localClassStringArrayList.size(); i++){
                desiredOutput.add(parseClass(localClassStringArrayList.get(i)));
            }
        }

        return desiredOutput;
    }
    public Map<String, Object> parseClass(String line){
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
            System.out.println(Arrays.toString(sections));
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



        return null;


    }
    public Map<String, Object> parseMethod (String[] dividedLine) {


        return null ;
    }

    public String checkModifier(String attributeName) {
        char firstChar = attributeName.charAt(0);
        String modifier;
        if (firstChar == '+'){
    
        }

        else if(firstChar == '-') {
        }
        else if(firstChar == '#') {

        }
        else {
        }

        return
    }



        return null;

    }

    public Map<String, Object> parseConstructor (String line) {
        Map<String, Object> betaConstructor = new HashMap<>();
        betaConstructor.put("Do Nothing", "Nothing");

        return betaConstructor;
    }

    }





