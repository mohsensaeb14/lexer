import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Lexer lex = new Lexer("Let's look");
        lex.reader = new BufferedReader(new FileReader(new File(lex.filelocation)));
        int i= 0;

        while (!lex.end) {
            System.out.print(lex.NextSym());
            i++;
        }

        lex.reader.close();
//        System.out.print('\r');
        System.out.println(i);
    }

}


class Lexer {
    public String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
    public BufferedReader reader = null;
    public boolean end = false;  //to check for the end of file
    public char ch;

    String str;
    public Lexer(String str) {this.str = str;}



    char NextSym() throws IOException {

        int c= reader.read();  //int will become -1 when it'll reach the end of file
        ch = (char)c;

//        if (ch == '\n')  //for future line and column count
//        if (ch == '\t')
//        if (ch == '\r')

        if (c == -1){ //check for the EOF and mark it
            end = true;
            ch = '@';
        }
        SkipWhiteSpaces();
        return ch;
    }

    void SkipWhiteSpaces() throws IOException { //called when ve have recognized a token
        if (ch == '\r') NextSym();
    }

    void getToken()//will return the Token
    {
        /*
        /a lot of if-else things/
         */
    }

}
