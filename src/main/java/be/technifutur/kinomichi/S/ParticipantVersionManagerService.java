package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.interfaces.VersionLoadable;
import be.technifutur.kinomichicommon.interfaces.VersionSavable;

import java.util.function.Consumer;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.MULTILINE;
import static store.luniversdemm.common.Utils.onMatches;

public class ParticipantVersionManagerService
        extends VersionManagerService<Participants>
        implements VersionSavable<Participants>, VersionLoadable<Participants> {

    @Override
    public Participants loadByTextSource(String textContent, Consumer<Boolean> cbComplete) {
        Participants tts = new Participants();
        onMatches(Pattern.compile(">Participant n° \\((?<id>\\d+)\\)\\n>-+\\n>Type : (?<type>.*)\\n>Personne : (?<firstname>.+) \\* (?<lastname>.+)\\n>Contact -+\\n>Email : (?<email>[^\\s]+) \\| Tel : (?<telephone>.+)\\n>Pratiquant -+\\n>Club et grade : (?<club>.+) / (?<grade>.+)",
                        MULTILINE),
                textContent, m -> {
                    int id = Integer.parseInt(m.group("id"));
                    tts.addItem(
                            new Participant.Builder(ParticipantType.get(m.group("type")))
                                    .firstname(m.group("firstname"))
                                    .lastname(m.group("lastname"))
                                    .email(m.group("email"))
                                    .telephone(m.group("telephone"))
                                    .club(m.group("club"))
                                    .grade(m.group("grade")
                                    ).id(id)
                    );
                });
        cbComplete.accept(true);
        return tts;
    }

    public Participants loadByTextSource(String textContent){
        return loadByTextSource(textContent,ignore -> {});
    }
}
