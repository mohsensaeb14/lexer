import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
        Lexer lex = new Lexer(filelocation);

        int i= 0;
        Token token1 = lex.getToken();

        while (token1 != null) {
            token1.printToken();
            token1 = lex.getToken();
            i++;
        }

        lex.reader.close();
        System.out.println("Total: " + i);


//        char [] chars = str.toCharArray();
        System.out.print(Keywords.reserved[1]);
    }
}


class Lexer{
    public BufferedReader reader = null;
    public boolean end = false;  //to check for the end of file
    public char ch;
    public int lineCounter= 1, columnCounter;

    public Lexer(String file) throws IOException {this.reader = new BufferedReader(new FileReader(new File(file)));
    nextSym();}



    char nextSym() throws IOException {
        int c= reader.read();  //int will become -1 when it'll reach the end of file
        ch = (char)c;

        if (ch == '\n') { lineCounter++;
                          columnCounter = 1; }
            else if (ch == '\t') columnCounter += 4 - (columnCounter - 1) % 4;
            else columnCounter++;
         if (c == -1){ //check for the EOF and mark it
            end = true;
            ch = '~';
        }
        return ch;
    }

    void SkipWhiteSpaces() throws IOException { //called when ve have recognized a token
        while (ch == '\r' || ch == '\n' || ch == ' ' || ch == '\t') nextSym();
    }

    Token getToken() throws IOException//returns the exact Token
    {

        SkipWhiteSpaces();
        if (ch == '~') return null;

        String type = "", lexeme = "";


        if (Character.isLetter(ch)) {

            while (Character.isDigit(ch) || Character.isLetter(ch)) { // write if there(?)
                lexeme = lexeme + Character.toString(ch);
                nextSym();
                type = "ident";
                if (Arrays.asList(Keywords.reserved).contains(lexeme)) type = "keyword";
                else if ((Arrays.asList(Keywords.operators).contains(lexeme))) type = "op";  //for first 6 operators recognition
            }
        return new Token (lineCounter, columnCounter, type, lexeme);
        }

//        int recognition
        else if (Character.isDigit(ch)) {
            while (Character.isDigit(ch)){
                lexeme = lexeme + Character.toString(ch);
                nextSym();
            }
            type = "integer";

            //real recognition
            if (ch == '.') {
                lexeme = lexeme + Character.toString(ch);
                nextSym();
                while (Character.isDigit(ch)) {
                    lexeme = lexeme + Character.toString(ch);
                    nextSym();
                }
            type = "real";
            }
            return new Token(lineCounter, columnCounter, type, lexeme);
        }
        //op and sep recognition
        else if ((Arrays.asList(Keywords.operators).contains(Character.toString(ch))) || Arrays.asList(Keywords.separators).contains(Character.toString(ch))) {
            lexeme = lexeme + Character.toString(ch);
            nextSym();
            lexeme = lexeme + Character.toString(ch);
            type = "op";

            if ((Arrays.asList(Keywords.operators).contains(lexeme))){
                nextSym();
            }

            if (Arrays.asList(Keywords.separators).contains(lexeme)) {
                type = "sep";
                return new Token(lineCounter, columnCounter, type, Character.toString(ch)); //without .. case
            }
            return new Token(lineCounter, columnCounter, type, lexeme);
        }



        return new Token(lineCounter, columnCounter, type, lexeme); //shouldn't be there throw exception

    }


}


class Token{


    int line, column;
    String type, lexeme;
    public Token(int line, int column, String type, String lexeme){
        this.line = line;
        this.column = column;
        this.type = type;
        this.lexeme = lexeme;
    }

   void printToken(){
       System.out.println(line+" "+column+" "+type+" "+lexeme);
   }
}

class Keywords{

    static String[] reserved = {
        "begin",        "forward",  "do",       "else",     "end",          "for",
        "function",     "if",       "array",    "of",       "program",
        "record",       "then",     "to",       "type",     "var",          "while",
        "break",        "continue", "downto",   "exit",     "repeat",       "until",
        "case",         "with",     "const",    "set"
    };


    static String[] operators = {
        "and",      "div",      "mod",      "not",       "or",        "xor",      "+",        "-",
        "*",        "/",        "^",        "+=",        "-=",        "*=",       "/=",       "<",
        ">",        "<=",       ">=",       "=",        "<>",        ":=",        "@",        "."
    };

    static String[] separators = {
        "(",        ")",        "[",        "]",        ";",        ",",        ":",        "..",
    };

}