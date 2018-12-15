package re.vianneyfaiv.assemblee.model.pojo;

public enum Gender {

    MALE("M."),
    FEMALE("Mme");

    String civilite;

    Gender(String civilite) {
        this.civilite = civilite;
    }

    public String getCivilite() {
        return civilite;
    }

    public static Gender fromCivilite(String civilite) {
        if (MALE.getCivilite().equalsIgnoreCase(civilite)) {
            return MALE;
        }
        if (FEMALE.getCivilite().equalsIgnoreCase(civilite)) {
            return FEMALE;
        }
        throw new UnsupportedOperationException(
                "The civilité " + civilite + " is not supported!"
        );
    }
}
