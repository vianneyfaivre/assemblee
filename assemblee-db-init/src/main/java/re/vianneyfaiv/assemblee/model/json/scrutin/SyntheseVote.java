package re.vianneyfaiv.assemblee.model.json.scrutin;

public class SyntheseVote {

    private int nombreVotants;
    private int suffragesExprimes;
    private int nbrSuffragesRequis;
    private Decompte decompte;

    public int getNombreVotants() {
        return nombreVotants;
    }

    public void setNombreVotants(int nombreVotants) {
        this.nombreVotants = nombreVotants;
    }

    public int getSuffragesExprimes() {
        return suffragesExprimes;
    }

    public void setSuffragesExprimes(int suffragesExprimes) {
        this.suffragesExprimes = suffragesExprimes;
    }

    public int getNbrSuffragesRequis() {
        return nbrSuffragesRequis;
    }

    public void setNbrSuffragesRequis(int nbrSuffragesRequis) {
        this.nbrSuffragesRequis = nbrSuffragesRequis;
    }

    public Decompte getDecompte() {
        return decompte;
    }

    public void setDecompte(Decompte decompte) {
        this.decompte = decompte;
    }
}
