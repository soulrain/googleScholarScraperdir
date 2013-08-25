import java.io.*;

/**
 * MyParser instantiates ScholarArticle and Output; it reads in at most
 * two parameters from the command line using the first as input file and
 * the second as an optional source file.
 * Created with IntelliJ IDEA.
 * User: Jeffrey Johnston
 * Date: 7/22/13
 * Time: 8:45 PM
 */
public class MyParser {
    public static void main(String[] args){
        ScholarArticle scholarArticle;
        Output printOutput = new Output();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileName ="";

        //At least one command line argument (source file) needed
        if (args.length == 0){
            System.out.println("Text file with urls required.");
        }else if (args.length == 1 || args.length == 2){

            //If two command line arguments present, change standard
            //print stream
            if(args.length == 2){
                baos = new ByteArrayOutputStream();
                System.setOut(new PrintStream(baos));
                fileName = args[1];
            }

            try{
                BufferedReader br = new BufferedReader(new
                        FileReader(args[0]));
                String urlTextLine;

                //Read in all urls from the source text file
                while((urlTextLine = br.readLine()) != null){
                    scholarArticle = new ScholarArticle();
                    Parse parse = new Parse(urlTextLine,scholarArticle);
                    printOutput.add(scholarArticle);
                    scholarArticle.print();
                }
                br.close();
                printOutput.print();

                //Create output text file if second argument is present
                if(args.length == 2){
                    File outFile = new File(fileName);
                  FileOutputStream out = new FileOutputStream(outFile);
                    baos.writeTo(out);
                }
            }catch(Exception e){
                System.out.println("Specified file: "+args[0]+" cannot be " +
                        "found check name and/or path.");
            }
        } else {
            System.out.println("Too many arguments given.");
        }
    } // end of main
} // end of MyParser
