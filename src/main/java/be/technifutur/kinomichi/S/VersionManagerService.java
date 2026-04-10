package be.technifutur.kinomichi.S;

import be.technifutur.kinomichicommon.interfaces.VersionLoadable;
import be.technifutur.kinomichicommon.interfaces.VersionSavable;

import java.io.*;
import java.util.function.Consumer;

public abstract class VersionManagerService<E>
        implements VersionSavable<E>, VersionLoadable<E> {
    @Override
    public void save(E tts, String filename, Consumer<Boolean> cbComplete) {
        // Serialization
        try (FileOutputStream file = new FileOutputStream(filename)){
            try(ObjectOutputStream out = new ObjectOutputStream(file)){
                out.writeObject(tts);
                System.out.println("Object has been serialized");
                cbComplete.accept(true);
            }
        } catch (IOException ex) {
            System.out.println(ex);
            cbComplete.accept(false);
        }
    }

    @Override
    public E load(String filename, Consumer<Boolean> cbComplete) {
        // Serialization
        E object1 = null;

        try(FileInputStream file = new FileInputStream(filename)) {
            try(ObjectInputStream in = new ObjectInputStream(file)){
                object1 = (E) in.readObject();
                cbComplete.accept(true);
            }
        } catch (IOException ex) {
            System.out.println(ex);
            cbComplete.accept(false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return object1;
    }

    public void save(E tts, String filename) {
        save(tts,filename,ignore->{});
    }
    public E load(String filename) {
        return load(filename,ignore->{});
    }
}
