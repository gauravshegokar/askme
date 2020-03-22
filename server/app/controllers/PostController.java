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
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class PostController {
    public Result addPost(String channelId) {
        boolean isAuthentic = request().headers().get("auth")[0].equals(null) ? false : true;

        if (!isAuthentic) {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }

        boolean isProfane = request().body().asJson().get("profane").asText().
                equalsIgnoreCase("true") ? true : false;

        return new PostService().addPost(channelId,
                request().headers().get("auth")[0],
                request().body().asJson().get("postText").asText(),
                isProfane,
                request().body().asJson().get("tags").asText());
    }

    public Result getPost(String postId) {
        boolean isAuthentic = request().headers().get("auth")[0].equals(null) ? false : true;

        if (!isAuthentic) {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }

        return new PostService().getPost(postId, request().headers().get("auth")[0]);
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

        return ok(json);
    }
}
