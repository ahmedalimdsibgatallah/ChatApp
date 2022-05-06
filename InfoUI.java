import javax.swing.*;
import java.awt.*;

public class InfoUI extends JPanel implements ComponentUI {
    Application application;
    JLabel nameLabel, addressLabel;

    public InfoUI(Application application) {
        this.application = application;
        this.setup();
        this.update();
    }

    private void setup() {
        this.setLayout(new BorderLayout());

        this.nameLabel = new JLabel();
        this.add(this.nameLabel, BorderLayout.NORTH);

        this.addressLabel = new JLabel();
        this.add(this.addressLabel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        User me = this.application.me;
        this.nameLabel.setText(String.format(" User: %s", me.name));
        this.addressLabel.setText(String.format(" Address: %s", me.address));
    }
}
