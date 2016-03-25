package bit.gardits1.languagetrainer;

/**
 * Created by Tim on 24/03/2016.
 */
public class Question {


    protected String englishWord;
    protected String noun;
    protected String article;
    protected String imageString;

    public Question(String englishWord, String noun, String article, String imageString) {
        this.englishWord = englishWord;
        this.noun = noun;
        this.article = article;
        this.imageString = imageString;
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



