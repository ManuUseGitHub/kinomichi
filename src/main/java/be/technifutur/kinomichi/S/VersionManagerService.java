package be.technifutur.kinomichi.S;

import be.technifutur.kinomichicommon.interfaces.VersionLoadable;
import be.technifutur.kinomichicommon.interfaces.VersionSavable;

import java.io.*;

public abstract class VersionManagerService<E>
        implements VersionSavable<E>, VersionLoadable<E> {
    @Override
    public void save(E tts, String filename) {
        // Serialization
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tts);
            out.close();
            file.close();
            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public E load(String filename) {
        // Serialization
        E object1 = null;

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object1 = (E) in.readObject();
            in.close();
            file.close();

        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return object1;
    }
}
