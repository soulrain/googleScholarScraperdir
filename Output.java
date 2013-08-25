import java.util.ArrayList;
import java.util.Collections;


/**
 * Created with IntelliJ IDEA.
 * User: Jeffrey Johnston
 * Date: 7/23/13
 * Time: 6:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Output {
    private ArrayList<String> totalCoAuthors;

    Output(){

        totalCoAuthors = new ArrayList<String>();
    }

    public void print(){
        Collections.sort(totalCoAuthors);
        StringBuilder border = new StringBuilder();
        for(int i = 0; i < 43; i++)
            border.append("-");
        System.out.println(border);
        System.out.println("vii. Co-author list sorted (Total: "+
        totalCoAuthors.size()+"):");
        for( String coAuhtor : totalCoAuthors)
            System.out.println(coAuhtor);
    }

    public void add(ScholarArticle scholarArticle){
        for (CoAuthor coAuthor : scholarArticle.getArticleCoAuthors()){
            if(!totalCoAuthors.contains(coAuthor.getFullName())){
                totalCoAuthors.add(coAuthor.getFullName());
            }
        }
    }
}
