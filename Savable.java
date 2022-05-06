import java.io.*;
import java.util.List;

public class Savable implements Serializable {
    String filename;

    public Savable(String filename) {
        this.filename = filename;
    }

    public Object load() {
        Object obj = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream( new FileInputStream(this.filename) );
            obj = inputStream.readObject();
        } catch (Exception e) {
            //
        }
        return obj;
    }

    public void save() {
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream( new FileOutputStream(this.filename) );
            outputStream.writeObject(this);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
