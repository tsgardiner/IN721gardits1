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

    public Question(String englishWord, String noun, String article, String gender) {
        this.englishWord = englishWord;
        this.noun = noun;
        this.article = article;
        this.gender = gender;
    }


    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

}



