package bit.gardits1.languagetrainer;

/**
 * Created by Tim on 24/03/2016.
 */
public class Question {


    protected String englishWord;
    protected String noun;
    protected String article;
    protected String gender;
    protected String imageString;

    public Question(String englishWord, String noun, String gender, String article, String imageString) {
        this.englishWord = englishWord;
        this.noun = noun;
        this.article = article;
        this.gender = gender;
        this.imageString = imageString;
    }


    public String getEnglishWord() { return englishWord; }
    public String getNoun() { return noun;   }
    public String getArticle() { return article; }
    public String getImageString() { return imageString; }

}



