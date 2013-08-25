import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jeffrey Johnston
 * Date: 7/23/13
 * Time: 6:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScholarArticle {
    private Author author;
    private ArrayList<CoAuthor> articleCoAuthors;
    private String numOfallCitations;
    private String i10Index;
    private ArrayList<String> publicationTitles;
    private int topFiveCitationTotal;

    ScholarArticle(){
        author = new Author();
        articleCoAuthors = new ArrayList<CoAuthor>();
        publicationTitles = new ArrayList<String>();
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public Author getAuthor(){
        return this.author;
    }

    public void setNumOfallCitations(String numOfallCitations){
        this.numOfallCitations = numOfallCitations;
    }

    public String getNumOfallCitations(){
        return numOfallCitations;
    }
    public void setI10Index(String i10Index){
        this.i10Index = i10Index;
    }

    public void addPublicationTitle(String title){
        publicationTitles.add(title);
    }

    public ArrayList<String> getPublicationTitles(){
        return publicationTitles;
    }

    public void addToTopFiveCitationTotal(int numCitations){
        topFiveCitationTotal+=numCitations;
    }

    public void addCoAuthor(CoAuthor coAuthor){
        articleCoAuthors.add(coAuthor);
    }

    public ArrayList<CoAuthor> getArticleCoAuthors(){
        return articleCoAuthors;
    }

    public String getI10Index(){
        return i10Index;
    }

    public void print(){
        StringBuilder border = new StringBuilder();
        for(int i = 0; i < 43; i++)
            border.append("-");
        System.out.println(border);
        System.out.println("i. Name of Author:");
        System.out.println("\t" + this.getAuthor().getFullName());
        System.out.println("ii. Number of All Citations:");
        System.out.println("\t" + this.getNumOfallCitations());
        System.out.println("iii. Number of i10-index after 2008:");
        System.out.println("\t" + getI10Index());
        System.out.println("iv. Title of the first three publications:");
        System.out.println("\t1-\t" + getPublicationTitles().get(0));
        System.out.println("\t2-\t" + getPublicationTitles().get(1));
        System.out.println("\t3-\t" + getPublicationTitles().get(2));
        System.out.println("v. Total paper citation of the first five " +
                "papers:");
        System.out.println("\t" + topFiveCitationTotal);
        System.out.println("vi. Total Co-Authors:");
        System.out.println("\t" + getArticleCoAuthors().size());

    }

}
