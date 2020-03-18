package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserInformationService;

public class UserInformationController extends Controller {
    public Result getUserBasicInfo(String userId) {

        System.out.println("header id ="  +request().headers().get("auth")[0]);
        if (request().headers().get("auth")[0].equals(userId)) {
            return new UserInformationService().getUserBasicInfo(userId);
        } else {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }
    }

    public Result getUserInterests(String userId) {
        return new UserInformationService().getUserInterests(request().getQueryString("userId"));
    }
}
