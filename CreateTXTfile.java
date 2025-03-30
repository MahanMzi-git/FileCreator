import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateTXTfile {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter the file NAME : ");
        String filename = null;
        try {
            //IN TRY , IF THE FILE EXIST THE PROGRAM CAN EASILY READ THE FILE AND DO WHATEVER WE WANT.
            filename = br.readLine();
            BufferedReader br2 = new BufferedReader(new FileReader(filename));
            System.out.println("the file EXIST and IS READ SUCCESSFULLY");
            int numOfCharacters=0;
            int total=0, spaces=0;
            String lines;
            int totallines=0;

            /*while((numOfCharacters = br2.read())!= -1){ //WHILE ITS NOT REACHED TO THE END OF THE FILE, COUNTS CHARACTERS
                total++;
                if (Character.isWhitespace((char) numOfCharacters)){
                    spaces++;
                }*/ //NOT ABLE TO READ THE LINES
            while ((lines=br2.readLine()) != null) { //reads line by line *****
                numOfCharacters += lines.length();
                spaces+=lines.replaceAll("[^ ]","").length();
                totallines++; //.... **** this one can read lines ****....
            }//this program is written by mahan_mizani



            System.out.println("the total number of characters is : "+total+"\n and total no. of spaces : "+spaces);
            //create object from optionlist class
            System.out.println("do you want to countiue ? (yes/no) ");
            String contiue = br.readLine().trim().toLowerCase();
            if(contiue.equals("yes")) {
                ListOfOptions showlist = new ListOfOptions(filename,br);
                showlist.OptionList();
            }else {
                System.out.println("thanks for using our program :) ");
            }

        }
        catch (IOException e) {
            //THIS HAPPENS WHEN THE FILE DOESNT EXIST . IN THIS CASE WE WILL FACE AN IOEXCEPTION. AND THEN
            //WE ARE ABLE TO DO EVERYTHING (EX: create new file)
            System.out.println("failed to read the file");
            System.out.println("do you want to see the list of Options? (yes/no) ");
            String answer = br.readLine().trim().toLowerCase();
            if(answer.equals("yes")) {
                ListOfOptions showlist = new ListOfOptions(filename,br);
                showlist.OptionList();
            }
            else {
                System.out.println("thanks for using our program :) ");
            }

        }
    }
}
