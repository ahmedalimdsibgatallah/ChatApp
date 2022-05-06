import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Application extends JFrame {
    User me;
    Chat currentChat;
    FriendList friendList;
    MessageLog messageLog;
    BackgroundServer backgroundServer;

    ArrayList<ComponentUI> components;

    public Application() {
        super("Chat Application");

        this.messageLog = new MessageLog("message.log");
        this.friendList = new FriendList("friends.log");

        this.backgroundServer = new BackgroundServer(this);

        this.me = createDefaultUser();

        setupGui();
    }

    private void setupGui() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, 600));
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        this.setupComponents();

        this.setVisible(true);
    }

    private void setupComponents() {
        this.components = new ArrayList<>();

        GridBagConstraints c = new GridBagConstraints();


        c.insets.top = 5;
        c.insets.bottom = 5;
        c.insets.left = 5;
        c.insets.right = 5;


        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        this.addComponent( new InfoUI(this), c );

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        this.addComponent( new AddFriendUI(this), c );




        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.VERTICAL;
        this.addComponent( new FriendListUI(this), c );

        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_END;
        this.addComponent( new MessageBoxUI( this ), c );

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.VERTICAL;
        this.addComponent( new AddMessageUI(this), c );
    }

    void addComponent(ComponentUI component, GridBagConstraints constraints) {
        this.components.add(component);
        this.add((Component) component, constraints);
    }

    private User createDefaultUser() {
        String localAddress = backgroundServer.getAddress();
        return new User("John", localAddress);
    }

    public void start() {
        listenForMessages();
        startUpdateTimer();
    }

    private void startUpdateTimer() {
        int updateInterval = 100;
        new Timer(updateInterval, (e) -> this.updateComponents()).start();
    }

    private void updateComponents() {
        for (ComponentUI component: components) {
            component.update();
        }
    }

    private void listenForMessages() {
        this.backgroundServer.start();
    }

    public void addFriend(String name, String address) {
        friendList.addFriend(name, address);
    }

    public void selectChat(User friend) {
        this.currentChat = new Chat(me, friend, messageLog);
    }

    public FriendList getFriendList() {
        return friendList;
    }

    public MessageLog getMessageLog() {
        return messageLog;
    }

    public Chat getCurrentChat() {
        return currentChat;
    }
}
