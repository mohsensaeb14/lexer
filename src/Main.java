import java.io.*;
import java.util.*;

public class Main {
    private static List<Character> chars = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        String filelocation = "C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\input.txt";
        BufferedWriter INfile = new BufferedWriter(new FileWriter(filelocation));
        INfile.write("you have made me cry");
        INfile.write(" goddammit!");
        INfile.close();

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(new File(filelocation)));
                int c;
                while ((c = reader.read()) != -1) {
                    chars.add((char) c);
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(chars.toString());
            System.out.println(chars);
            System.out.println(chars.get(0));

        //recognize id



        }

    }


class Lexer { /*принимает входной файл и формирует из него файлы
              ничего не записывает через
            printtoken закидывает токен в выходной поток */
    String str;
    public Lexer(String str) {
        this.str = str;
    }


/*
//функция, которая дает следующий символ
//getchar
отлавливать ошибки с помощью try-cach??
*/


}





//    BufferedReader OUTfile = new BufferedReader (new FileReader("C:\\Users\\Nikita\\Dropbox\\git\\lexer\\files\\output.txt"));
//System.out.println(OUTfile.readLine());
//        OUTfile.close();


