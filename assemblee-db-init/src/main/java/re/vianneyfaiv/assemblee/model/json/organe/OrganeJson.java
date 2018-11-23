package re.vianneyfaiv.assemblee.model.json.organe;

public class OrganeJson {

    private String uid;
    private String codeType;
    private String libelle;
    private ViMoDe viMoDe;
    private String regime;
    private int legislature;

    public String getUid() {
        return uid;
    }

    public String getCodeType() {
        return codeType;
    }

    public String getLibelle() {
        return libelle;
    }

    public ViMoDe getViMoDe() {
        return viMoDe;
    }

    public String getRegime() {
        return regime;
    }

    public int getLegislature() {
        return legislature;
    }
}