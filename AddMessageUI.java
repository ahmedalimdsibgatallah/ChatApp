import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMessageUI extends JPanel implements ActionListener, ComponentUI{
    Application application;

    JTextField messageInput;
    JButton sendButton;

    public AddMessageUI(Application application) {
        this.application = application;
        this.setup();
    }

    private void setup() {
        this.sendButton = new JButton("Send");
        this.messageInput = new JTextField(84);

        this.sendButton.addActionListener(this);

        this.add(this.messageInput);
        this.add(this.sendButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String messageText = this.messageInput.getText();
        this.application.getCurrentChat().send(messageText);
        this.messageInput.setText("");
    }

    @Override
    public void update() {

    }
}
