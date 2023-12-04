package UmlObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String[] sections;
            //System.out.println("hey" + line);
            if (line.contains("(")){
               System.out.println(line + "Hey");
                sections = line.split("\\):");
                String addedClosure = String.valueOf(new StringBuilder(sections[0] + ")"));
                sections[0] = addedClosure;
                System.out.println("sections" + Arrays.toString(sections));

            }
            else {
                sections = line.split(":");
            }
            //System.out.println(Arrays.toString(sections));
            char lastCharacter = sections[0].charAt(sections[0].length() -1);
            //System.out.println(lastCharacter + " " + sections);
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
        Map<String, Object> methodMap = new HashMap<>();
        methodMap.put("method", new HashMap<String, String>());
        Map<String, String> methodInnerMap = (Map<String, String>)methodMap.get("method");

//        System.out.println("leftSIde" + dividedLine[0]);
        String nameWithModifierAndParams = dividedLine[0];
        Matcher leftSide = parseLeftSide(nameWithModifierAndParams);
        String methodNameWithModifier = leftSide.group(1);
        String contentInsideParentheses = leftSide.group(2);
        //
        methodMap.put("params", parseParams(contentInsideParentheses));
        String returnType = dividedLine[1];

        //Check modifier add to map
        String accessModifier = checkModifier(methodNameWithModifier);



        methodInnerMap.put("accessModifier", accessModifier);

        if (accessModifier.equals("default")){
            methodInnerMap.put("name", methodNameWithModifier);
        }
        else {
            String nameWithoutModifier = methodNameWithModifier.substring(1);
            methodInnerMap.put("name", nameWithoutModifier);
        }
        methodInnerMap.put("type", returnType);

        return methodMap;
    }

    public Matcher parseLeftSide(String leftSide){

        //String input = "+method(param1:String,param2:double)";

        // Define a regular expression pattern
        String regex = "([\\+\\-#]*[a-zA-Z]+)\\(([^)]+)\\)";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(leftSide);

        // Check if the pattern matches
        if (matcher.matches()) {
            // Extract method name and content inside parentheses
            String methodName = matcher.group(1);
            String contentInsideParentheses = matcher.group(2);

            // Print the results
            System.out.println("Method Name: " + methodName);
            System.out.println("Content Inside Parentheses: " + contentInsideParentheses);
        } else {
            System.out.println("Pattern does not match the input string.");
        }

        return matcher;
    }

    public Map<String, String> parseParams(String params){
        Map<String, String> paramMap = new HashMap<>();
        String[] pairs = params.split(",");

        for (String pair: pairs){
            String[] parts = pair.split(":");
            String attribute = parts[0].trim();
            String type = parts[1].trim();

            paramMap.put(attribute, type);
        }
        return paramMap;
    }

    public String checkModifier(String nameWithModifier) {
        char firstChar = nameWithModifier.charAt(0);

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





