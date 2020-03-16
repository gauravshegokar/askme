package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import services.SignupService;


public class SignupController extends Controller {

    public Result addUser() {


        String username = null;
        String password = null;
        String fname;
        String lname;
        String userType;

        // Validation
        try {
            username = request().body().asFormUrlEncoded().get("username")[0];
            password = request().body().asFormUrlEncoded().get("password")[0];

        } catch (Exception e) {
            return badRequest("{\"error\":\"Missing required parameters\"}");
        }
        try {
            fname = request().body().asFormUrlEncoded().get("fname")[0];
        } catch (Exception e) {
            fname = "";
        }

        try {
            lname = request().body().asFormUrlEncoded().get("lname")[0];
        } catch (Exception e) {
            lname = "";
        }

        userType = request().body().asFormUrlEncoded().get("userType")[0];

        return SignupService.addUser(username, password, userType, fname, lname);
    }
}
