package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.S.NavigatorUtils;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

public class AddPeopleMS extends MicroService implements MicroServiable {
    private final Participants pps;

    public AddPeopleMS(Participants pps, String event) {
        super(event);
        this.pps = pps;
    }

    public IEventListener handle() {
        return event -> {
            onAddParticipant();
        };
    }

    public void onAddParticipant() {
        EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createLockEvent(this));
        Promptor.displayMenu();
        Participant.Builder built = insertNewParticipantType();

        if (built != null) {
            insertNames(built);
            insertContactInfo(built);
            insertClubAndGrade(built);
            pps.addItem(built);
        }

        NavigatorUtils.getBackOnSuccess(this);
    }

    private void insertClubAndGrade(Participant.Builder built) {
        System.out.println("- Pratiquant -");
        System.out.print("Club :");
        String club = Saisir.scanString();
        built.club(club);

        System.out.print("Grade :");
        String grade = Saisir.scanString();
        built.grade(grade);
    }

    private void insertContactInfo(Participant.Builder built) {
        System.out.println("- Contact -");
        System.out.print("Email :");
        String email = Saisir.scanString();
        built.email(email);

        System.out.print("Tel :");
        String telephone = Saisir.scanString();
        built.telephone(telephone);
    }

    private void insertNames(Participant.Builder built) {
        System.out.println("- Nom et prénom -");
        String lastName = "";
        while (lastName.isEmpty()) {
            System.out.print("* Nom :");
            lastName = Saisir.scanString();
        }
        built.lastname(lastName);

        String firstName = Saisir.scanString();
        while (firstName.isEmpty()) {
            System.out.print("* Prénom :");
            firstName = Saisir.scanString();
        }
        built.firstname(firstName);
    }

    private Participant.Builder insertNewParticipantType() {
        System.out.println("Entrez le type du participant");
        Promptor.displayParticipantsTypesProposition();
        System.out.print("type :");
        String input = Saisir.scanString();
        
        ParticipantType selectedType = null;
        ParticipantType[] types = ParticipantType.values();

        if (input.matches("^\\d+$")) {
            selectedType = types[Integer.parseInt(input) - 1];
        }

        while (!input.isEmpty() && selectedType == null) {
            String input2 = Saisir.scanString();
            if (!input2.matches("^\\d+$")) {
                System.err.print("You have to pick a number");
                System.out.println("Or enter nothing to cancel");
            } else {
                int picked = Integer.parseInt(input2) - 1;
                if (!(0 <= picked && picked < types.length)) {
                    System.err.println("You have to pick a valid number");
                } else {
                    selectedType = types[picked];
                }
            }
        }
        
        return selectedType != null ? new Participant.Builder(selectedType) : null;
    }
}
