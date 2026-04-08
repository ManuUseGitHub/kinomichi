package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.ParticipantType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import store.luniversdemm.common.Saisir;

import java.util.List;

import static be.technifutur.kinomichi.StateEngineHelper.navigateFromHome;

class AddPeopleMSTest {

    public static List<Arguments> pickAndExpects(){
        return List.of(
                Arguments.of(1, ParticipantType.VISITOR),
                Arguments.of(2, ParticipantType.TEACHER),
                Arguments.of(3, ParticipantType.MEMBER),
                Arguments.of(4, ParticipantType.KID_MEMBER),
                Arguments.of(5, ParticipantType.KID),
                Arguments.of(6, ParticipantType.VIP_KID),
                Arguments.of(7, ParticipantType.VIP)
        );
    }

    @BeforeEach
    public void tearUp(){
        navigateFromHome();
    }

    @Test
    void itIsPossibleToAddATypeOfParticipant(){

        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("2")
                    .thenReturn("Pitance")
                    .thenReturn("Luc")
                    .thenReturn("luc.pitence@gmail.com")
                    .thenReturn("+32495667331")
                    .thenReturn("Eken Dojo")
                    .thenReturn("3e dan");

            Participants pps = new Participants();
            Promptor.setStateEngine(StateEngine.getInstance());

            new AddPeopleMS(pps, States.PEOPLE_ADDING.getValue())
                    .onAddParticipant();

            Participant p = pps.getItemById(1);
            Assertions.assertEquals(ParticipantType.TEACHER, p.getType());
            Assertions.assertEquals("Pitance", p.getLastname());
            Assertions.assertEquals("Luc", p.getFirstname());
            Assertions.assertEquals("luc.pitence@gmail.com", p.getEmail());
            Assertions.assertEquals("+32495667331", p.getTelephone());
            Assertions.assertEquals("Eken Dojo", p.getClub());
            Assertions.assertEquals("3e dan", p.getGrade());
        }
    }

    @Test
    void itIsPossibleToCancelTheSelectionOfATypeOfParticipant(){

        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("");

            Participants pps = new Participants();
            Promptor.setStateEngine(StateEngine.getInstance());

            new AddPeopleMS(pps, States.PEOPLE_ADDING.getValue())
                    .onAddParticipant();
        }
    }

    @ParameterizedTest
    @MethodSource("pickAndExpects")
    void itIsPossibleToSetAnyTypeOfParticipant(int picked, ParticipantType type){
        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn(String.valueOf(picked));

            Participants pps = new Participants();
            Promptor.setStateEngine(StateEngine.getInstance());

            new AddPeopleMS(pps, States.PEOPLE_ADDING.getValue())
                    .onAddParticipant();

            Participant p = pps.getItemById(1);
            Assertions.assertEquals(type,p.getType());
        }
    }
}