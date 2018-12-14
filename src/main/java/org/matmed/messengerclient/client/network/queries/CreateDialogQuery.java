package org.matmed.messengerclient.client.network.queries;


import org.matmed.messengerclient.client.network.ClientSocket;
import org.matmed.messengerclient.client.suppliers.DialogManager;
import com.alibaba.fastjson.JSONObject;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.*;
import org.matmed.messengerclient.common.objects.requests.CreateDialogRequest;
import org.matmed.messengerclient.common.objects.requests.CreateGroupRequest;
import org.matmed.messengerclient.common.objects.Dialog;

import java.io.IOException;
import java.util.List;

public class CreateDialogQuery {
    private static int sent = +0;
    public static void sendDialogQuery(String login, String initialMessage) throws IOException {
        CreateDialogRequest r = new CreateDialogRequest();
        r.setPartner(login);
        r.setInitialMessage(initialMessage);
        ClientSocket.getInstance().send(new Request().setMethod(Methods.CREATE_DIALOG).setBody(r.toJSONObject()));
        sent++;
    }
    public static void sendGroupQuery(String title, List<String> partners, byte[] avatar) throws IOException {
        CreateGroupRequest r = new CreateGroupRequest();
        r.setTitle(title);
        r.setPartners(partners);
        r.setAvatar(avatar);
        ClientSocket.getInstance().send(new Request().setMethod(Methods.CREATE_GROUP).setBody(r.toJSONObject()));
        sent++;
    }
    public static void sendChannelQuery(String title, List<String> partners, byte[] avatar) throws IOException
    {
        CreateGroupRequest r = new CreateGroupRequest();
        r.setTitle(title);
        r.setPartners(partners);
        r.setAvatar(avatar);
        ClientSocket.getInstance().send(
                new Request().setMethod(Methods.CREATE_CHANNEL).setBody(r.toJSONObject())
        );
        sent++;
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            if (sent != 0) {
                // It's the new dialog we've just created!
                JSONObject unparsedDialog = response.getBody().getJSONObject("info");

                DialogManager.getInstance().addDialog(unparsedDialog);
                Dialog dialog = response.getBody().getObject("dialog", Dialog.class);
                DialogManager.getInstance().addMessages(dialog.getDialogId(), dialog.getMessages());
                sent--;
            }
            else
            {
                DialogManager.getInstance().addDialog(response.getBody());
            }
        }
    }
}
