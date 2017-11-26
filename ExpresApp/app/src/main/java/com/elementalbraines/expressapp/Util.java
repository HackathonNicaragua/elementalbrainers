package com.elementalbraines.expressapp;

import com.facebook.login.LoginManager;
import com.tumblr.remember.Remember;

/**
 * Created by Maurel on 26/11/2017.
 */

public class Util {

    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PICTURE = "USER_PICTURE";
    public static final String SHOW_WELLCOME = "SHOW_WELLCOME";

    public static void logout(){
        Remember.remove(Util.USER_ID);
        Remember.remove(Util.USER_NAME);
        Remember.remove(Util.USER_PICTURE);
        LoginManager.getInstance().logOut();
    }

}
