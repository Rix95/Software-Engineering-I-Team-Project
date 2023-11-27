package UmlObject;

import java.util.ArrayList;
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
        //Map<String, Object> localMap = new HashMap<>();
        ArrayList<Map<String, Object>> desiredOutput = new ArrayList<>();
        //unique case for outer comments
        if (objectType.equals("outer-comment")) {

            desiredOutput.add(parseComment(localClassString.get(0)));
            ;
        }
        //class general...
        else if (objectType.equals("class")) {
            for(String line : localClassStringArrayList){
                desiredOutput.add(parseClass(line));
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
            char lastCharacter = sections[0].charAt(-1);

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

    public Map<String, Object> parseConstructor (String line) {


        return null;
    }

    }





