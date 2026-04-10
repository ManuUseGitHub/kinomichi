package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.ParticipantVersionManagerService;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import static be.technifutur.kinomichi.S.NavigatorUtils.getBackOnSuccess;

public class SavePeopleMS extends MicroService implements MicroServiable {
    private final Participants pps;

    public SavePeopleMS(Participants pps, String event){
        super(event);
        this.pps = pps;
    }

    public IEventListener handle() {
        return new IEventListener() {
            @Override
            public void processEvent(Event event) {
                Promptor.displayMenu();

                System.out.println("Nommez votre nouveau fichier de sauvegarde");
                String fileName = Saisir.scanString();

                ParticipantVersionManagerService pvms = new ParticipantVersionManagerService();
                pvms.save(pps,fileName,(success) -> {
                   if(success){
                       getBackOnSuccess(this);
                   }
                });
            }
        };
    }
}