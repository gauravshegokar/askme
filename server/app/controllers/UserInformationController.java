package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserInformationService;

import static play.mvc.Http.Context.Implicit.request;

public class UserInformationController extends Controller {
    public Result getUserBasicInfo(String userId) {


        if (request().headers().get("auth")[0].equals(userId)) {
            return new UserInformationService().getUserBasicInfo(userId);
        } else {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }
    }

    //TODO: complete it in later sprints
    public Result getUserInterests(String userId) {
        return new UserInformationService().getUserInterests(request().getQueryString("userId"));
    }

    public Result getUserPosts(String userId) {

        boolean isAuthentic = request().headers().get("auth")[0].equals(userId) ? true : false;
        if (!isAuthentic) {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }

        return new UserInformationService().getUserPosts(userId);

    }
}
