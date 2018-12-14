package org.matmed.messengerclient.client.network.queries;

import org.matmed.messengerclient.client.network.ClientSocket;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.requests.RegistrationData;

import java.io.IOException;
import java.util.function.Consumer;

public class VerifyDataQuery {
    private static Consumer<Integer> callback = null;
    public static void send(String login, String password, String name, String email, String info, Consumer<Integer> callback) throws IOException
    {
        RegistrationData registrationData = new RegistrationData();
        registrationData.setLogin(login);
        registrationData.setPassword(password);
        registrationData.setEmail(email);
        registrationData.setInfo(info);
        registrationData.setName(name);
        Request request = new Request().setMethod(Methods.VERIFY_DATA).setBody(registrationData.toJSONObject());
        ClientSocket.getInstance().send(request);
        VerifyDataQuery.callback = callback;
    }
    public static void onHandle(Response response)
    {
        if (callback!= null)
            callback.accept(response.getCode());
    }
}
