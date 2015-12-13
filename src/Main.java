import java.io.*;

public class Main {
    public static String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
    public static BufferedReader reader = null;
    public static boolean end = false;  //to check for the end of file


    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new FileReader(new File(filelocation)));

        while (!end) {
            System.out.print(NextSym());
        }

        reader.close();
    }



    static char NextSym() throws IOException {
        int c= reader.read();  //int will become -1 when it'll reach the end of file
        char ch = (char)c;

//        if (ch == '\n')
//        if (ch == '\t')

        if (c == -1){ //check for the EOF and mark it
            end = true;
            ch = '@';
        }

        return ch;
    }

}


class Lexer {
    String str;
    public Lexer(String str) {this.str = str;}

    void getToken()//will return the Token
    {
        /*
        /a lot of if-else things/
         */
    }

}
