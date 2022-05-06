import javax.swing.*;
import java.awt.*;

public class MessageBoxUI extends JPanel implements ComponentUI {
    Application application;
    JLabel messageTitle;
    DefaultListModel<String> messageModel;

    public MessageBoxUI(Application application) {
        this.application = application;
        this.setup();
        this.update();
    }

    private void setup() {
        this.setLayout(new BorderLayout());

        this.messageTitle = new JLabel();
        this.add(this.messageTitle, BorderLayout.NORTH);

        this.messageModel = new DefaultListModel<>();
        this.add(new JScrollPane( new JList(this.messageModel)) , BorderLayout.CENTER);
    }

    @Override
    public void update() {
        this.messageModel.clear();

        if (this.application.getCurrentChat() != null) {
            this.messageTitle.setText(this.application.getCurrentChat().friend.name);
            for (Message msg: this.application.getCurrentChat().getMessages()) {
                this.messageModel.addElement(msg.toString());
            }
        }
    }
}
