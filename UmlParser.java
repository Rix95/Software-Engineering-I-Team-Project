public class UmlParser {
    String [] lineArray;
    
    public UmlParser(String inputUml){
        
        lineArray = parseLines(inputUml);
    }

    public String[] parseLines(String inputUml) {

        return inputUml.split("\n");
    }

}
