package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserInformationService;

public class UserInformationController extends Controller {

    public Result getUserBasicInfo() {
        return new UserInformationService().getUserBasicInfo(request().getQueryString("userId"));
    }

    public Result getUserInterests(){

        return new UserInformationService().getUserInterests(request().getQueryString("userId"));
    }


}
