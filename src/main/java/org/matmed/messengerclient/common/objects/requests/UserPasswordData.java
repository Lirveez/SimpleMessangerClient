package org.matmed.messengerclient.common.objects.requests;

import org.matmed.messengerclient.common.objects.Body;

public class UserPasswordData extends Body {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
