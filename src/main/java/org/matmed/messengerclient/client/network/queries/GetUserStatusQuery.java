package org.matmed.messengerclient.client.network.queries;


import org.matmed.messengerclient.client.network.ClientSocket;
import org.matmed.messengerclient.client.utils.UserStatusListener;
import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.User;

import java.io.IOException;
import java.util.function.Consumer;

public class GetUserStatusQuery {
    public static void hookToUser(String login, Consumer<User> handler) throws IOException {
        if (!UserStatusListener.getInstance().exist(login))
        {
            UserStatusListener.getInstance().addHandler(login, handler);
            sendQuery(login);
        }
        else
            UserStatusListener.getInstance().addHandler(login, handler);
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            User user =
                    JSON.parseObject(response.getBody().toJSONString(), User.class);
            //ApplicationBank.getInstance().updateUserStatus(user);
            UserStatusListener.getInstance().acceptUser(user);
        }
    }
    private static void sendQuery(String login) throws IOException
    {
        User user = new User();
        user.setLogin(login);
        Request r = new Request().setMethod(Methods.HOOK_USER_STATUS).setBody(user.toJSONObject());
        ClientSocket.getInstance().send(r);
    }
}
