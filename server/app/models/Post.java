package models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends Model {

    public static final Finder<Long, Post> find = new Finder<>(Post.class);
    @Constraints.Required
    @Column(columnDefinition = "datetime")
    public Timestamp date_created;
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
     * Find post by id
     *
     * @param postId
     * @return
     */
    public static Post findById(int postId) {
        return find.where().eq("post_id", postId).findUnique();
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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

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
}
