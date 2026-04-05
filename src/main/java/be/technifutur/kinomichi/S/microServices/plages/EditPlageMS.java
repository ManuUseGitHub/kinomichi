package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.DateAndTimeUtils;
import store.luniversdemm.common.Saisir;
import java.util.concurrent.atomic.AtomicReference;

import static be.technifutur.kinomichi.V.Promptor.selectTimeTables;
import static store.luniversdemm.common.Utils.onMatch;

public class EditPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public EditPlageMS(TimeTables tts, String event) {
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            onEditPlage(event);
            EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
        };
    }

    public void onEditPlage(Event event) {
        selectTimeTables(tts,"Quelle plage voulez-vous changer? (plusieurs choix possibles)").forEach(id -> {
            TimeTable selected = tts.getItemById(Integer.parseInt(id));
            if (selected != null) {
                Promptor.getMenu();
                Promptor.displayModifyingTimeTable(selected);
                String choices = Saisir.scanString();

                AtomicReference<TimeTable.Builder> built = new AtomicReference<>(
                        selected.pastyCat(new TimeTable.Builder(selected.getActivity()))
                );

                onMatch("(?<activity>1)?\\s*(?<description>2)?\\s*(?<animator>3)?\\s*(?<time>4)?", choices, m -> {

                    if (m.group("activity") != null) {
                        insertNewActivity(built.get(),selected.getActivity());

                    }
                    if (m.group("description") != null) {
                        insertDescription(built.get(), selected.getDescription());
                    }
                    if (m.group("animator") != null) {
                        insertFormator(built.get(), selected.getAnimator());
                    }
                    if (m.group("time") != null) {
                        insertTimes(built.get(), selected);
                    }
                });

                tts.changeItem(built.get(), selected);
            }
        });
    }

    private void insertFormator(TimeTable.Builder built, String name) {
        System.out.print("[FORMATOR], Valeur actuelle :" + ConsoleColors.RED + name + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        built.animator(!input.isEmpty() ? input : name);
    }

    private void insertTimes(TimeTable.Builder built, TimeTable selected) {
        String sDuration = DateAndTimeUtils.durationBetweenTwoTimes(selected.getEnd().toString(), selected.getStart().toString());

        System.out.print("[TEMPORALITES], Valeur actuelle :" + ConsoleColors.RED + temporalityFormated(selected, sDuration) + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        System.out.println("\nEntrez une date , le début et la durée de la plage");
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

        if (input.isEmpty()) {
            built.date(selected.getDate().toString());
            built.start(selected.getStart().toString());
            built.duration(sDuration);
        } else {
            built.date(date.get())
                    .start(start.get())
                    .duration(duration.get());
        }
    }

    private String temporalityFormated(TimeTable selected, String duration) {
        return "%d/%d/%d | %d:%d -> %d:%d (+%s)".formatted(
                selected.getDate().getDayOfMonth(),
                selected.getDate().getMonthValue(),
                selected.getDate().getYear(),

                selected.getStart().getHour(),
                selected.getStart().getMinute(),

                selected.getEnd().getHour(),
                selected.getEnd().getMinute(),

                duration
        );
    }

    private void insertDescription(TimeTable.Builder built, String description) {
        System.out.print("[DESCRIPTION], Valeur actuelle :" + ConsoleColors.RED + description + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        built.description(!input.isEmpty() ? input : description);
    }

    private void insertNewActivity(TimeTable.Builder built, String activity) {
        System.out.print("[ACTIVITÉ], Valeur actuelle :" + ConsoleColors.RED + activity + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        built.activity(!input.isEmpty() ? input : activity);
    }
}