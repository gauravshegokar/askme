package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Channel;
import models.UserProfile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.UserInformationService;

import java.util.List;

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
    public Result getUserSubscribedChannels(String userId) {

//        boolean isAuthentic = request().headers().get("auth")[0].equals(userId) ? true : false;
//        if (!isAuthentic) {
//            return badRequest("\"{\"error\":\"Authentication failed, Please login again\"}\"");
//        }
        // any user can check any users profile
        return new UserInformationService().getUserSubscribedChannels(userId);
    }

    /**
     * Get owned channels of a user.
     *
     * @param userId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getOwnedChannels(String userId) {
        List<Channel> channels = UserProfile.findById(userId).getOwnChannels();
        ObjectNode json = Json.newObject();

        json.set("ownedChannels", Json.toJson(channels));

        return ok(json);
    }
}
