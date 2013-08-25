import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ne0
 * Date: 7/24/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Parse {
    private static final int NUMBER_OF_PUBLICATIONS = 3;
    private BufferedReader htmlCode;
    private ScholarArticle scholarArticle;
    private ArrayList<String> regExs;

    Parse(String url, ScholarArticle scholarArticle){


        regExs = new ArrayList<String>();

        //Author's Name (0)
        regExs.add(
         "<span id=\"cit-name-display\" class=\"" +
                 "cit-in-place-nohover\">([^<]+)");

        //Number of All Citations (1)
        regExs.add(
                "<td class=\"cit-borderleft cit-data\">([^<]+)");

        //Number of i10-index after 2008 (2)
        regExs.add(
                "<td class=\"cit-borderleft cit-data\">([^<]+)" +
                        "</td></tr></table>"
        );

        //The title of publications (3)
        regExs.add(
                "<tr class=\"cit-table item\"><td id=\"col-title\"" +
                        "><a href=\"/citations\\?view_op=view_citation" +
                        "([^>]+)[>]([^<]+)"
        );

        //Cited-by numbers (4)
        regExs.add(
                "<td id=\"col-citedby\"><a class=\"cit-dark-link\" " +
                        "href=\"http://scholar\\.google[^>]+>([^<]+)</a>"

        );

        //CoAuthors
        regExs.add(
                "<a class=\"cit-dark-link\" href=\"/citations\\?user=" +
                        "[^>]+>([^<]+)</a><br>"
        );

        this.scholarArticle = scholarArticle;
        get(url);
        populateScholarArticle();
    }

    public void get(String url){
       try{
        URL targetURL = new URL(url);
           URLConnection connection = targetURL.openConnection();
            htmlCode = new BufferedReader(new InputStreamReader(
                   connection.getInputStream()));
       } catch (Exception e){
           System.out.println("URL Error.");
       }
}

    public void populateScholarArticle(){
        String lineOfHTML;
        try{
            while((lineOfHTML = htmlCode.readLine()) != null){
                checkForMatches(lineOfHTML);
            }
        }   catch (Exception e){
             System.out.println("Trouble reading HTML.");
        }
    }

    public void checkForMatches(String lineOfHTML){

        //Author's name
        Pattern p0 = Pattern.compile(regExs.get(0));
        Matcher m0 = p0.matcher(lineOfHTML);
        String name ="";
        while(m0.find()){
           Author author = new Author(m0.group(1));
            scholarArticle.setAuthor(author);
        }

        //Number of all citations
        Pattern p1 = Pattern.compile(regExs.get(1));
        Matcher m1 = p1.matcher(lineOfHTML);
        int index = 0;
        while(m1.find() && index == 0){
            scholarArticle.setNumOfallCitations(m1.group(1));
            index++;
        }

        //Number of i10-index after 2008
        Pattern p2 = Pattern.compile(regExs.get(2));
        Matcher m2 = p2.matcher(lineOfHTML);
        while(m2.find()){
            scholarArticle.setI10Index(m2.group(1));
        }

        //The title of the first three publications
        index = 0;
        Pattern p3 = Pattern.compile(regExs.get(3));
        Matcher m3 = p3.matcher(lineOfHTML);
        while(m3.find() && index < NUMBER_OF_PUBLICATIONS){
            scholarArticle.addPublicationTitle(m3.group(2));
            index++;
        }

        //The total number of citations of the first five papers
        index = 0;
        Pattern p4 = Pattern.compile(regExs.get(4));
        Matcher m4 = p4.matcher(lineOfHTML);
        while(m4.find() && index < 5){
            scholarArticle.addToTopFiveCitationTotal
                    (Integer.parseInt(m4.group(1)));
            index++;
        }

        //Number of coauthors according to the right side bar
        Pattern p5 = Pattern.compile(regExs.get(5));
        Matcher m5 = p5.matcher(lineOfHTML);
        while(m5.find()){
            CoAuthor coAuthor = new CoAuthor(m5.group(1));
            scholarArticle.addCoAuthor(coAuthor);
        }
    }
}// end of Parse