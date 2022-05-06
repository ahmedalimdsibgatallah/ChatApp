import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class FriendListUI extends JPanel implements ComponentUI, ListSelectionListener {
    Application application;
    DefaultListModel<User> friendModel;
    JList friendModelList;


    public FriendListUI(Application application) {
        this.application = application;
        this.setup();
        this.update();
    }

    private void setup() {
        this.setLayout(new BorderLayout());
        this.friendModel = new DefaultListModel<>();
        this.friendModelList = new JList(this.friendModel);
        this.friendModelList.setMinimumSize(new Dimension(500, 500));
        this.add(new JScrollPane(friendModelList), BorderLayout.LINE_START);
    }

    @Override
    public void update() {
        this.friendModelList.removeListSelectionListener(this);
        this.friendModel.clear();
        for(User user: application.getFriendList().getFriends()) {
            this.friendModel.addElement(user);
        }

        this.friendModelList.addListSelectionListener(this);
    }


    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedIndex = event.getFirstIndex();
            User friend = application.getFriendList().getFriends().get(selectedIndex);
            this.application.selectChat(friend);
        }
    }
}
