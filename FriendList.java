import java.util.ArrayList;
import java.util.List;

public class FriendList extends Savable {
    List<User> friends;

    public FriendList(String filename) {
        super(filename);
        this.loadSavedInstance();
    }

    public void addFriend(String name, String address) {
        this.addFriend( new User(name, address) );
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
        this.save();
    }

    public User findByAddress(String address) {
        for (User friend: friends) {
            if (friend.address.equals(address)) {
                return friend;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User friend: friends) {
            if (friend.name.equals(name)) {
                return friend;
            }
        }
        return null;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void loadSavedInstance() {
        FriendList savedInstance = (FriendList)this.load();

        if (savedInstance == null) {
            this.friends = new ArrayList<>();
            return ;
        }

        this.friends = savedInstance.friends;
    }

}
