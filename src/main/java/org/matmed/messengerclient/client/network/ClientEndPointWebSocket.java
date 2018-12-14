package org.matmed.messengerclient.client.network;

import org.matmed.messengerclient.client.network.queries.*;
import org.matmed.messengerclient.common.Methods;
import org.matmed.messengerclient.common.Response;

import javax.websocket.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@ClientEndpoint
public class ClientEndPointWebSocket {
    Session userSession = null;
    private MessageHandler messageHandler;
    private final URI endpointURI = URI.create("wss://echo.websocket.org");
    private static final int MAX_MESSAGE_SIZE = 104857600;
    private final Map<String, Consumer<Response>> handlers = new HashMap<>();
    {
        handlers.put(Methods.LOGIN, AuthorizationQuery::onHandle);
        handlers.put(Methods.GET_DIALOGS, GetDialogsQuery::onHandle);
        handlers.put(Methods.GET_DIALOG, GetDialogQuery::onHandle);
        handlers.put(Methods.SEND_MESSAGE, SendMessageQuery::onHandle);
        handlers.put(Methods.GET_USER_STATUS, GetUserStatusQuery::onHandle);
        handlers.put(Methods.REGISTER, RegistrationQuery::onHandle);
        handlers.put(Methods.FIND_USERS, FindUsersQuery::onHandle);
        handlers.put(Methods.CREATE_DIALOG, CreateDialogQuery::onHandle);
        handlers.put(Methods.CREATE_GROUP, CreateDialogQuery::onHandle);
        handlers.put(Methods.CREATE_CHANNEL, CreateDialogQuery::onHandle);
        handlers.put(Methods.MODIFY_GROUP, GroupModificationQuery::onHandle);
        handlers.put(Methods.JOIN_GROUP, GroupModificationQuery::onJoinedGroup);
        handlers.put(Methods.READ_MESSAGES, ReadMessageQuery::onHandle);
        handlers.put(Methods.GET_PROFILE, GetUserProfileQuery::handle);
        handlers.put(Methods.VERIFY_DATA, VerifyDataQuery::onHandle);
        handlers.put(Methods.GET_FILE, GetFileQuery::onHandle);
        handlers.put(Methods.GET_USER, GetUserQuery::onHandle);
    }

    public ClientEndPointWebSocket() {

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(ClientEndPointWebSocket.class, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}

