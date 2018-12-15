package re.vianneyfaiv.assemblee.model.pojo;

public enum VoteMode {

    NOMINATIVE("DecompteNominatif"),
    DISSIDENTS("DecompteDissidentsPositionGroupe")
    ;

    private String code;

    VoteMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static VoteMode fromCode(String code) {
        if (NOMINATIVE.getCode().equalsIgnoreCase(code)) {
            return NOMINATIVE;
        }
        if (DISSIDENTS.getCode().equalsIgnoreCase(code)) {
            return DISSIDENTS;
        }
        throw new UnsupportedOperationException(
                "The vote mode " + code + " is not supported!"
        );
    }
}
