package controllers;

import com.avaje.ebean.QueryIterator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Jsonable;
import models.Post;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

public class FeedController extends Controller {
    /**
     * [Iterator Pattern]
     *
     * @return the most recently added posts.
     */
    @Security.Authenticated(Secured.class)
    public Result getFeed(int limit) {
        ObjectNode json = Json.newObject();
        QueryIterator<Post> iterator = Post.getMostRecentPosts(limit);
        List<Post> feed = new ArrayList<>();
        int count = 0;

        while (count < limit && iterator.hasNext()) {
            count++;
            feed.add(iterator.next());
        }

        json.set("feed", Jsonable.toJson(feed));

        return ok(json);
    }
}
