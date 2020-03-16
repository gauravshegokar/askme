package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends Model {

    private UserProfile author;

    private boolean isProfane;

    @Id
    @GeneratedValue
    private int postId;

    private Channel channel;

    private String text;

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(Tag tag) {
        if(this.tags==null){
            tags=new ArrayList<>();
        }
        this.tags.add(tag);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Constraints.Required
    @Column(columnDefinition = "datetime")
    public Timestamp date_created;



    public boolean isProfane() {
        return isProfane;
    }

    public void setProfane(boolean profane) {
        isProfane = profane;
    }



    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public static final Finder<Long, Post> find = new Finder<>(Post.class);


}
