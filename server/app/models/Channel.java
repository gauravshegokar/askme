package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Channel extends Model {
    public static final Finder<Long, Channel> find = new Finder<>(Channel.class);
    @Id
    @GeneratedValue
    private int channelId;

    private String channelName;

    private UserProfile admin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserProfile> members;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private String description;

    private Date date;

    public static Finder<Long, Channel> getFind() {
        return find;
    }

    public UserProfile getAdmin() {
        return admin;
    }

    public void setAdmin(UserProfile admin) {
        this.admin = admin;
    }

    public List<UserProfile> getMembers() {
        return members;
    }

    public void setMembers(List<UserProfile> members) {
        this.members = members;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPosts(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }

        this.posts.add(post);
    }
}
