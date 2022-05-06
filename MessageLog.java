import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageLog extends Savable {
    List<Message> messages;

    public MessageLog(String filename) {
        super(filename);
        this.loadSavedInstance();
    }

    public synchronized void addMessage(Message message) {
        this.messages.add(message);
        this.save();
    }

    public synchronized List<Message> getMessages() {
        return messages;
    }

    public void loadSavedInstance() {
        MessageLog savedInstance = (MessageLog)this.load();

        if (savedInstance == null) {
            this.messages = new ArrayList<>();
            return ;
        }

        this.messages = savedInstance.messages;
    }

}
