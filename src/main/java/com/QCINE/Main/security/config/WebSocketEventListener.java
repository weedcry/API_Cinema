package com.QCINE.Main.security.config;

import com.QCINE.Main.CustomerController.Websocket_Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    private static List<String> onlineUser = new ArrayList<String>();
    private static Map<String,String> session_user = new HashMap<String, String>();
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {

        StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());
        @SuppressWarnings("rawtypes")
        GenericMessage connectHeader = (GenericMessage) stompAccessor
                .getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER); // FIXME find a way to pass the username
        // to the server
        @SuppressWarnings("unchecked")
        Map<String, List<String>> nativeHeaders = (Map<String, List<String>>) connectHeader.getHeaders()
                .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
//        String id = nativeHeaders.get("userId").get(0);
//        String sessionId = stompAccessor.getSessionId();
//        session_user.put(sessionId, id); //cái này dùng để check trog lúc disconnect vì disconnect khong nhan dc username
//        onlineUser.add(id);
        Websocket_Controller w = new Websocket_Controller();
//        w.connect(id); // id user

//        logger.info("Chat connection by user <{}> with sessionId <{}>", id, sessionId);
        // hiển thị user đang online
//        onlineUser.stream().forEach(user-> System.out.print(user+"\t"));
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = stompAccessor.getSessionId();
//        String id = session_user.get(sessionId);
//        logger.info("Chat connection by user <{}> with sessionId <{}>", id, sessionId);
//        onlineUser.remove(id);
        Websocket_Controller w = new Websocket_Controller();
//        w.disconnect(id); // id user

        // hiển thị user đang online
//        onlineUser.stream().forEach(user-> System.out.print(user+"\t"));
    }
}
