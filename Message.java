import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class Message implements Serializable {
    User from, to;
    Timestamp createdAt;
    String text;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.createdAt = getCurrentTime();
    }

    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        String formattedTime = new SimpleDateFormat("d MMM hh:mm a").format(createdAt);
        return String.format("<html>%s - %s<br />> %s</html>", from.name, formattedTime, text);
    }
}
