package UmlObject;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UmlObjectTest {

    ArrayList<String>  testArray = new ArrayList<>(Arrays.asList("''", "Comment"));
    UmlObject testUml = new UmlObject(testArray, "outer-comment");
    ArrayList<String>  testClassArray = new ArrayList<>(Arrays.asList("BattleshipGame", "-inputboard:ArrayList<ArrayList>",
            "<<constructor>> BattleshipGame()"  ));
    UmlObject testClassUml = new UmlObject(testArray, "class");



    @Test
    void parseObject() {

    }

    @Test
    void parseClass() {
        testClassUml.parseObject("class", testClassArray);
    }

    @Test
    void parseComment() {
        Map<String, Object> test = new HashMap<>();
        test.put("comment", "Comment");
        assertEquals(testUml.parseComment("''Comment"), test);
    }

    @Test
    void parseAttribute() {
        String[] testAttributeArray = {"+testAttribute", "int"};
        Map<String, Object> testMap = testClassUml.parseAttribute(testAttributeArray);
        testMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

    }

    @Test
    void parseMethod() {

    }

    @Test
    void parseConstructor() {
    }
}