package re.vianneyfaiv.assemblee.model.db;

public class Acteur {

    private String acteurId;
    private String civilite;
    private String prenom;
    private String nom;

    public Acteur(String acteurId, String civilite, String prenom, String nom) {
        this.acteurId = acteurId;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getActeurId() {
        return acteurId;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
