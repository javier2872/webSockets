import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URI;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MiClienteWebSocket.class})
public class MiClienteWebSocketPowerMockTest {
    @Mock
    private WebSocketClient mockClient;

    @Test
    public void testWebSocketClient() throws Exception {
        // Mock de la clase WebSocketClient
        // Mock de la clase WebSocketServer
        MiClienteWebSocket mockClient = Mockito.mock(MiClienteWebSocket.class);
        PowerMockito.whenNew(MiClienteWebSocket.class).withAnyArguments().thenReturn(mockClient);

        // Crea una instancia del cliente
        URI serverUri = new URI("ws://localhost:8080"); // Cambia la URL del servidor según tu configuración
        MiClienteWebSocket clienteWebSocket = new MiClienteWebSocket(serverUri);

        // Simula la apertura de la conexión
        clienteWebSocket.onOpen(Mockito.mock(ServerHandshake.class));
        Mockito.verify(mockClient, Mockito.times(1))
                .onOpen(Mockito.any(ServerHandshake.class));

        // Simula un mensaje recibido
        clienteWebSocket.onMessage("Mensaje de prueba");
        Mockito.verify(mockClient, Mockito.times(1))
                .onMessage(Mockito.anyString());

        // Simula el cierre de la conexión
        clienteWebSocket.onClose(1000, "Cierre normal", true);
        Mockito.verify(mockClient, Mockito.times(1))
                .onClose(Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }




}
