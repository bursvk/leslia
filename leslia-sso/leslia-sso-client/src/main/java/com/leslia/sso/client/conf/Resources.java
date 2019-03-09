package com.leslia.sso.client.conf;

import java.util.ResourceBundle;

public class Resources {

    public static final ResourceBundle resourceBundle=ResourceBundle.getBundle("conf/sso");

    public static final String SSO_SERVICE_LOGIN_URL=resourceBundle.getString("sso.service.login.url");

    public static final String SSO_SERVICE_VERIFY_TICKET_URL=resourceBundle.getString("sso.service.verify.ticket.url");

    public static final String SSO_SERVICE_LOGOUT_URL=resourceBundle.getString("sso.service.logout.url");

    public static final String SSO_CLIENT_LOGOUT_URL=resourceBundle.getString("sso.client.logout.url");


}
