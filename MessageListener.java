import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageListener extends Thread {
    BackgroundServer server;
    Socket connection;

    public MessageListener(BackgroundServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
    }

    public void run() {
        Message message = this.waitForMessage();
        this.server.handleNewMessage( message );
    }

    private Message waitForMessage() {
        Message message = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream( this.connection.getInputStream() );
            message = (Message) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }


}
