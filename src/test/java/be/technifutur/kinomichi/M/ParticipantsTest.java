package be.technifutur.kinomichi.M;

import be.technifutur.kinomichicommon.ParticipantType;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {
    String[] names = {
      "Alber","Bruno","Chantale","Daisy","Ethan","Francine",
      "Gautrand","Harry","Ivan","Julien","Kevin","Lanna","Maurice",
      "Nicolas","Oliver","Prunelle","Quinten","Rose-Marie","Stephane",
      "Thomas","Uvogin","Valtide","William","Xavier","Yolande","Zoé"
    };

    @Test
    void itIsPossibleToAddAParticipantWithTheLastId(){
        Participants tts = new Participants();
        tts.addItem(new Participant.Builder(ParticipantType.VIP));

        assertNotNull(tts.getItemById(1));
    }

    @Test
    void allGeneratedIdsAreFromAnIncrementation(){
        Participants tts = new Participants();
        IntStream.range(0,10).forEach(n -> {
            tts.addItem(
                    new Participant.Builder(ParticipantType.MEMBER)
                            .firstname(names[n])
            );
        });

        IntStream.range(0,10).forEach(n -> {
            System.out.println(tts.getItemById(n+1).getFirstname());
            assertNotNull(tts.getItemById(n+1));
        });
    }

    @Test
    void removedParticipantDontDisruptTheIdGeneration(){
        Participants tts = new Participants();
        IntStream.range(0,10).forEach(n -> {
            tts.addItem(
                    new Participant.Builder(ParticipantType.MEMBER)
                            .firstname(names[n]));
        });

        tts.removeItem(5);
        tts.addItem(new Participant.Builder(ParticipantType.TEACHER)
                .firstname("*** Lucas ***"));

        tts.getItems().forEach(t -> {
            System.out.println(t.getFirstname());
        });
    }

}