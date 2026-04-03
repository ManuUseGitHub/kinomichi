package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;

import java.io.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.MULTILINE;
import static store.luniversdemm.common.DateAndTimeUtils.durationBetweenTwoTimes;
import static store.luniversdemm.common.Utils.onMatches;

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

    public TimeTables loadByTextSource(String textContent){
        TimeTables tts = new TimeTables();
        onMatches(Pattern.compile(">Tranche horaire n° \\((?<id>\\d+)\\)\\n>-+\\n>Activité : (?<activity>.*)\\n>Description :\\n> (?<description>.*)\\n>Qui anime : (?<formator>.*)\\n>Temporalité -+\\n>(?<date>\\d{1,2}\\/\\d{1,2}\\/\\d{4}) \\| (?<start>\\d{1,2}:\\d{1,2}) -> (?<end>\\d{1,2}:\\d{1,2})",
                        MULTILINE),
                textContent,m -> {
                    tts.addTimeTable(
                            new TimeTable.Builder(m.group("activity"))
                                    .description(m.group("description"))
                                    .start(m.group("start"))
                                    .animator(m.group("formator"))
                                    .duration(durationBetweenTwoTimes(
                                            m.group("end"),
                                            m.group("start"))
                                    )
                                    .date(m.group("date"))
                                    );
                });
        return tts;
    }
}
