package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserInformationService;

import static play.mvc.Http.Context.Implicit.request;

public class UserInformationController extends Controller {
    public Result getUserBasicInfo(String userId) {

        // any user can check any users profile
        return new UserInformationService().getUserBasicInfo(userId);
//        if (request().headers().get("auth")[0].equals(userId)) {
//            return new UserInformationService().getUserBasicInfo(userId);
//        } else {
//            return badRequest("\"{\"error\":\"Authentication failed, Please login again\"}\"");
//        }
    }

    //TODO: complete it in later sprints
    public Result getUserInterests(String userId) {
        return new UserInformationService().getUserInterests(request().getQueryString("userId"));
    }

    public Result getUserPosts(String userId) {

//        boolean isAuthentic = request().headers().get("auth")[0].equals(userId) ? true : false;
//        if (!isAuthentic) {
//            return badRequest("\"{\"error\":\"Authentication failed, Please login again\"}\"");
//        }
        // any user can check any users profile
        return new UserInformationService().getUserPosts(userId);

    }

    //TODO: by default returning channel with id 1, change it in future sprints
    public Result getUserSubscribedChannels(String userId){

//        boolean isAuthentic = request().headers().get("auth")[0].equals(userId) ? true : false;
//        if (!isAuthentic) {
//            return badRequest("\"{\"error\":\"Authentication failed, Please login again\"}\"");
//        }
        // any user can check any users profile
        return  new UserInformationService().getUserSubscribedChannels(userId);
    }
}
