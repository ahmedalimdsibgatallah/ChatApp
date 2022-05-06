import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendUI extends JPanel implements ActionListener, ComponentUI{
    Application application;
    JButton addFriendButton;
    AddFriendDialog addFriendDialog;

    public AddFriendUI(Application application) {
        this.application = application;
        this.setup();
    }

    private void setup() {
        this.addFriendDialog = new AddFriendDialog(this.application);
        this.addFriendButton = new JButton("Add Friend");
        this.addFriendButton.addActionListener(this);

        this.add(this.addFriendButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.addFriendDialog.setVisible(true);
    }

    @Override
    public void update() {

    }

    class AddFriendDialog extends JDialog implements ActionListener {
        Application application;

        JButton addButton;
        JTextField nameField, addressField;

        public AddFriendDialog(Application application) {
            this.application = application;
            this.setup();
        }

        private void setup() {
            nameField = new JTextField(16);
            addressField = new JTextField(16);
            addButton = new JButton("Add");

            this.setLayout(new FlowLayout());
            this.add(new JLabel("Name"));
            this.add(nameField);
            this.add(new JLabel("Address"));
            this.add(addressField);
            this.add(addButton);
            this.pack();

            addButton.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String name = nameField.getText();
            String address = addressField.getText();

            this.application.addFriend(name, address);

            this.nameField.setText("");
            this.addressField.setText("");
            this.setVisible(false);
        }
    }
}

