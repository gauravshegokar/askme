package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class UserProfile extends Model implements Jsonable {
    public static final Finder<Long, UserProfile> find = new Finder<>(UserProfile.class);
    @Constraints.Required
    @Column(columnDefinition = "datetime", updatable = false)
    public Date dateCreated;
    @Id
    @GeneratedValue
    private int id;
    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;
    private String fname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Post> posts;
    @ManyToMany(mappedBy = "followers")
    private List<Tag> interests = new ArrayList<>();
    private String lname;
    private String accessLevel;

    /**
     * Find user by id
     *
     * @param userId
     * @return
     */
    public static final UserProfile findById(int userId) {
        return find.where().eq("id", userId).findUnique();
    }

    /**
     * A helper method of #findById(int userId)
     *
     * @param userId
     * @return
     */
    public static final UserProfile findById(String userId) {
        return findById(Integer.parseInt(userId));
    }

    /**
     * Get owned channels of a user.
     *
     * @return
     */
    public List<Channel> getOwnChannels() {
        return Channel.getOwnedChannels(this);
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

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", interests=" + interests +
                ", lname='" + lname + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", date_created=" + dateCreated +
                '}';
    }

    public List<Tag> getInterests() {
        return interests;
    }

    public void setInterests(Tag interest) {
        this.interests.add(interest);
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Date();
    }

    @Override
    public void save() {
        dateCreated();
        super.save();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("userId", this.id)
                .put("username", this.username)
                .put("firstName", this.fname)
                .put("lastName", this.lname)
                .put("dateJoined", this.dateCreated.toString());

        return json;
    }
}
