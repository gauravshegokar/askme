package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment extends Model {
    @Id
    private long commentId;

    @Constraints.Required
    private String commentText;

    @Constraints.Required
    private Date date;

    @ManyToOne
    private UserProfile user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public Comment setCommentText(String commentText) {
        this.commentText = commentText;

        return this;
    }

    public Date getDate() {
        return date;
    }

    public Comment setDate(Date date) {
        this.date = date;

        return this;
    }

    public UserProfile getUser() {
        return user;
    }

    public Comment setUser(UserProfile user) {
        this.user = user;

        return this;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Convert comment to JSON.
     *
     * @return
     */
    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("commentId", this.commentId)
                .put("commentText", this.commentText)
                .put("date", this.date.toString())
                .put("userId", this.user.getId())
                .put("username", this.user.getUsername());

        return json;
    }
}
