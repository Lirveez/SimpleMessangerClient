package org.matmed.messengerclient.client.app.main.dialog;

import org.matmed.messengerclient.client.suppliers.AbstractDialogBean;
import org.matmed.messengerclient.client.suppliers.GroupBean;
import org.matmed.messengerclient.client.suppliers.UserSupplier;

import java.io.IOException;

class ChannelWrapper extends AbstractDialogWrapper<GroupBean> {
    ChannelWrapper(AbstractDialogBean dialogBean) throws IOException {
        super((GroupBean) dialogBean);
        init();
    }
    private void init()
    {
        dialogController.getTitle().setText(dialogBean.title().getValue());
        dialogController.getInfo().setText("Читают: "+ (dialogBean.membersCount().get() - 1)+" человек(а)");
        bindMessages(dialogBean);
        if (!dialogBean.creator.equalsIgnoreCase(UserSupplier.getInstance().getMyLogin()))
        {
            dialogController.getMessageWindow().setBottom(null);
        }
    }
}
