import java.io.*;

public class ListOfOptions {
    String filename;
    BufferedReader input;
    public ListOfOptions(String filename, BufferedReader input) {
        this.filename = filename;
        this.input = input;
    }
    public void OptionList()throws IOException {
        while (true){
            File file = new File(filename);
            System.out.print("*** LIST OF OPTIONS ***\n" +
                    "\n 1.VIEW the file data" +
                    "\n 2.ADD data to the file" +
                    "\n 3.SEARCH in the file" +
                    "\n 4.RENAME the file" +
                    "\n 5.PATH of the file" +
                    "\n 6.***OPEN ANOTHER FILE***" +
                    "\n 7.***CREATE FILE***" +
                    "\n 8.EXIT :( !!! \n");

            int option = Integer.parseInt(input.readLine().trim()); //**** IMPORTANT : INPUT TAKES NUMBERS AS ASKII CODE
            //SO INCASE TO ACCEPT THE USER OPTION AS A NUMBER, IT SHOULD CONVERT TO INTEGER ******

            switch (option) {
                case 1:
                    //show the whole data in file
                    BufferedReader br1 = new BufferedReader(new FileReader(filename));
                    String totaldata;
                    while ((totaldata = br1.readLine()) != null) {
                        System.out.println("data EXIST in file " + filename + " : \n " + totaldata);
                    }
                    br1.close();
                    break;

                case 2:
                    //add data to the file
                    BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
                    System.out.println("please write whatever you want to add to the file : \n");
                    String data = input.readLine();
                    bw.write(data); //append data to the file
                    bw.close();
                    break;

                case 3:
                    //search method
                    BufferedReader brsearch = new BufferedReader(new FileReader(filename));
                    System.out.println("please enter the word you are searching for : \n");
                    String word = input.readLine();
                    String line;
                    boolean found = false;

                    if (word != null) {
                        while ((line=brsearch.readLine()) != null) {
                            if (line.contains(word)) {
                                System.out.println("word *" + word + "* found successfully" +
                                        " IN file : *" + filename + "*");
                                found = true;
                                break;
                            }
                        }if (!found) {
                            System.out.println("word *" + word + "* not found");
                        }
                    } else {
                        System.out.println("wrong input !!!!");
                    }
                    brsearch.close();
                    break;

                case 4:
                    //rename the file
                    //BufferedReader brRename = new BufferedReader(new FileReader(filename)); //UNNECESSARY
                    System.out.println("please enter the Name you want to set : \n");
                    String rename = input.readLine();
                    File oldfile = new File(filename);
                    File newfile = new File(rename);
                    if (oldfile.renameTo(newfile)) {
                        System.out.println("file RENAMED successfully");
                        filename = rename;
                    } else {
                        System.out.println("FAILED to rename!!!!!");
                    }
                    //brRename.close();
                    break;

                case 5:
                    //show the file path
                    File getpath = new File(filename);
                    String path = getpath.getAbsolutePath();
                    System.out.println("PATH of the file *" + filename + "* is : " + path);
                    break;

                case 6:
                    //open another file
                    System.out.println("enter the name of the FILE : \n");
                    String filename2 = input.readLine();
                    File newfile2 = new File(filename2);
                    if (newfile2.exists()) {
                        BufferedReader brfile2 = new BufferedReader(new FileReader(filename2));
                        System.out.println("the file EXIST and IS READ SUCCESSFULLY");
                        int numOfCharacters=0;
                        int totallines = 0, spaces = 0;
                        String lines;
                        /*while ((numOfCharacters = brfile2.read()) != -1) { //WHILE ITS NOT REACHED TO THE END OF THE FILE, COUNTS CHARACTERS
                            total++;
                            if (Character.isWhitespace((char) numOfCharacters)) {
                                spaces++;
                            }
                            //THIS DOESNT COUNT LINES
                        }*/
                        while ((lines=brfile2.readLine()) != null) { //reads line by line *****
                            numOfCharacters += lines.length();
                            spaces+=lines.replaceAll("[^ ]","").length();
                            totallines++; //.... **** this one can read lines ****....
                        }//this program is written by mahan_mizani

                        System.out.println("the total number of characters is : " + numOfCharacters +
                                "\n and total no. of spaces : " + spaces+"\n and total number of lines : " + totallines);
                        //create object from optionlist class
                        System.out.println("do you want to countiue ? (yes/no) ");
                        String contiue = input.readLine().trim().toLowerCase();
                        if (contiue.equals("yes")) {
                            ListOfOptions showlist = new ListOfOptions(filename2,input);
                            showlist.OptionList();
                        } else {
                            System.out.println("thanks for using our program :) ");
                        }
                        brfile2.close();
                    }
                    else {
                        System.out.println("file not found!!!!!!");
                    }

                    break;

                case 7:

                    System.out.println("please enter the name of the FILE to create : \n");
                    String filenameCreate = input.readLine();
                    File newfileCreate = new File(filenameCreate);
                    if (newfileCreate.exists()) {
                        System.out.println("File *" + filenameCreate + "* already exists! Choose another name.");
                    } else {
                        BufferedWriter bwCreate = new BufferedWriter(new FileWriter(filenameCreate));
                        System.out.println("File *" + filenameCreate + "* CREATED successfully! 🎉");
                        bwCreate.close();
                    }
                    break;

                case 8:
                    System.out.println("Exiting the program...");
                    System.exit(0);  //EXIT the method

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 8.");

            }
            System.out.println("do you want to continue ? (yes/no) ");
            String contiinue = input.readLine().trim().toLowerCase();
            if (contiinue.equals("no")) {
                break;
            }
        }

    }
}
