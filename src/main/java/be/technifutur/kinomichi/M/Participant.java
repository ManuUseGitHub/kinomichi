package be.technifutur.kinomichi.M;

import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.CopyCatAble;
import be.technifutur.kinomichicommon.interfaces.HasId;

import java.io.Serializable;

public class Participant implements HasId, CopyCatAble<Participant,Participant.Builder>, Serializable {
    private int id;
    private ParticipantType type;
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String club;
    private String grade;

    private Participant(){}

    @Override
    public String toString() {
        return """
                >Participant n° (%s)
                >-------------------------------------------------------------------------
                >Type : %s
                >Personne : %s * %s
                >Contact -----------------------------------------------------------------
                >Email : %s | Tel : %s
                >Pratiquant --------------------------------------------------------------
                >Club et grade : %s / %s
                """.formatted(
                ConsoleColors.BLUE + getId() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getType().getName() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getFirstname() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getLastname() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getEmail() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getTelephone() + ConsoleColors.RESET,
                ConsoleColors.GREEN + getClub() + ConsoleColors.RESET,
                ConsoleColors.CYAN + getGrade() + ConsoleColors.RESET);
    }

    @Override
    public void copyCat(Participant built) {
        if (id != built.id)
            throw new RuntimeException("ID mismatched");
        this.id = built.id;
        this.type = built.type;
        this.firstname = built.firstname;
        this.lastname = built.lastname;
        this.email = built.email;
        this.telephone = built.telephone;
        this.club = built.club;
        this.grade = built.grade;
    }

    @Override
    public Builder pastyCat(Builder built) {
        built.id = this.id;
        built.type = this.type;
        built.firstname = this.firstname;
        built.lastname = this.lastname;
        built.email = this.email;
        built.telephone = this.telephone;
        built.club = this.club;
        built.grade = this.grade;
        return built;
    }

    public static class Builder {
        private int id;
        private String firstname;
        private String lastname;
        private String email;
        private String telephone;
        private String club;

        private String grade;
        private ParticipantType type;

        public Builder(ParticipantType type){
            this.type = type;
        }

        public Builder firstname(String name){
            this.firstname = name;
            return this;
        }

        public Builder lastname(String name){
            this.lastname = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder telephone(String telephone){
            this.telephone = telephone;
            return this;
        }

        public Builder type(ParticipantType type){
            this.type = type;
            return this;


        }

        public Builder club(String club) {
            this.club = club;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        Participant build(){
            Participant built = new Participant();
            built.id = this.id;
            built.type = this.type;
            built.firstname = this.firstname;
            built.lastname = this.lastname;
            built.email = this.email;
            built.telephone = this.telephone;
            built.club = this.club;
            built.grade = this.grade;

            return built;
        }
    }

    //region Accessors / Mutators
    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String name) {
        this.lastname = name;
    }

    public ParticipantType getType() {
        return type;
    }

    public void setType(ParticipantType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion
}
