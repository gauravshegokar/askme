/**
 * @see https://www.playframework.com/documentation/2.2.x/JavaGuide4#Implementing-authenticators
 */
package middlewares;

import models.UserProfile;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Map;

public class Secured extends Security.Authenticator {
    /**
     * @param context
     * @return userId
     */
    @Override
    public String getUsername(Http.Context context) {
        Map<String, String[]> headers = context.request().headers();
        String[] auth = headers.get("auth");

        if (auth != null && auth.length == 1) {
            String userId = auth[0];
            UserProfile user = UserProfile.findById(Integer.parseInt(userId));

            return user.getId() + "";
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return forbidden("Authentication failed, Please login again");
    }
}
