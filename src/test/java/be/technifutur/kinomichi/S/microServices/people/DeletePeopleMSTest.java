package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.S.ParticipantVersionManagerService;
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

class DeletePeopleMSTest {
    @Test
    void deletingRemovesElementsFromResult(){
        ParticipantVersionManagerService pvms = new ParticipantVersionManagerService();

        AtomicReference<Participants> result = new AtomicReference<>(new Participants());
        onReadTextFile("people.kino",(textContent) -> {
            result.set(pvms.loadByTextSource(textContent));
        });

        Participants pps = result.get();
        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("1 2");


            Promptor.setStateEngine(StateEngine.getInstance());

            assertTrue(2 <= pps.getItems().size());
            new DeletePeopleMS(pps, States.PEOPLE_DELETING.getValue())
                    .onDeletePeople(Event.createNavEvent(this));

        }
        assertNull(pps.getItemById(1));
        assertNull(pps.getItemById(2));
    }
}