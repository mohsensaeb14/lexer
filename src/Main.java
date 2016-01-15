import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
        Lexer lex = new Lexer(filelocation);

        int i= 0;
        Token token1 = lex.getToken();

        while (token1 != null) {
            token1.printToken();
            token1 = lex.getToken();
//           System.out.println("sobaka(?)");
            i++;
        }

        lex.reader.close();
        System.out.println(i);

    }
}


class Lexer{
    public BufferedReader reader = null;
    public boolean end = false;  //to check for the end of file
    public char ch;
    public int lineCounter= 1, columnCounter;

    public Lexer(String file) throws FileNotFoundException {this.reader = new BufferedReader(new FileReader(new File(file)));}



    char NextSym() throws IOException {
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
        while (ch == '\r' || ch == '\n' || ch == ' ' || ch == '\t') NextSym();
    }

    Token getToken() throws IOException//will return the exact Token
    {
        NextSym();
        SkipWhiteSpaces();
        if (ch == '~'){System.out.println("No other Tokens"); return null; }

        int line = 0, column = 0;
        String type = "", lexeme = "";

        if (Character.isLetter(ch)){
            lexeme = lexeme + Character.toString(ch);
            NextSym();
           while  (Character.isDigit(ch) || Character.isLetter(ch)) { // write if there(?)
                lexeme = lexeme + Character.toString(ch);
               type = "ident";
               if (Arrays.asList(Keywords.reserved).contains(lexeme)) type = "keyword";
                NextSym();
            }

//        int recognition

    }
        if (Character.isDigit(ch)) {
            while (Character.isDigit(ch)){
                lexeme = lexeme + Character.toString(ch);
                NextSym();
            }
            type = "integer";
        }


        line = lineCounter;
        column = columnCounter;
        return new Token(line, column, type, lexeme);
    }

}


class Token{


    int line, column;
    String type, lexeme, value;
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
        //бращаемся к мэпу

    //поиск по ключу,  в качестве ключей - ключевые слова
    // и значеня для них 1
    //нахождение значения в ассоциатиыном массиве.
    // специальная функция, которая проверяет на соответствие ключа

    static String[] reserved = {
        "begin",        "forward",  "do",       "else",     "end",          "for",
        "function",     "if",       "array",    "of",       "program",
        "record",       "then",     "to",       "type",     "var",          "while",
        "break",        "continue", "downto",   "exit",     "repeat",       "until",
        "case",         "with",     "const",    "set"
    };






}