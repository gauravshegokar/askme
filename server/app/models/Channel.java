package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Channel extends Model {
    public static final Finder<Long, Channel> find = new Finder<>(Channel.class);
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
    private List<UserProfile> members;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private String channelDescription;

    @Constraints.Required
    @Column(columnDefinition = "datetime")
    private Date dateCreated;

    public static Finder<Long, Channel> getFind() {
        return find;
    }

    /**
     * Find channel by id
     *
     * @param channelId
     * @return
     */
    public static Channel findById(int channelId) {
        return find.byId((long) channelId);
    }

    /**
     * Get all channels
     * @return
     */
    public static List<Channel> getAllChannels() {
        return find.all();
    }

    /**
     * Convert a list of channels to JsonNode
     *
     * @param channels
     * @return
     */
    public static JsonNode toJson(List<Channel> channels) {
        return Json.toJson(
                channels.stream()
                        .map(Channel::toJson)
                        .collect(Collectors.toList()));
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
