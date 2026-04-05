package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.C.StateEngine;
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

class DeletePlageMSTest {
    @BeforeEach
    public void tearUp(){
        navigateFromHome();
    }

    @Test
    void deletingRemovesElementsFromResult(){
        PlageVersionManagerService pvms = new PlageVersionManagerService();

        AtomicReference<TimeTables> result = new AtomicReference<>(new TimeTables());
        onReadTextFile((textContent) -> {
            result.set(pvms.loadByTextSource(textContent));
        });

        TimeTables tts = result.get();
        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString)
                                        .thenReturn("1 3");


            Promptor.setStateEngine(StateEngine.getInstance());

            new DeletePlageMS(tts, States.PLAGE_DELETING.getValue())
                    .onDeletePlage(Event.createNavEvent(this));

        }
        assertNull(tts.getItemById(1));
        assertNull(tts.getItemById(3));
    }
}