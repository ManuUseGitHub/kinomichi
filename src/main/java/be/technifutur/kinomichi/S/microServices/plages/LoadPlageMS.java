package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import static be.technifutur.kinomichi.S.LoadingVersionService.processLoading;
import static be.technifutur.kinomichi.S.NavigatorUtils.getBackOnSuccess;

public class LoadPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;
    private final PlageVersionManagerService pvms;

    public LoadPlageMS(TimeTables tts, String... events) {
        super(events);
        this.tts = tts;
        this.pvms = new PlageVersionManagerService();
    }

    public IEventListener handle() {
        return event -> {
            Promptor.displayMenu();

            System.out.print("Entrez le chemin vers un fichier");
            System.out.println(" (laissez vide pour annuler...) ");
            String fileName = Saisir.scanString();

            if (!fileName.isEmpty()) {
                processLoading(tts, pvms, fileName);
            }
            getBackOnSuccess(this);
        };
    }
}
