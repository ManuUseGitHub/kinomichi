package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.DateAndTimeUtils;
import store.luniversdemm.common.Saisir;

import java.util.concurrent.atomic.AtomicReference;

import static be.technifutur.kinomichi.V.Promptor.selectItems;
import static store.luniversdemm.common.Utils.onMatch;

public class EditPeopleMS extends MicroService implements MicroServiable {
    private final Participants tts;

    public EditPeopleMS(Participants tts, String event) {
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            onEditPlage(event);
            EventBus.publishEvent(Event.Topic.NAVIGATION.name(), Event.createBackNavEvent(this));
            EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
        };
    }

    public void onEditPlage(Event event) {
        selectItems(tts,"Quelle entrée voulez-vous changer? (plusieurs choix possibles)").forEach(id -> {
            Participant selected = tts.getItemById(Integer.parseInt(id));
            if (selected != null) {
                Promptor.displayMenu();
                Promptor.displayModifyingItem(selected);
                String choices = Saisir.scanString();

                AtomicReference<Participant.Builder> built = new AtomicReference<>(
                        selected.pastyCat(new Participant.Builder(selected.getType()))
                );

                onMatch("(?<type>1)?\\s*(?<lastname>2)?\\s*(?<firstName>3)?\\s*(?<email>4)?\\s*(?<telephone>5)?\\s*(?<club>6)?\\s*(?<grade>7)?", choices, m -> {

                    if (m.group("type") != null) {
                        Promptor.displayParticipantsTypesProposition();
                        insertType(built.get(),selected.getType());
                    }
                    if (m.group("lastname") != null) {
                        insertLastname(built.get(), selected.getLastname());
                    }
                    if (m.group("firstName") != null) {
                        insertFirstname(built.get(), selected.getFirstname());
                    }
                    if (m.group("email") != null) {
                        insertEmail(built.get(), selected.getEmail());
                    }
                    if (m.group("telephone") != null) {
                        insertTelephone(built.get(),selected.getTelephone());
                    }
                    if (m.group("club") != null) {
                        insertClub(built.get(),selected.getClub());
                    }
                    if (m.group("grade") != null) {
                        insertGrade(built.get(),selected.getGrade());
                    }
                });

                tts.changeItem(built.get(), selected);
            }
        });
    }

    private void insertType(Participant.Builder built, ParticipantType type) {
        System.out.print("[TYPE], Valeur actuelle :" + ConsoleColors.RED + type.getName() + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.type(ParticipantType.get(input));
        }
    }

    private void insertLastname(Participant.Builder built, String lastname) {
        System.out.print("[NOM], Valeur actuelle :" + ConsoleColors.RED + lastname + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.lastname(input);
        }
    }

    private void insertFirstname(Participant.Builder built, String firstname) {
        System.out.print("[PRENOM], Valeur actuelle :" + ConsoleColors.RED + firstname + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.firstname(input);
        }
    }

    private void insertEmail(Participant.Builder built, String email) {
        System.out.print("[EMAIL], Valeur actuelle :" + ConsoleColors.RED + email + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.email(input);
        }
    }

    private void insertTelephone(Participant.Builder built, String telephone) {
        System.out.print("[TELEPHONE], Valeur actuelle :" + ConsoleColors.RED + telephone + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.telephone(input);
        }
    }

    private void insertClub(Participant.Builder built, String club) {
        System.out.print("[Club], Valeur actuelle :" + ConsoleColors.RED + club + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.club(input);
        }
    }

    private void insertGrade(Participant.Builder built, String grade) {
        System.out.print("[GRADE], Valeur actuelle :" + ConsoleColors.RED + grade + ConsoleColors.RESET);
        System.out.println(" (laissez vide pour garder) ");
        String input = Saisir.scanString();
        if(!input.isEmpty()){
            built.grade(input);
        }
    }
}