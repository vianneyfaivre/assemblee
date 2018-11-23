package re.vianneyfaiv.assemblee.model.db;

import java.util.Date;

public class Organe {

    private String organeId;
    private String type;
    private String libelle;
    private Date dateDebut;
    private Date dateFin;
    private String regime;
    private int legislature;

    public Organe(String organeId, String type, String libelle, Date dateDebut, Date dateFin, String regime, int legislature) {
        this.organeId = organeId;
        this.type = type;
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.regime = regime;
        this.legislature = legislature;
    }

    public String getOrganeId() {
        return organeId;
    }

    public String getType() {
        return type;
    }

    public String getLibelle() {
        return libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getRegime() {
        return regime;
    }

    public int getLegislature() {
        return legislature;
    }
}
