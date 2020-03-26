package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Jsonable;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.search.SearchService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SearchController extends Controller {
    /**
     * Search hashtags and posts.
     *
     * @param keywordsString `+` separated keywords
     * @param searchType     'all'|'posts'|'hashtags'
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result search(String keywordsString, String searchType) {
        List<String> keywords = Arrays.asList(keywordsString.split("\\+"));
        ObjectNode json = Json.newObject();
        SearchService searchService = new SearchService(searchType);
        HashMap<String, List<? extends Jsonable>> result = searchService.search(keywords);

        result.forEach((key, value) -> {
            json.set(key, Jsonable.toJson(value));
        });

        return ok(json);
    }

}
