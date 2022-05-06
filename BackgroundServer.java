import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundServer extends Thread {
    Application application;
    ServerSocket serverSocket;
    List<MessageListener> listeners;

    public BackgroundServer(Application application) {
        this.application = application;
        this.listeners = new ArrayList<>();
        this.serverSocket = this.createServerSocket();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            Socket connection = this.waitForNewConnection();
            this.addListenerForConnection( connection );
        }
    }

    private Socket waitForNewConnection() {
        Socket connection = null;
        try {
            connection = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private ServerSocket createServerSocket() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(1234);
        } catch (IOException e) {
            try {
                socket = new ServerSocket(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return socket;
    }

    private void addListenerForConnection(Socket connection) {
        MessageListener listener = new MessageListener( this, connection );
        this.listeners.add(listener);
        listener.start();
    }

    public void handleNewMessage(Message message) {
        addSenderToFriendList(message);
        addMessageToLog(message);
    }

    private void addMessageToLog(Message message) {
        application.getMessageLog().addMessage(message);
    }

    private void addSenderToFriendList(Message message) {
        User messageSender = message.from;
        FriendList friendList = application.getFriendList();

        if (friendList.findByAddress( messageSender.address ) == null) { // Add to friend list if not already added
            friendList.addFriend(messageSender);
        }
    }

    public String getAddress() {
        String host = "";
        Integer port = 0;
        try {
            host = serverSocket.getInetAddress().getLocalHost().getHostAddress();
            port = serverSocket.getLocalPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String address = String.format("%s:%d", host, port);
        return address;
    }
}
