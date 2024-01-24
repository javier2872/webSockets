import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class MiClienteWebSocket extends WebSocketClient {

    public MiClienteWebSocket(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conexión abierta con el servidor: " + handshakedata.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Mensaje recibido del servidor: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexión cerrada. Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("Error en la conexión: " + ex.getMessage());
    }

    public static void main(String[] args) throws URISyntaxException {
        String serverUri = "ws://localhost:8080"; // Cambia la URL del servidor según tu configuración
        MiClienteWebSocket clienteWebSocket = new MiClienteWebSocket(new URI(serverUri));
        clienteWebSocket.connect();

        // Enviar mensajes desde el cliente
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Mensaje para enviar al servidor (o 'exit' para salir): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                clienteWebSocket.close();
                break;
            } else {
                clienteWebSocket.send(input);
            }
        }
    }
}