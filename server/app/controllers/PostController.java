package controllers;

import play.mvc.Result;
import services.PostService;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.badRequest;

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
}
