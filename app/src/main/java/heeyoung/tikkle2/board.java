package heeyoung.tikkle2;

/**
 * Created by heeyoung on 2017-12-08.
 */

public class board {

    private String title;
    private String content;
    private String image;

    public board(){

    }

    public board(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
