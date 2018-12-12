package org.matmed.messengerclient.client.network.queries;

import org.matmed.messengerclient.client.utils.Listener;
import client.network.ClientSocket;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.requests.RegistrationData;

import java.io.IOException;

public class RegistrationQuery {
    private static Listener<Integer> errorListener, successListener;
    public static void sendQuery(String login, String password, String name, String email, String info, byte[] avatar) throws IOException {

        RegistrationData data = new RegistrationData();
        data.setPassword(password);
        data.setLogin(login);
        data.setName(name);
        data.setEmail(email);
        data.setInfo(info);
        data.setAvatar(avatar);
        Request r = new Request().setMethod(Methods.REGISTER).setBody(data.toJSONObject());
        ClientSocket.getInstance().send(r);
    }

    public static void setErrorListener(Listener<Integer> errorListener) {
        RegistrationQuery.errorListener = errorListener;
    }

    public static void setSuccessListener(Listener<Integer> successListener) {
        RegistrationQuery.successListener = successListener;
    }

    public static void onHandle(Response r)
    {
        if (r.getStatus() == Response.OK)
        {
            successListener.onHandle(Response.OK);
        }
        else
        {
            errorListener.onHandle(r.getCode());
        }
    }
}
