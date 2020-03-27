package services.factory;

import com.google.gson.JsonObject;
import domains.Admin;
import domains.RegularUser;
import domains.interfaces.User;
import models.UserProfile;

import static play.mvc.Results.badRequest;

public class UserFactory {

    public User getUser(String userType) {


        if (userType.equalsIgnoreCase("regular")) {
            return new RegularUser();
        }
        if (userType.equalsIgnoreCase("admin")) {
            return new Admin();
        }

        return null;
    }


}

