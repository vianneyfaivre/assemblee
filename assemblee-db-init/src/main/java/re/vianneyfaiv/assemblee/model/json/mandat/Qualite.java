package re.vianneyfaiv.assemblee.model.json.mandat;

public class Qualite {

    private String codeQualite;
    private String libQualite;
    private String libQualiteSex;

    public String getCodeQualite() {
        return codeQualite;
    }

    public String getLibQualite() {
        return libQualite;
    }

    public String getLibQualiteSex() {
        return libQualiteSex;
    }

    @Override
    public String toString() {
        return "Qualite{" +
                "codeQualite='" + codeQualite + '\'' +
                ", libQualite='" + libQualite + '\'' +
                ", libQualiteSex='" + libQualiteSex + '\'' +
                '}';
    }
}
