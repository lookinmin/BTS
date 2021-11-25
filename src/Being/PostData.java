package Being;

import java.util.Base64;

public class PostData {
    private String date;
    private String id;
    private String text;
    private byte[] pic;
    private Integer like;

    PostData(String date, String id, String text, String pic, Integer like){
        this.date = date;
        this.id = id;
        this.text = text;
        this.pic = Base64.getDecoder().decode(pic);
        this.like = like;
    }

    public Integer getLike() {
        return like;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public byte[] getPic() {
        return pic;
    }

    public String getText() {
        return text;
    }
}