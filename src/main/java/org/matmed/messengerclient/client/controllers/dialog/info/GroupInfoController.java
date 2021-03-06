package org.matmed.messengerclient.client.controllers.dialog.info;

import org.matmed.messengerclient.client.app.main.AbstractWindow;
import org.matmed.messengerclient.client.app.main.MainWindowManager;
import org.matmed.messengerclient.client.controllers.UserListController;
import org.matmed.messengerclient.client.suppliers.AbstractDialogBean;
import org.matmed.messengerclient.client.suppliers.DialogManager;
import org.matmed.messengerclient.client.suppliers.GroupBean;
import org.matmed.messengerclient.common.objects.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class GroupInfoController extends AbstractWindow {
    @FXML
    private Text title;
    @FXML
    private AnchorPane userList;
    private int dialogId;


    public static GroupInfoController create(int dialogId) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(GroupInfoController.class.getResource("GroupInfo.fxml"));
        loader.load();
        GroupInfoController c = loader.getController();
        c.init(dialogId);
        return c;
    }
    @FXML
    private void onClick()
    {
        MainWindowManager.getInstance().replaceWindow(()->AddUsersToGroupController.create(dialogId));
    }
    private void init(int dialogId) throws IOException
    {
        this.dialogId = dialogId;
        AbstractDialogBean dialogBean = DialogManager.getInstance().getDialogById(dialogId);
        title.textProperty().bind(dialogBean.title());
        List<User> partners = ((GroupBean)dialogBean).getMembers();
        UserListController controller = UserListController.create();
        controller.setTextListener(controller::search).setSelectable(true).setUserList(partners);
        userList.getChildren().add(controller.getRoot());
    }
}
