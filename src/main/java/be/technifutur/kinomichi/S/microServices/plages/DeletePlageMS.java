package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

import static be.technifutur.kinomichi.S.NavigatorUtils.getBackOnSuccess;
import static be.technifutur.kinomichi.V.Promptor.selectItems;

public class DeletePlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public DeletePlageMS(TimeTables tts, String event){
        super(event);
        this.tts = tts;
    }

    @Override
    public IEventListener handle() {
        return event -> {
            onDeletePlage(event);
            getBackOnSuccess(this);
        };
    }

    public void onDeletePlage(Event event) {
        Promptor.displayMenu();
        selectItems(tts,"Quelle plage voulez-vous supprimer? (plusieurs choix possibles)")
                .stream().map(Integer::parseInt).sorted((a, b) -> b - a)
                .forEach(tts::removeItem);
    }
}
