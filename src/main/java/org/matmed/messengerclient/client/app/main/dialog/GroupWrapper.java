package org.matmed.messengerclient.client.app.main.dialog;

import org.matmed.messengerclient.client.controllers.dialog.info.GroupInfoController;
import org.matmed.messengerclient.client.suppliers.AbstractDialogBean;
import org.matmed.messengerclient.client.suppliers.GroupBean;

import java.io.IOException;

class GroupWrapper extends AbstractDialogWrapper<GroupBean> {
    GroupWrapper(AbstractDialogBean dialogBean) throws IOException {
        super((GroupBean) dialogBean);
        init();
    }
    private void init()
    {
        /*dialogBean.users().addListener((ListChangeListener<String>) c -> {
            int newSize = c.getList().size();
            Platform.runLater(()->dialogController.getInfo().setText(newSize + " человек(а)"));
        });*/
        dialogController.getInfo().setText(dialogBean.membersCount().getValue() + " человек(а)");
        dialogController.getTitle().textProperty().bind(dialogBean.title());
        bindMessages(dialogBean);
        dialogController.setInfoWindow(()->GroupInfoController.create(dialogBean.dialogId));

    }

}
