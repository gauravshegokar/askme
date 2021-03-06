package models;


import com.avaje.ebean.Model;
import com.avaje.ebean.QueryIterator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * [Builder Pattern]
 */
@Entity
public class Post extends Model implements Jsonable {
    /**
     * Used to do queries.
     */
    private static final Finder<Long, Post> FINDER = new Finder<>(Post.class);

    @Constraints.Required
    @Column(columnDefinition = "datetime", updatable = false)
    public Date dateCreated;
    /**
     * Post Id
     */
    @Id
    @GeneratedValue
    private int postId;
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private UserProfile author;

    private boolean isProfane;

    @ManyToOne
    private Channel channel;

    private String text;

    @ManyToMany
    @JoinTable(name = "post_tag_mapper")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    /**
     * [Singleton Pattern]
     *
     * @return
     */
    public static Find<Long, Post> getFinder() {
        return FINDER;
    }

    /**
     * Find post by id
     *
     * @param postId
     * @return
     */
    public static Post findById(int postId) {
        return FINDER.where().eq("post_id", postId).findUnique();
    }

    /**
     * Search posts by keywords
     *
     * @param keywords
     * @return
     */
    public static List<Post> searchByKeywords(List<String> keywords) {
        // @see https://stackoverflow.com/a/7566973
        String where = "text REGEXP '" + String.join("|", keywords) + "'";

        return getFinder().query().where(where).findList();
    }

    /**
     * Get most recent posts.
     * [Iterator Pattern]
     *
     * @param limit
     * @return
     */
    public static QueryIterator<Post> getMostRecentPosts(int limit) {
        return getFinder()
                .query()
                .orderBy("date_created desc")
                .setMaxRows(limit)
                .findIterate();
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(Tag tag) {
        if (this.tags == null) {
            tags = new ArrayList<>();
        }
        this.tags.add(tag);
    }

    public Post setTags(List<Tag> tags) {
        this.tags = tags;

        return this;
    }

    public boolean isProfane() {
        return isProfane;
    }

    public Post setProfane(boolean profane) {
        isProfane = profane;

        return this;
    }

    public UserProfile getAuthor() {
        return author;
    }

    public Post setAuthor(UserProfile author) {
        this.author = author;

        return this;
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

    public Post setChannel(Channel channel) {
        this.channel = channel;

        return this;
    }

    public String getText() {
        return text;
    }

    public Post setText(String text) {
        this.text = text;

        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post addComment(Comment comment) {
        this.comments.add(comment);

        return this;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public void save() {
        dateCreated();
        super.save();
    }

    /**
     * Get all comments in JSON format.
     *
     * @return
     */
    public ArrayNode getCommentsJson() {
        ArrayNode array = Json.newArray();

        this.getComments().forEach(comment -> array.add(comment.toJson()));

        return array;
    }

    /**
     * Convert the post object to JSON.
     *
     * @return
     */
    @Override
    public JsonNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("postId", this.postId)
                .put("postText", this.text)
                .put("profane", this.isProfane)
                .put("author", this.author.getUsername())
                .put("authorId", this.author.getId())
                .put("channelId", this.channel.getChannelId())
                .put("channelName", this.channel.getChannelName())
                .put("datePosted", this.dateCreated.toString());

        json.set("tags", Jsonable.toJson(this.tags));

        return json;
    }
}
