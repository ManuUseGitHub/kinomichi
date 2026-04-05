package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.States;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import store.luniversdemm.common.Saisir;

import java.util.concurrent.atomic.AtomicReference;

import static be.technifutur.kinomichi.StateEngineHelper.navigateFromHome;
import static org.junit.jupiter.api.Assertions.*;
import static store.luniversdemm.common.Utils.onReadTextFile;

class EditPlageMSTest {


    @BeforeEach
    public void tearUp(){
        navigateFromHome();
    }

    @Test
    void encodingAllTheOptionsLeadtoSuccess(){
        PlageVersionManagerService pvms = new PlageVersionManagerService();

        AtomicReference<TimeTables> result = new AtomicReference<>(new TimeTables());
        onReadTextFile((textContent) -> {
            result.set(pvms.loadByTextSource(textContent));
        });

        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("1")
                    .thenReturn("1 2 3 4")
                    .thenReturn("Thousand sunny go")
                    .thenReturn("This is a boat in one piece that can fly!")
                    .thenReturn("Monkey D. Luffy")
                    .thenReturn("01/01/2027  10:15  50");

            TimeTables tts = result.get();
            Promptor.setStateEngine(StateEngine.getInstance());

            new EditPlageMS(tts, States.PLAGE_EDIT.getValue())
                    .onEditPlage(Event.createNavEvent(this));

            TimeTable changed = tts.getItemById(1);
            assertEquals("Monkey D. Luffy",changed.getAnimator());
            assertEquals("This is a boat in one piece that can fly!",changed.getDescription());
            assertEquals("Thousand sunny go",changed.getActivity());
            assertEquals("2027-01-01",changed.getDate().toString());
            assertEquals("10:15",changed.getStart().toString());
            assertEquals("11:05",changed.getEnd().toString());
        }
    }

    @Test
    void encodingFewOptionsShouldLeadtoSuccess(){
        PlageVersionManagerService pvms = new PlageVersionManagerService();

        AtomicReference<TimeTables> result = new AtomicReference<>(new TimeTables());
        onReadTextFile((textContent) -> {
            result.set(pvms.loadByTextSource(textContent));
        });

        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                    .thenReturn("1 2 3 4")
                    .thenReturn("1")
                    .thenReturn("Thousand sunny go")
                    .thenReturn("2")
                    .thenReturn("This is a boat in one piece that can fly!")
                    .thenReturn("3")
                    .thenReturn("Monkey D. Luffy")
                    .thenReturn("4")
                    .thenReturn("01/01/2027  10:15  50");

            TimeTables tts = result.get();
            Promptor.setStateEngine(StateEngine.getInstance());

            new EditPlageMS(tts, States.PLAGE_EDIT.getValue())
                    .onEditPlage(Event.createNavEvent(this));

            TimeTable changed = tts.getItemById(1);
            assertEquals("Thousand sunny go",changed.getActivity());

            TimeTable second = tts.getItemById(2);
            assertEquals("This is a boat in one piece that can fly!",second.getDescription());

            TimeTable third = tts.getItemById(3);
            assertEquals("Monkey D. Luffy",third.getAnimator());

            TimeTable fourth = tts.getItemById(4);
            assertEquals("2027-01-01",fourth.getDate().toString());
            assertEquals("10:15",fourth.getStart().toString());
            assertEquals("11:05",fourth.getEnd().toString());
        }
    }
}