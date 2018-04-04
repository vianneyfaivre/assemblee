package re.vianneyfaiv.assemblee.model.json.scrutin;

public class ScrutinVote {

    private String positionMajoritaire;
    private Decompte decompteVoix;
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

    public Decompte getDecompteVoix() {
        return decompteVoix;
    }

    public void setDecompteVoix(Decompte decompteVoix) {
        this.decompteVoix = decompteVoix;
    }
}
