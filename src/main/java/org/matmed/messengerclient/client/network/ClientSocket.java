package org.matmed.messengerclient.client.network;

import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.client.network.queries.*;
import org.matmed.messengerclient.common.Methods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.matmed.messengerclient.common.*;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

public class ClientSocket{
    private static ClientSocket instance;

    private final Map<String, Consumer<Response>> handlers = new HashMap<>();
    {
        System.out.println("jopa");
         handlers.put(Methods.LOGIN, AuthorizationQuery::onHandle);
        handlers.put(Methods.GET_DIALOGS, GetDialogsQuery::onHandle);
        handlers.put(Methods.GET_DIALOG, GetDialogQuery::onHandle);
        handlers.put(Methods.SEND_MESSAGE, SendMessageQuery::onHandle);
        handlers.put(Methods.GET_USER_STATUS, GetUserStatusQuery::onHandle);
        handlers.put(Methods.REGISTER, RegistrationQuery::onHandle);
        handlers.put(Methods.FIND_USERS, FindUsersQuery::onHandle);
        handlers.put(Methods.CREATE_DIALOG, CreateDialogQuery::onHandle);
        handlers.put(Methods.CREATE_GROUP, CreateDialogQuery::onHandle);
        handlers.put(Methods.CREATE_CHANNEL, CreateDialogQuery::onHandle);
        handlers.put(Methods.MODIFY_GROUP, GroupModificationQuery::onHandle);
        handlers.put(Methods.JOIN_GROUP, GroupModificationQuery::onJoinedGroup);
        handlers.put(Methods.READ_MESSAGES, ReadMessageQuery::onHandle);
        handlers.put(Methods.GET_PROFILE, GetUserProfileQuery::handle);
        handlers.put(Methods.VERIFY_DATA, VerifyDataQuery::onHandle);
        handlers.put(Methods.GET_FILE, GetFileQuery::onHandle);
        handlers.put(Methods.GET_USER, GetUserQuery::onHandle);
    }

    public static ClientSocket getInstance() {
        return instance;
    }
    public void send(Request r) throws IOException {
        String s = JSON.toJSONString(r);
        System.out.println("Sending: "+s);
        //getSession().getRemote().sendString(s);
        //System.out.println("JUST SENT!");
    }

    public void onWebSocketConnect(String sess) {
        //super.onWebSocketConnect(sess);
        instance = this;
    }

    public void onWebSocketText(String message) {
        System.out.println("RECEIVED: "+message);
        Response r = JSON.parseObject(message, Response.class);
        Consumer<Response>  consumer = handlers.get(r.getType());
        if (consumer != null)
            consumer.accept(r);
    }

    public void onWebSocketError(Throwable cause) {
        System.out.println("ERROR: "+cause.getMessage());
    }
}
