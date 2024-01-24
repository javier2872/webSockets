import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MiServidorWebSocket.class})
public class MiServidorWebSocketPowerMockTest {

    @Mock
    private WebSocket mockWebSocket;

    @Test
    public void testWebSocketServer() throws Exception {
        // Mock de la clase WebSocketServer
        MiServidorWebSocket mockServer = Mockito.mock(MiServidorWebSocket.class);
        PowerMockito.whenNew(MiServidorWebSocket.class).withAnyArguments().thenReturn(mockServer);

        // Crea una instancia del servidor
        MiServidorWebSocket servidorWebSocket = new MiServidorWebSocket(8080);

        // Simula la apertura de la conexión
        servidorWebSocket.onOpen(mockWebSocket, Mockito.mock(ClientHandshake.class));
        Mockito.verify(mockServer, Mockito.times(1))
                .onOpen(Mockito.<WebSocket>any(), Mockito.<ClientHandshake>any());

        // Simula un mensaje recibido
        servidorWebSocket.onMessage(mockWebSocket, "Mensaje de prueba");
        Mockito.verify(mockServer, Mockito.times(1))
                .onMessage(Mockito.<WebSocket>any(), Mockito.anyString());

        // Simula el cierre de la conexión
        servidorWebSocket.onClose(mockWebSocket, 1000, "Cierre normal", true);
        Mockito.verify(mockServer, Mockito.times(1))
                .onClose(Mockito.<WebSocket>any(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }
}