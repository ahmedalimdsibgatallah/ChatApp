import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    String name, address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddressHost() {
        return address.split(":")[0];
    }

    public int getAddressPort() {
        return Integer.parseInt(address.split(":")[1]);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
