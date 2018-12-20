package re.vianneyfaiv.assemblee.model.json.scrutin;

public class ScrutinVote {

    private String positionMajoritaire;
    private DecompteVoix decompteVoix;
    private DecompteNominatif decompteNominatif;

    public String getPositionMajoritaire() {
        return positionMajoritaire;
    }

    public void setPositionMajoritaire(String positionMajoritaire) {
        this.positionMajoritaire = positionMajoritaire;
    }

    public DecompteNominatif getDecompteNominatif() {
        return decompteNominatif;
    }

    public void setDecompteNominatif(DecompteNominatif decompteNominatif) {
        this.decompteNominatif = decompteNominatif;
    }

    public DecompteVoix getDecompteVoix() {
        return decompteVoix;
    }

    public void setDecompteVoix(DecompteVoix decompteVoix) {
        this.decompteVoix = decompteVoix;
    }
}
