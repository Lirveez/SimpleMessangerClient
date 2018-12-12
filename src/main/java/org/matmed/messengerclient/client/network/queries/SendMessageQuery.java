package org.matmed.messengerclient.client.network.queries;

import client.network.ClientSocket;
import org.matmed.messengerclient.client.suppliers.DialogManager;
import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.Message;

import java.io.IOException;

public class SendMessageQuery {
    public static void sendQuery(int dialogId, String text) throws IOException {
        Message m = new Message();
        m.setDialogId(dialogId);
        m.setText(text);
        Request r = new Request().setMethod(Methods.SEND_MESSAGE).setBody(m.toJSONObject());
        ClientSocket.getInstance().send(r);
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            Message m =
                    JSON.parseObject(response.getBody().toJSONString(), Message.class);
            DialogManager.getInstance().addMessage(m);
        }

    }
}
