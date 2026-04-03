package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.TimeTables;

import java.io.*;

public class PlageVersionManagerService {

    public void save(TimeTables tts,String filename) {
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

    public TimeTables load(String filename) {
        // Serialization
        TimeTables object1 = null;

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object1 = (TimeTables) in.readObject();
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
