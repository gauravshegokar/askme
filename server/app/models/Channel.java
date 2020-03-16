package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel extends Model {

    @Id
    @GeneratedValue
    private int channelId;

    private String channelName;

    private List<Post> posts;


    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
            this.posts.add(post);
        } else {
            this.posts.add(post);
        }
    }

    public static final Finder<Long, Channel> find = new Finder<>(Channel.class);
}
