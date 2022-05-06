import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Chat {
    Socket connection;
    User me, friend;
    MessageLog log;

    public Chat(User me, User friend, MessageLog log) {
        this.me = me;
        this.friend = friend;
        this.log = log;

        this.connection = createConnection(friend);
    }

    private Socket createConnection(User friend) {
        Socket connection = null;
        try {
            connection = new Socket(friend.getAddressHost(), friend.getAddressPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void send(String text) {
        Message message = new Message(me, friend, text);

        addToLog(message);
        sendToFriend(message);
    }

    private void addToLog(Message message) {
        this.log.addMessage(message);
    }

    private void sendToFriend(Message message) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream( connection.getOutputStream() );
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();

        for (Message message: log.getMessages()) {
            if (
                message.from.address.equals(friend.address) ||
                message.to.address.equals(friend.address)
            ) {
                messages.add(message);
            }
        }

        return messages;
    }
}
