package org.matmed.messengerclient.client.network.queries;

import client.network.ClientSocket;
import org.matmed.messengerclient.client.suppliers.DialogManager;
import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.ReadMessages;

import java.io.IOException;

public class ReadMessageQuery {
    public static void sendQuery(int dialogId) throws IOException {
        ReadMessages m = new ReadMessages();
        m.setDialogId(dialogId);
        Request r = new Request().setMethod(Methods.READ_MESSAGES).setBody(m.toJSONObject());
        ClientSocket.getInstance().send(r);
    }
    public static void onHandle(Response r)
    {
        if (r.getStatus() == Response.OK)
        {
            ReadMessages m = JSON.parseObject(r.getBody().toJSONString(), ReadMessages.class);
            DialogManager.getInstance().readMessages(m.getDialogId());
        }
    }
}
