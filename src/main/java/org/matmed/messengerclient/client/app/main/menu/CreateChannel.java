package org.matmed.messengerclient.client.app.main.menu;

import org.matmed.messengerclient.client.app.main.AbstractWindow;
import org.matmed.messengerclient.client.controllers.menu.CreateGroupChannelController;
import org.matmed.messengerclient.client.network.queries.CreateDialogQuery;

import java.io.IOException;

public class CreateChannel extends AbstractWindow {

    public CreateChannel() throws IOException{
        CreateGroupChannelController controller = CreateGroupChannelController.create();
        this.root = controller.getRoot();
        controller.setOnClick(CreateDialogQuery::sendChannelQuery);
    }

}
