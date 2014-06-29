package model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Tiago
 */
public class WebSocketCliente {

    private WebSocketClient socket;
    private boolean isOpen = false;
    private static WebSocketCliente INSTANCE = new WebSocketCliente();;

    public static WebSocketCliente getInstance() {        
        return INSTANCE;
    }

    public WebSocketCliente() {
        try {
            initialize();
        } catch (URISyntaxException ex) {
            Logger.getLogger(WebSocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String message) {
        if (isOpen) {
            socket.send(message);
        } 
    }

    private void initialize() throws URISyntaxException {
        URI uri = new URI("ws://localhost/endpoint");
        socket = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake sh) {
                isOpen = true;
            }

            @Override
            public void onMessage(String string) {
            }

            @Override
            public void onClose(int i, String string, boolean bln) {
                isOpen = false;
            }

            @Override
            public void onError(Exception excptn) {
                isOpen = false;
            }
        };
        socket.connect();
    }
}