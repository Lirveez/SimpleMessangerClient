package org.matmed.messengerclient.client.controllers.menu;

import org.matmed.messengerclient.client.app.main.AbstractWindow;
import org.matmed.messengerclient.client.app.main.MainWindowManager;
import org.matmed.messengerclient.client.controllers.ImagePicker;
import org.matmed.messengerclient.client.controllers.UserListController;
import org.matmed.messengerclient.client.suppliers.UserSupplier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;

public class CreateGroupChannelController extends AbstractWindow
{
    @FXML
    private TextField title;
    @FXML
    private AnchorPane empty;
    @FXML
    private Circle avatarSelector;

    private UserListController listController;

    private TriConsumer<String, List<String>, byte[]> onClick = (s, list, b) -> {};
    private byte[] avatar = null;

    @FXML
    private void onClick()
    {
        try {
            onClick.consume(title.getText(), listController.getSelected(), avatar);
            MainWindowManager.getInstance().closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void selectAvatar()
    {
        ImagePicker picker = MainWindowManager.getInstance().replaceWindow(ImagePicker::create);
        if (picker == null)
            return;
        picker.setOnImageSelect(bytes -> {
            avatar = bytes;
            System.out.println("Получил пикчу, заебОк");
        });
    }
    public static CreateGroupChannelController create() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CreateGroupChannelController.class.getResource("CreateGroup.fxml"));
        loader.load();
        CreateGroupChannelController c = loader.getController();
        c.init();
        return c;
    }
    private void init() throws IOException {
        listController = UserListController.create();
        listController.setUserList(UserSupplier.getInstance().getFriendList());
        listController.setSize(300, 300).setTextListener(listController::filter).setSelectable(true);
        empty.getChildren().add(listController.getRoot());
    }

    public void setOnClick(TriConsumer<String, List<String>, byte[]> onClick)
    {
        this.onClick = onClick;
    }

    @FunctionalInterface
    public interface TriConsumer<E, T, V>
    {
        void consume(E e, T t, V v) throws Exception;
    }
}
