package controllers;


import com.google.gson.JsonObject;
import play.mvc.Controller;
import play.mvc.Result;
import services.SignupService;


public class SignupController extends Controller {

    public Result addUser() {
        String username;
        String password;
        String fname;
        String lname;
        String userType;

        // Validation
        try {

            username = request().body().asJson().get("username").asText();
            password = request().body().asJson().get("password").asText();
            userType = request().body().asJson().get("userType").asText();

        } catch (Exception e) {

            return badRequest("{\"error\":\"Missing required parameters\"}");
        }

        try {
            fname = request().body().asJson().get("fname").asText();
        } catch (Exception e) {
            fname = "";
        }

        try {
            lname = request().body().asJson().get("lname").asText();
        } catch (Exception e) {
            lname = "";
        }

        if (!(userType.equalsIgnoreCase("admin") | userType.equalsIgnoreCase("regular"))) {
            return badRequest("{\"error\":\"Incorrect user type\"}");
        }

        JsonObject userJson;
        try {

            userJson = SignupService.addUser(username, password, userType, fname, lname);


            if (userJson.get("success").getAsBoolean() == true) {


                return status(201, userJson.get("userId").getAsString());
            } else {

                return badRequest("{error: " +
                        userJson.get("msg") + "}");
            }
        } catch (Exception e) {

            return internalServerError("{\"error\":\"Couldn't create new user \"}");
        }


    }
}
