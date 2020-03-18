package models;



import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag extends Model {
    @Id
    @GeneratedValue
    private int tagId;

    private String tagName;

    @ManyToMany
    @JoinTable(name="interest_followers")
    private List<UserProfile> followers;

    public List<UserProfile> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserProfile> followers) {
        this.followers = followers;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public static final Finder<Long, Tag> find = new Finder<>(Tag.class);
}
