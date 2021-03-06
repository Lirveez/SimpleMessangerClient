package org.matmed.messengerclient.client.controllers.dialog.info;

import org.matmed.messengerclient.client.app.main.AbstractWindow;
import org.matmed.messengerclient.client.utils.AvatarSupplier;
import org.matmed.messengerclient.common.objects.UserProfile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserProfileController extends AbstractWindow {
    @FXML
    private Circle avatar;
    @FXML
    private Text login, name, email;
    @FXML
    private TextArea info;

    public void setUser(UserProfile profile)
    {
        Platform.runLater(()-> {
            login.setText(profile.getLogin());
            name.setText(profile.getName());
            email.setText(profile.getEmail());
            info.setText(profile.getInfo());
        });
        if (profile.getAvatarPath() != null)
            AvatarSupplier.getInstance().getAvatar(profile.getAvatarPath(), this::onSetAvatar);
        else
            onSetAvatar(AvatarSupplier.paintDefaultAvatar(profile.getName()));
    }
    public static UserProfileController create() throws IOException {
        FXMLLoader loader = new FXMLLoader(UserProfileController.class.getResource("UserProfile.fxml"));
        loader.load();
        return loader.getController();
    }
    private void onSetAvatar(Image avatar)
    {
        Platform.runLater(()->this.avatar.setFill(new ImagePattern(avatar)));
    }
}
