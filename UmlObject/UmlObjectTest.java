package UmlObject;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UmlObjectTest {

    ArrayList<String>  testArray = new ArrayList<>(Arrays.asList("''", "Comment"));
    UmlObject testUml = new UmlObject(testArray, "outer-comment");


    @Test
    void parseObject() {

    }

    @Test
    void parseClass() {
    }

    @Test
    void parseComment() {
        Map<String, Object> test = new HashMap<>();
        test.put("comment", "Comment");
        assertEquals(testUml.parseComment("''Comment"), test);
    }

    @Test
    void parseAttribute() {
    }

    @Test
    void parseMethod() {

    }

    @Test
    void parseConstructor() {
    }
}