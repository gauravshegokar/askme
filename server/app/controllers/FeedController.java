package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Post;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class FeedController extends Controller {
    /**
     * @return the most recently added posts.
     */
    @Security.Authenticated(Secured.class)
    public Result getFeed(int limit) {
        ObjectNode json = Json.newObject();

        json.set("feed", Post.toJson(
                Post.getMostRecentPosts(limit)
        ));

        return ok(json);
    }
}
