package utils;

import human.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteAndRead implements SavingAndLoading {
    private final String path = "src/utils/human.txt";

    @Override
    public boolean save(List<Human> humans) {
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(path));
            objectOutputStream.writeObject(humans);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Human> read() {
        List<Human> humans;
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(path));
            humans = (ArrayList<Human>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return humans;
    }
}
