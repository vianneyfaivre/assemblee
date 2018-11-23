package re.vianneyfaiv.assemblee.model.db;

import java.util.Date;

public class Scrutin {

    private String scrutinId;
    private String titre;
    private int numero;
    private String organeId;
    private int legislature;
    private String sessionId;
    private String seanceId;
    private Date dateScrutin;
    private String typeVote;
    private String sort;
    private String demandeur;
    private String objet;
    private String modePublicationVotes;
    private int resultatNombreVotants;
    private int resultatPour;
    private int resultatContre;
    private int resultatAbstention;
    private int resultatNonVotant;

    public Scrutin(String scrutinId, String titre, int numero, String organeId, int legislature, String sessionId, String seanceId, Date dateScrutin, String typeVote, String sort, String demandeur, String objet, String modePublicationVotes, int resultatNombreVotants, int resultatPour, int resultatContre, int resultatAbstention, int resultatNonVotant) {
        this.scrutinId = scrutinId;
        this.titre = titre;
        this.numero = numero;
        this.organeId = organeId;
        this.legislature = legislature;
        this.sessionId = sessionId;
        this.seanceId = seanceId;
        this.dateScrutin = dateScrutin;
        this.typeVote = typeVote;
        this.sort = sort;
        this.demandeur = demandeur;
        this.objet = objet;
        this.modePublicationVotes = modePublicationVotes;
        this.resultatNombreVotants = resultatNombreVotants;
        this.resultatPour = resultatPour;
        this.resultatContre = resultatContre;
        this.resultatAbstention = resultatAbstention;
        this.resultatNonVotant = resultatNonVotant;
    }

    public String getScrutinId() {
        return scrutinId;
    }

    public String getTitre() {
        return titre;
    }

    public int getNumero() {
        return numero;
    }

    public String getOrganeId() {
        return organeId;
    }

    public int getLegislature() {
        return legislature;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public Date getDateScrutin() {
        return dateScrutin;
    }

    public String getTypeVote() {
        return typeVote;
    }

    public String getSort() {
        return sort;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public String getObjet() {
        return objet;
    }

    public String getModePublicationVotes() {
        return modePublicationVotes;
    }

    public int getResultatNombreVotants() {
        return resultatNombreVotants;
    }

    public int getResultatPour() {
        return resultatPour;
    }

    public int getResultatContre() {
        return resultatContre;
    }

    public int getResultatAbstention() {
        return resultatAbstention;
    }

    public int getResultatNonVotant() {
        return resultatNonVotant;
    }
}