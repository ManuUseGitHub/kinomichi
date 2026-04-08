package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.S.ParticipantVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import static be.technifutur.kinomichi.S.LoadingVersionService.processLoading;

public class LoadParticipantsMS extends MicroService implements MicroServiable {
    private final Participants pps;
    private final ParticipantVersionManagerService pvms;

    public LoadParticipantsMS(Participants pps, String... events) {
        super(events);
        this.pps = pps;
        this.pvms = new ParticipantVersionManagerService();
    }

    public IEventListener handle() {
        return event -> {
            onLoadRequest();
        };
    }

    public void onLoadRequest() {
        Promptor.displayMenu();

        System.out.print("Entrez le chemin vers un fichier");
        System.out.println(" (laissez vide pour annuler...) ");

        String fileName = Saisir.scanString();

        if (!fileName.isEmpty()) {
            processLoading(pps, pvms, fileName);
        }

        EventBus.publishEvent(Event.Topic.NAVIGATION.name(), Event.createBackNavEvent(this));
        EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
    }
}
