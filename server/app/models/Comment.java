package models;


import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

public class Comment extends Model {
    @Id
    private long commendId;

    @Constraints.Required
    private String commentText;

    @Constraints.Required
    private Date date;

    @OneToOne
    private UserProfile user;

    public long getCommendId() {
        return commendId;
    }

    public void setCommendId(long commendId) {
        this.commendId = commendId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
