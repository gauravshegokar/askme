package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends Model implements Jsonable {
    public static final Finder<Long, Tag> find = new Finder<>(Tag.class);
    @Id
    @GeneratedValue
    private int tagId;
    private String tagName;
    @ManyToMany
    private List<Post> posts;
    @ManyToMany
    @JoinTable(name = "interest_followers")
    private List<UserProfile> followers;

    /**
     * Search tags by keywords
     *
     * @param keywords
     * @return
     */
    public static List<Tag> searchByKeywords(List<String> keywords) {
        // @see https://stackoverflow.com/a/7566973
        String where = "tag_name REGEXP '" + String.join("|", keywords) + "'";

        return find.query().where(where).findList();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts.add(post);
    }

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

    /**
     * Convert an instance into JsonNode.
     *
     * @return
     */
    @Override
    public JsonNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("tagId", this.tagId)
                .put("tagName", this.tagName);

        return json;
    }
}
