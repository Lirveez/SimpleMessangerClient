package org.matmed.messengerclient.client.network.queries;


import org.matmed.messengerclient.client.network.ClientSocket;
import org.matmed.messengerclient.client.utils.CallbackMap;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.File;
import org.matmed.messengerclient.common.objects.requests.GetFileRequest;

import java.io.IOException;
import java.util.function.Consumer;

public class GetFileQuery {
    private static final CallbackMap<File> CALLBACK_MAP = new CallbackMap<>();
    public static void sendQuery(String path, Consumer<File> callback) throws IOException {
        GetFileRequest fileRequest = new GetFileRequest();
        fileRequest.setFilePath(path);
        Request r = new Request().setMethod(Methods.GET_FILE).setBody(fileRequest.toJSONObject());
        ClientSocket.getInstance().send(r);
        CALLBACK_MAP.put(path, callback);
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK) {
            File file = response.getBody().toJavaObject(File.class);
            CALLBACK_MAP.get(file.getPath()).forEach(c->c.accept(file));
            CALLBACK_MAP.remove(file.getPath());
        }

    }
}
