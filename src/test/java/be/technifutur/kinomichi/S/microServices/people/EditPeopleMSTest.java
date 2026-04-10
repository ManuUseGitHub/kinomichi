package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.ParticipantVersionManagerService;
import be.technifutur.kinomichi.S.microServices.plages.EditPlageMS;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.States;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import store.luniversdemm.common.Saisir;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static store.luniversdemm.common.Utils.onReadTextFile;

class EditPeopleMSTest {
    @Test
    void encodingAllTheOptionsLeadtoSuccess(){
        ParticipantVersionManagerService pvms = new ParticipantVersionManagerService();

        AtomicReference<Participants> result = new AtomicReference<>(new Participants());
        onReadTextFile("people.kino",(textContent) -> {
            result.set(pvms.loadByTextSource(textContent,(s)->{}));
        });

        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("1")
                    .thenReturn("1234567")
                    .thenReturn("1")
                    .thenReturn("Monkey D")
                    .thenReturn("Luffy")
                    .thenReturn("sunny.go@gmail.com")
                    .thenReturn("+3280771138")
                    .thenReturn("Mugiwara")
                    .thenReturn("Yonkō");

            Participants pps = result.get();
            Promptor.setStateEngine(StateEngine.getInstance());

            new EditPeopleMS(pps, States.PEOPLE_EDIT.getValue())
                    .onEditPlage(Event.createNavEvent(this));

            Participant changed = pps.getItemById(1);
            assertEquals("Visiteur",changed.getType().getName());
            assertEquals("Monkey D",changed.getLastname());
            assertEquals("Luffy",changed.getFirstname());
            assertEquals("sunny.go@gmail.com",changed.getEmail());
            assertEquals("+3280771138",changed.getTelephone());
            assertEquals("Mugiwara",changed.getClub());
            assertEquals("Yonkō",changed.getGrade());
        }
    }
}