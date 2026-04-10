package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

import static be.technifutur.kinomichi.S.NavigatorUtils.getBackOnSuccess;

public class ListingPeopleMS extends MicroService implements MicroServiable {
    private final Participants pps;

    public ListingPeopleMS(Participants pps,String event) {
        super(event);
        this.pps = pps;
    }

    @Override
    public IEventListener handle() {
        return new IEventListener() {
            @Override
            public void processEvent(Event event) {
                pps.getItems().forEach(System.out::println);
                System.out.println("Faites la touche <Enter> pour continuer");

                getBackOnSuccess(this);
            }
        };
    }
}
