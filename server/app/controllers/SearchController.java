package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Post;
import models.Tag;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Arrays;
import java.util.List;

public class SearchController extends Controller {
    /**
     * Search hashtags and posts.
     *
     * @param keywordsString `+` separated keywords
     * @return
     */
    public Result search(String keywordsString) {
        List<String> keywords = Arrays.asList(keywordsString.split("\\+"));
        ObjectNode json = Json.newObject();
        List<Post> posts = Post.searchByKeywords(keywords);
        List<Tag> tags = Tag.searchByKeywords(keywords);

        json.set("posts", Post.toJson(posts));
        json.set("tags", Tag.toJson(tags));

        return ok(json);
    }
}
