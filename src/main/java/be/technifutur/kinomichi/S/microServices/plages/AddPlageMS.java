package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import java.util.concurrent.atomic.AtomicReference;

import static store.luniversdemm.common.Utils.onMatch;

public class AddPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public AddPlageMS(TimeTables tts, String event) {
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            EventBus.publishEvent("DOING:ACTIVITY", Event.createAddEvent(this));
            Promptor.getMenu();
            TimeTable.Builder built = insertNewActivity();

            insertDescription(built);
            insertTimes(built);
            insertFormator(built);

            tts.addTimeTable(built);

            EventBus.publishEvent("FINISH:ACTIVITY", Event.createAddEvent(this));
        };
    }

    private void insertFormator(TimeTable.Builder built) {
        System.out.println("Entrez le nom du formateur");
        String formator = Saisir.scanString();
        built.animator(formator);
    }

    private void insertTimes(TimeTable.Builder built) {
        System.out.println("Entrez une date , le début et la durée de la plage");
        System.out.println("ex 01/01/1993   10:15   30 (temps ou minutes)");
        System.out.print("> ");
        AtomicReference<String> date = new AtomicReference<>("");
        AtomicReference<String> start = new AtomicReference<>("");
        AtomicReference<String> duration = new AtomicReference<>("");

        String input = Saisir.scanString();
        onMatch("(?<date>\\d{2}\\/\\d{2}\\/\\d{4})\\s*(?<start>\\d{2}:\\d{2})\\s*(?<duration>(?:\\d{1,2}:)?\\d+)", input, m -> {
            date.set(m.group("date"));
            start.set(m.group("start"));
            duration.set(m.group("duration"));
        });

        built.date(date.get())
                .start(start.get())
                .duration(duration.get());
    }

    private void insertDescription(TimeTable.Builder built) {
        System.out.print("description :");
        String description = Saisir.scanString();
        built.description(description);
    }

    private TimeTable.Builder insertNewActivity() {
        System.out.print("Entrez ces infos\nactivité :");
        String activite = Saisir.scanString();
        return new TimeTable.Builder(activite);
    }
}
