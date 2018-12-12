package org.matmed.messengerclient.client.suppliers;

import org.matmed.messengerclient.common.objects.Message;
import org.matmed.messengerclient.common.objects.User;
import org.matmed.messengerclient.common.objects.requests.DialogTypes;

public class DialogBean extends AbstractDialogBean{
    public final User partner;

    protected DialogBean(int dialogId, Message lastMessage, User partner, int unread) {
        super(DialogTypes.DIALOG, dialogId);
        this.lastMessage.set(lastMessage);
        this.partner = partner;
        this.unreadCount.set(unread);
        title.set(partner.getName());
    }
}
