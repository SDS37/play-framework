package controllers;

import play.mvc.Http.Context;

import play.mvc.Security.Authenticator;
import play.mvc.Result;

public class UserAuthenticator extends Authenticator {

	@Override
    public Result onUnauthorized(Context context) {
        return redirect(routes.UserAuthenticationController.userForm());
    }
	
}
