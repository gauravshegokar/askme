package controllers;


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

        userType = request().body().asJson().get("userType").asText();


        return SignupService.addUser(username, password, userType, fname, lname);
    }
}
