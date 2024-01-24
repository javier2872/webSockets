import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class MiServidorWebSocket extends WebSocketServer {

    public MiServidorWebSocket(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Nueva conexión: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Conexión cerrada: " + conn.getRemoteSocketAddress() + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Mensaje recibido de " + conn.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error en la conexión de " + conn.getRemoteSocketAddress() + ": " + ex.getMessage());
    }
    @Override
    public void onStart() {

    }

    public static void main(String[] args) {
        int puerto = 8080; // Puerto en el que el servidor WebSocket estará escuchando
        MiServidorWebSocket servidorWebSocket = new MiServidorWebSocket(puerto);
        servidorWebSocket.start();
        System.out.println("Servidor WebSocket en ejecución en el puerto " + puerto);
    }

}