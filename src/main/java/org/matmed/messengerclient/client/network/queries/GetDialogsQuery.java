package org.matmed.messengerclient.client.network.queries;

import org.matmed.messengerclient.client.suppliers.DialogManager;

import org.matmed.messengerclient.client.network.ClientSocket;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.requests.DialogListRequest;

import java.io.IOException;
import java.util.Arrays;

public class GetDialogsQuery {
    public static void sendQuery(int count) throws IOException {
        DialogListRequest request = new DialogListRequest();
        request.setCount(count);
        Request r = new Request().setMethod(Methods.GET_DIALOGS).setBody(request.toJSONObject());
        ClientSocket.getInstance().send(r);
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            JSONArray array = response.getBody().getJSONArray("dialogs");
            JSONObject[] objects = new JSONObject[array.size()];
            Arrays.setAll(objects, array::getJSONObject);
            Arrays.stream(objects).forEach(o-> DialogManager.getInstance().addDialog(o));
        }

    }
}
