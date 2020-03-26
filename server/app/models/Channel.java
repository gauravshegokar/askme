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
public class Channel extends Model implements Jsonable {
    /**
     * Used to do queries of Channel.
     */
    private static final Finder<Long, Channel> FINDER = new Finder<>(Channel.class);
    @Id
    @GeneratedValue
    private int channelId;

    @Constraints.Required
    private String channelName;

    @Constraints.Required
    private UserProfile channelOwner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserProfile> members = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private String channelDescription;

    @Constraints.Required
    @Column(columnDefinition = "datetime")
    private Date dateCreated;

    /**
     * [Singleton Pattern]
     *
     * @return
     */
    public static Finder<Long, Channel> getFinder() {
        return FINDER;
    }

    /**
     * Find channel by id
     *
     * @param channelId
     * @return
     */
    public static Channel findById(int channelId) {
        return getFinder().byId((long) channelId);
    }

    /**
     * Get the default channel which is loaded when the database is initiated.
     *
     * @return
     */
    public static Channel getDefaultChannel() {
        return Channel.findById(1);
    }

    /**
     * Get all channels
     *
     * @return
     */
    public static List<Channel> getAllChannels() {
        return getFinder().all();
    }

    public UserProfile getChannelOwner() {
        return channelOwner;
    }

    public void setChannelOwner(UserProfile channelOwner) {
        this.channelOwner = channelOwner;
    }

    public List<UserProfile> getMembers() {
        return members;
    }

    public void setMembers(List<UserProfile> members) {
        this.members = members;
    }

    public Channel addMembers(UserProfile user) {
        this.members.add(user);

        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    /**
     * Convert Channel object into JSON.
     *
     * @return
     */
    @Override
    public JsonNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("channelId", this.channelId)
                .put("channelName", this.channelName)
                .put("channelDescription", this.channelDescription)
                .put("channelOwner", this.channelOwner != null ? this.channelOwner.getUsername() : null)
                .put("ownerId", this.channelOwner != null ? this.channelOwner.getId() : null)
                .put("dateCreated", this.dateCreated.toString());

        return json;
    }
}
