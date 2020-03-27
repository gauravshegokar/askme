package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Comment;
import models.Post;
import models.UserProfile;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import services.PostService;

import java.util.Date;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class PostController {

    /**
     * Get post by id.
     *
     * @param postId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getPost(String postId) {
        Post post = new PostService().getPost(postId);

        return ok(post.toJson());
    }

    /**
     * Get comments of a post
     *
     * @param postId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getComments(int postId) {
        Post post = Post.findById(postId);
        ObjectNode json = Json.newObject();

        json.set("comments", post.getCommentsJson());

        return ok(json);
    }

    @Security.Authenticated(Secured.class)
    public Result createComment(int postId) {
        String text = request().body().asJson().get("commentText").asText();
        // @see https://www.playframework.com/documentation/2.1.0/JavaGuide4#Using-the-current-user
        String userId = request().username();
        Post post = Post.findById(postId);
        Comment comment = new Comment();

        // init comment
        comment.setCommentText(text);
        comment.setDate(new Date());
        comment.setUser(UserProfile.findById(userId));
        comment.setPost(post);
        comment.save();

        // append comment to post
        post.addComment(comment);
        post.save();

        ObjectNode json = Json.newObject().put("_id", comment.getCommentId());

        return status(201, json.toString());
    }
}
