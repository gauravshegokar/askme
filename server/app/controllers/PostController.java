package controllers;

import play.mvc.Result;
import services.PostService;

import static play.mvc.Http.Context.Implicit.request;

public class PostController {
    public Result addPost() {
        boolean isProfane = request().body().asFormUrlEncoded().
                get("isProfane").toString().equalsIgnoreCase("true") ? true : false;

        return new PostService().addPost(request().body().asFormUrlEncoded().get("channelId").toString(),
                request().headers().get("auth").toString(),
                request().body().asFormUrlEncoded().get("postText").toString(),
                isProfane,
                request().body().asFormUrlEncoded().get("tags").toString());
    }
}
