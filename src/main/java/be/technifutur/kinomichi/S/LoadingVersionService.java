package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.GroupManaging;

import static store.luniversdemm.common.Utils.onReadTextFile;

public class LoadingVersionService {
    public static void processLoading(GroupManaging groupManaging, VersionManagerService managerService, String fileName) {
        if (StateEngine.getInstance().getCurrentState() == States.PLAGE_LOADING_A ||
                StateEngine.getInstance().getCurrentState() == States.PEOPLE_LOADING_A) {

            groupManaging.replaceItems(managerService.load(fileName,(sucess) -> {
            }));
        } else {
            onReadTextFile(fileName, text -> {
                groupManaging.replaceItems(managerService.loadByTextSource(text,(sucess) -> {
                }));
            });
        }
    }
}
