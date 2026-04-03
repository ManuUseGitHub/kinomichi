package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

public class StarterMS extends MicroService implements MicroServiable {

    private final TimeTables tts;
    private static boolean once;
    public StarterMS(TimeTables tts,String event){
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            if(!once){
                once = true;
                System.out.println(ConsoleColors.BLACK+ConsoleColors.BLUE_BACKGROUND+"LOADING STATE"+ConsoleColors.RESET);
                PlageVersionManagerService pvms = new PlageVersionManagerService();
                tts.replaceTimeTables(pvms.load("tom.ser"));
            }
        };
    }
}
