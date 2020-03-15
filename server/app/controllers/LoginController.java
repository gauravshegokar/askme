package controllers;

import com.google.gson.JsonObject;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class LoginController extends Controller {

    // POST api/login
    public Result postUser(){
        String username = null;
        String password = null;

        // Validation
        try {
            username = request().body().asFormUrlEncoded().get("username")[0];
            password = request().body().asFormUrlEncoded().get("password")[0];
        } catch (Exception e){
            return badRequest("{\"error\":\"Missing required parameters\"}");
        }


        // creating user bean
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Check if user exits
        List<User> dbUserMapped=new ArrayList<>();
        try {
            dbUserMapped=User.find.query().where().ilike("username", user.getUsername()).findList();
        }
        catch (Exception e){
            return internalServerError("{\"error\":\"Internal Server Error\"}");
        }

        if (dbUserMapped.size() > 0 && dbUserMapped.get(0).getPassword().equals(user.getPassword())){
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("auth", String.valueOf(dbUserMapped.get(0).getId()));
            return status(200, resultJson.toString());
        }

        return badRequest("{\"error\":\"incorrect Username or Password\"}");
    }

}
