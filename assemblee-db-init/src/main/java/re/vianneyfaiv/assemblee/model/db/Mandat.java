package re.vianneyfaiv.assemblee.model.db;

import java.util.Date;
import java.util.List;

public class Mandat {

    private String mandatId;
    private String acteurId;
    private String organeType;
    private Date dateDebut;
    private Date datePriseFonction;
    private Date dateFin;
    private int numPlaceHemicycle;
    private String cause;
    private List<MandatOrgane> organes;

    public Mandat(String mandatId, String acteurId, String organeType, Date dateDebut, Date datePriseFonction, Date dateFin, int numPlaceHemicycle, String cause, List<MandatOrgane> organes) {
        this.mandatId = mandatId;
        this.acteurId = acteurId;
        this.organeType = organeType;
        this.dateDebut = dateDebut;
        this.datePriseFonction = datePriseFonction;
        this.dateFin = dateFin;
        this.numPlaceHemicycle = numPlaceHemicycle;
        this.cause = cause;
        this.organes = organes;
    }

    public String getMandatId() {
        return mandatId;
    }

    public String getActeurId() {
        return acteurId;
    }

    public String getOrganeType() {
        return organeType;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDatePriseFonction() {
        return datePriseFonction;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getNumPlaceHemicycle() {
        return numPlaceHemicycle;
    }

    public String getCause() {
        return cause;
    }

    public List<MandatOrgane> getOrganes() {
        return organes;
    }
}
