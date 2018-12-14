package org.matmed.messengerclient.client.network.queries;

import org.matmed.messengerclient.client.suppliers.DialogManager;

import org.matmed.messengerclient.client.network.ClientSocket;
import org.matmed.messengerclient.client.app.main.MainWindowManager;
import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.Dialog;
import org.matmed.messengerclient.common.objects.requests.DialogRequest;

import java.io.IOException;

public class GetDialogQuery {
    public static void sendQuery(int dialogId) throws IOException {
        DialogRequest request = new DialogRequest();
        request.setDialogId(dialogId);
        Request r = new Request().setMethod(Methods.GET_DIALOG).setBody(request.toJSONObject());
        ClientSocket.getInstance().send(r);
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            Dialog dialog =
                    JSON.parseObject(response.getBody().toJSONString(), Dialog.class);
            DialogManager.getInstance().addMessages(dialog.getDialogId(), dialog.getMessages());
            MainWindowManager.getInstance().setDialog(dialog.getDialogId());
        }

    }
}
