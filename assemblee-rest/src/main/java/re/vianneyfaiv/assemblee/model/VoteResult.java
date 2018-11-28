package re.vianneyfaiv.assemblee.model;

public enum VoteResult {

    APPROVED("adopté"),
    REJECTED("rejeté")
    ;

    private String code;

    VoteResult(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static VoteResult fromCode(String code) {
        if (APPROVED.getCode().equalsIgnoreCase(code)) {
            return APPROVED;
        }
        if (REJECTED.getCode().equalsIgnoreCase(code)) {
            return REJECTED;
        }
        throw new UnsupportedOperationException(
                "The vote result " + code + " is not supported!"
        );
    }
}
