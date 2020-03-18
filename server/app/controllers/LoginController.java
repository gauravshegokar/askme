package controllers;

import com.google.gson.JsonObject;
import models.UserProfile;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class LoginController extends Controller {
    // POST api/login
    public Result postUser() {
        String username = null;
        String password = null;

        // Validation
        try {
            username = request().body().asJson().get("username").asText();
            password = request().body().asJson().get("password").asText();
        } catch (Exception e) {
            return badRequest("{\"error\":\"Missing required parameters\"}");
        }


        // creating user bean
        UserProfile user = new UserProfile();
        user.setUsername(username);
        user.setPassword(password);

        // Check if user exits
        List<UserProfile> dbUserMapped = new ArrayList<>();
        try {
            dbUserMapped = UserProfile.find.query().where().ilike("username", user.getUsername()).findList();
        } catch (Exception e) {
            return internalServerError("{\"error\":\"Internal Server Error\"}");
        }

        if (dbUserMapped.size() > 0 && dbUserMapped.get(0).getPassword().equals(user.getPassword())) {
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("auth", String.valueOf(dbUserMapped.get(0).getId()));
            return status(200, resultJson.toString());
        }

        return badRequest("{\"error\":\"incorrect Username or Password\"}");
    }

}
