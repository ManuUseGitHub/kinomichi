package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.ParticipantType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import store.luniversdemm.common.Saisir;

class LoadParticipantsMSTest {
    @Test
    void itIsPossibleToLoadAParticipantFromLog(){
        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("people.kino");

            Participants pps = new Participants();
            Promptor.setStateEngine(StateEngine.getInstance());

            new LoadParticipantsMS(pps, States.PEOPLE_LOADING_B.getValue())
                    .onLoadRequest();

            Participant p = pps.getItemById(1);
            Assertions.assertEquals(ParticipantType.TEACHER, p.getType());
            Assertions.assertEquals("Luc", p.getFirstname());
            Assertions.assertEquals("Pitance", p.getLastname());
            Assertions.assertEquals("luc.pitence@gmail.com", p.getEmail());
            Assertions.assertEquals("Eken Dojo", p.getClub());
            Assertions.assertEquals("3e dan", p.getGrade());
            Assertions.assertEquals("+32495667331", p.getTelephone());
        }
    }
}