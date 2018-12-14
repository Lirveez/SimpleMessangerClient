package org.matmed.messengerclient.client.network.queries;


import org.matmed.messengerclient.client.network.ClientSocket;
import com.alibaba.fastjson.JSON;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Request;
import org.matmed.messengerclient.common.Response;
import org.matmed.messengerclient.common.objects.DialogInfo;
import org.matmed.messengerclient.common.objects.requests.AddUsersToGroupRequest;

import java.io.IOException;
import java.util.List;

public class GroupModificationQuery {
    public static void addUsers(int dialogId, List<String> users) throws IOException {
        AddUsersToGroupRequest request = new AddUsersToGroupRequest();
        request.setDialogId(dialogId);
        request.setUsers(users);
        ClientSocket.getInstance().send(new Request().setMethod(Methods.MODIFY_GROUP).setBody(request.toJSONObject()));
    }
    public static void onHandle(Response response)
    {
        if (response.getStatus() == Response.OK)
        {
            // TODO: 15.10.2018
            /*GroupModification modification = JSON.parseObject(response.getBody().toJSONString(), GroupModification.class);
            if (modification.getType() == GroupModification.NEW_USERS)
            {
                DialogBean b = ApplicationBank.getInstance().getDialogById(modification.getDialogId());
                b.users().addAll(modification.getUsers().stream().map(User::getLogin).collect(Collectors.toList()));
                modification.getUsers().forEach(user -> ApplicationBank.getInstance().addUser(user));
                modification.getMessages().forEach(m->ApplicationBank.getInstance().addMessage(m));
            }*/
        }
    }
    public static void onJoinedGroup(Response r)
    {
        DialogInfo dialogInfo = JSON.parseObject(r.getBody().toJSONString(), DialogInfo.class);
        //ApplicationBank.getInstance().addDialogInfo(dialogInfo);
    }
}
