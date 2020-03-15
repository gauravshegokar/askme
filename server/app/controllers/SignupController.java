package controllers;

import com.google.gson.JsonObject;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;


public class SignupController extends Controller {

    public Result addUser() {

        String username = null;
        String password = null;
        String fname;
        String lname;

        // Validation
        try {
            username = request().body().asFormUrlEncoded().get("username")[0];
            password = request().body().asFormUrlEncoded().get("password")[0];
        } catch (Exception e){
            return badRequest("{\"error\":\"Missing required parameters\"}");
        }
        try {
            fname = request().body().asFormUrlEncoded().get("fname")[0];
        }catch (Exception e){
            fname = "";
        }

        try {
            lname = request().body().asFormUrlEncoded().get("lname")[0];
        }catch (Exception e){
            lname = "";
        }

        // creating user bean
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFname(fname);
        user.setLname(lname);

        // Check if user exits
        List<User> dbUserMapped = User.find.query().where().ilike("username", user.getUsername()).findList();
        if (dbUserMapped.size() == 0 ){
            user.save();
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("auth", String.valueOf(user.getId()));
            return status(201, resultJson.toString());
        }
        else if(dbUserMapped.size() > 0){
            return badRequest("{\"error\":\"username already exists\"}");
        }

        return badRequest("{\"error\":\"Unable to perform operation\"}");

    }
}
