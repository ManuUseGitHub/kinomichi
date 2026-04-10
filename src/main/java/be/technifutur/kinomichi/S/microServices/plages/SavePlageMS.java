package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.NavigatorUtils;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

public class SavePlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public SavePlageMS(TimeTables tts, String event) {
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            Promptor.displayMenu();

            System.out.println("Nommez votre nouveau fichier de sauvegarde");
            String fileName = Saisir.scanString();

            PlageVersionManagerService pvms = new PlageVersionManagerService();
            pvms.save(tts, fileName, (success) -> {
                if (success) {
                    NavigatorUtils.getBackOnSuccess(this);
                }
            });
        };
    }
}
