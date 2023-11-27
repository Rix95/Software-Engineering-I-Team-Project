import UmlObject.UmlObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UmlParserTest {
    String testString = "" +
            "''TestyTest\n" +
            "class BattleshipGame {\n" +
            "    -inputboard:ArrayList<ArrayList>\n" +
            "    \n" +
            "    <<constructor>> BattleshipGame()\n" +
            "    +playgame(board:board,player:player)\n" +
            "\n" +
            "}\n" +
            "\n" +
            "class Board {\n" +
            "    \n" +
            "    -virtualboard:ArrayList<ArrayList>\n" +
            "    -ships:ArrayList<ship>\n" +
            "    -traps:ArrayList<trap>\n" +
            "    -potions:ArrayList<potion>\n" +
            "\n" +
            "    <<constructor>> Board(ship:int,trap:int,potion:int)\n" +
            "    +createboard():ArrayList<ArrayList>\n" +
            "    +createships(num:int)\n" +
            "    +createtraps(num:int)\n" +
            "    +createpotions(num:int)\n" +
            "    +isoverlap(temp:potion) : boolean\n" +
            "    +getship():ArrayList<ship>\n" +
            "    +gettrap():ArrayList<trap>\n" +
            "    +getpotion():ArrayList<potion>\n" +
            "    +getvirtualboard():ArrayList<ArrayList>\n" +
            "    +displayboard(board):ArrayList<ArrayList>\n" +
            "    \n" +
            "}\n";
    @Test
    void parseLines() {
        UmlParser testLines = new UmlParser(testString, "java");
        System.out.println(Arrays.toString(testLines.lineArray));
        System.out.println(testLines.umlObjectArray);


        //assertArrayEquals(new String[]{"Line 1", "Line 2", "Line 3", "Line 4"}, testLines.lineArray);

    }
}