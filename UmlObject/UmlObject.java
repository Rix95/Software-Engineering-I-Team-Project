package UmlObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO Need to detect comments, class name, variables, methods, return type, access modifier, constructor, etc..
public class UmlObject {

    String objectType;
    ArrayList<String> umlObjectList;
    ArrayList<String> localClassStringArrayList;
    public UmlObject(ArrayList<String> classStringArrayList, String type){

        this.localClassStringArrayList = classStringArrayList;

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

    public Map<String, Object> parseComment (String comment) {
        String[] commentWithoutBacklash = comment.split("\\\\");
        Map<String, Object> localComment = new HashMap<>();
        localComment.put("comment", commentWithoutBacklash[1]);

        return localComment;


    }


    public Map<String, Object> parseClass(String line){
        //check if its a comment
        //if not it should be a method or attribute
        //split by :
        //TODO constructor (wont be implemented in second sprint)


        }




        return localArrayList;
    }



    return umlObjectList
}
