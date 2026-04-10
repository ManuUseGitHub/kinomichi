package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichicommon.interfaces.VersionLoadable;
import be.technifutur.kinomichicommon.interfaces.VersionSavable;

import java.util.function.Consumer;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.MULTILINE;
import static store.luniversdemm.common.DateAndTimeUtils.durationBetweenTwoTimes;
import static store.luniversdemm.common.Utils.onMatches;

public class PlageVersionManagerService
        extends VersionManagerService<TimeTables>
        implements VersionSavable<TimeTables>, VersionLoadable<TimeTables> {

    @Override
    public TimeTables loadByTextSource(String textContent, Consumer<Boolean> cbComplete) {
        TimeTables tts = new TimeTables();
        onMatches(Pattern.compile(">Tranche horaire n° \\((?<id>\\d+)\\)\\n>-+\\n>Activité : (?<activity>.*)\\n>Description :\\n> (?<description>.*)\\n>Qui anime : (?<formator>.*)\\n>Temporalité -+\\n>(?<date>\\d{1,2}\\/\\d{1,2}\\/\\d{4}) \\| (?<start>\\d{1,2}:\\d{1,2}) -> (?<end>\\d{1,2}:\\d{1,2})",
                        MULTILINE),
                textContent, m -> {
                    tts.addItem(
                            new TimeTable.Builder(m.group("activity"))
                                    .description(m.group("description"))
                                    .start(m.group("start"))
                                    .animator(m.group("formator"))
                                    .duration(durationBetweenTwoTimes(
                                            m.group("end"),
                                            m.group("start"))
                                    )
                                    .date(m.group("date"))
                    );
                });
        cbComplete.accept(true);
        return tts;
    }

    public TimeTables loadByTextSource(String textContent) {
        return loadByTextSource(textContent,ignore->{});
    }
}
