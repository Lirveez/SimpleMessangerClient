package org.matmed.messengerclient.common.objects.requests;

import org.matmed.messengerclient.common.objects.Body;

public class CreateDialogRequest extends Body {
    private String partner;
    private String initialMessage;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }
}
