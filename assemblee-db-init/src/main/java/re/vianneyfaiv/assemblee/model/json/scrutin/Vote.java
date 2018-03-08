package re.vianneyfaiv.assemblee.model.json.scrutin;

public class Vote {

    private String positionMajoritaire;
    private DecompteVoix decompteVoix;
    private DecompteNominatif decompteNominatif;

    public String getPositionMajoritaire() {
        return positionMajoritaire;
    }

    public DecompteVoix getDecompteVoix() {
        return decompteVoix;
    }

    public DecompteNominatif getDecompteNominatif() {
        return decompteNominatif;
    }
}
