package domains;

import com.google.gson.JsonObject;
import domains.interfaces.User;
import models.UserProfile;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.status;

public class RegularUser implements User {
    @Override
    public Result addUser(String username, String pwd, String userType, String fname, String lname) {
        // creating user bean
        UserProfile user = new UserProfile();
        user.setUsername(username);
        user.setPassword(pwd);
        user.setFname(fname);
        user.setLname(lname);
        user.setAccessLevel("regular");

        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().ilike("username", user.getUsername()).findList();
        if (dbUserMapped.size() == 0) {
            user.save();
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("auth", String.valueOf(user.getId()));
            return status(201, resultJson.toString());
        } else if (dbUserMapped.size() > 0) {
            return badRequest("{\"error\":\"username already exists\"}");
        }

        return badRequest("{\"error\":\"Unable to perform operation\"}");
    }

    @Override
    public Result getUser() {
        return null;
    }
}
