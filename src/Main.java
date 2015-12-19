import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
        Lexer lex = new Lexer(filelocation);

        int i= 0;
        while (!lex.end) {
            lex.getToken();
//            System.out.print(lex.NextSym());
             if (lex.getToken() == null) System.out.println("sobaka(?)");
            i++;
        }
//
//        while (lex.getToken() != null) {
//            System.out.println("sobaka(?)");
//            Token token1 = lex.getToken();
//            token1.printToken();
//        }

        lex.reader.close();
        System.out.println(i);

    }

}


class Lexer{
//    public
    public BufferedReader reader = null;
    public boolean end = false;  //to check for the end of file
    public char ch;

    public Lexer(String file) throws FileNotFoundException {this.reader = new BufferedReader(new FileReader(new File(file)));}



    char NextSym() throws IOException {
        int c= reader.read();  //int will become -1 when it'll reach the end of file
        ch = (char)c;

//        if (ch == '\n')  //for future line and column count
//        if (ch == '\t')
//        if (ch == '\r')
        if (c == -1){ //check for the EOF and mark it
            end = true;
            ch = '~';
        }
        return ch;
    }

    void SkipWhiteSpaces() throws IOException { //called when ve have recognized a token
        if (ch == '\r' || ch == '\n') NextSym();
    }

    Token getToken() throws IOException//will return the exact Token
    {
        NextSym();
        SkipWhiteSpaces();
        if (ch == '~'){System.out.println("eeert"); return null; }

        int line = 0, column = 0;
        String type = "", lexeme = "";
        if (Character.isLetter(ch)){
            lexeme = lexeme + Character.toString(ch);
            NextSym();
           while  (Character.isDigit(ch) || Character.isLetter(ch)) { // write if there(?)
                type = "ident";
                lexeme = lexeme + Character.toString(ch);
                NextSym();
            }
        }


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
