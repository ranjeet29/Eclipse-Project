

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by praveenb on 8/25/2017.
 */
public class MyWebSocketHandler implements WebSocketHandler {
    public String message = "";

    private void setMessage(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
    // System.out.print("x");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        setMessage(webSocketMessage.getPayload().toString());
        System.out.print(webSocketMessage.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.print("Trasport error");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
       // System.out.print("connection closed");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
