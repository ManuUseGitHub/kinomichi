package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.NavigationMS;
import be.technifutur.kinomichi.S.microServices.people.AddPeopleMS;
import be.technifutur.kinomichi.S.microServices.people.ListingPeopleMS;
import be.technifutur.kinomichi.S.microServices.people.LoadParticipantsMS;
import be.technifutur.kinomichi.S.microServices.plages.*;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.States;

public class Services {
    public static void createServices(){
        TimeTables tts = new TimeTables();
        Participants pps = new Participants();

        new NavigationMS(Event.Topic.NAVIGATION.name());
        new StarterMS(tts, States.MAIN_MENU.getValue());
        new AddPlageMS(tts, States.PLAGE_ADDING.getValue());
        new DeletePlageMS(tts, States.PLAGE_DELETING.getValue());
        new ListingPlageMS(tts, States.PLAGE_LISTING.getValue());
        new SavePlageMS(tts, States.PLAGE_SAVING.getValue());
        new EditPlageMS(tts, States.PLAGE_EDIT.getValue());

        new AddPeopleMS(pps, States.PEOPLE_ADDING.getValue());
        new ListingPeopleMS(pps, States.PEOPLE_LISTING.getValue());

        new LoadPlageMS(tts,
                States.PLAGE_LOADING_A.getValue(),
                States.PLAGE_LOADING_B.getValue());

        new LoadParticipantsMS(pps,
            States.PEOPLE_LOADING_A.getValue(),
                States.PEOPLE_LOADING_B.getValue()
        );
    }
}
