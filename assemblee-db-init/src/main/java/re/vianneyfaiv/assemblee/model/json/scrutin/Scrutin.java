package re.vianneyfaiv.assemblee.model.json.scrutin;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Scrutin {

    private String uid;
    private int numero;
    private String organeRef;
    private int legislature;
    private String sessionRef;
    private String seanceRef;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateScrutin;
    private TypeVote typeVote;
    private Sort sort;
    private String titre;
    private Demandeur demandeur;
    private Objet objet;
    private String modePublicationDesVotes;
    private SyntheseVote syntheseVote;
    private VentilationVotes ventilationVotes;
    private MiseAuPoint miseAuPoint;

    public String getUid() {
        return uid;
    }

    public int getNumero() {
        return numero;
    }

    public String getOrganeRef() {
        return organeRef;
    }

    public int getLegislature() {
        return legislature;
    }

    public String getSessionRef() {
        return sessionRef;
    }

    public String getSeanceRef() {
        return seanceRef;
    }

    public Date getDateScrutin() {
        return dateScrutin;
    }

    public TypeVote getTypeVote() {
        return typeVote;
    }

    public Sort getSort() {
        return sort;
    }

    public String getTitre() {
        return titre;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public Objet getObjet() {
        return objet;
    }

    public String getModePublicationDesVotes() {
        return modePublicationDesVotes;
    }

    public SyntheseVote getSyntheseVote() {
        return syntheseVote;
    }

    public VentilationVotes getVentilationVotes() {
        return ventilationVotes;
    }

    public MiseAuPoint getMiseAuPoint() {
        return miseAuPoint;
    }
}

