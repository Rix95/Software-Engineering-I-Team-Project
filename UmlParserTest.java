import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UmlParserTest {

    @Test
    void parseLines() {
        UmlParser testLines = new UmlParser("Line 1\nLine 2\nLine 3\nLine 4");
        assertArrayEquals(new String[]{"Line 1", "Line 2", "Line 3", "Line 4"}, testLines.lineArray);

    }
}