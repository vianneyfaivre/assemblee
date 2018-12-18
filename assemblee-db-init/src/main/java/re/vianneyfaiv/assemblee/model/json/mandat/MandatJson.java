package re.vianneyfaiv.assemblee.model.json.mandat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MandatJson {

    private String uid;
    private String acteurRef;
    private String typeOrgane;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    private Qualite infosQualite;
    private OrganesWrapper organes;
    private Election election;
    private Mandature mandature;

    public String getUid() {
        return uid;
    }

    public String getActeurRef() {
        return acteurRef;
    }

    public String getTypeOrgane() {
        return typeOrgane;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public OrganesWrapper getOrganes() {
        return organes;
    }

    public Election getElection() {
        return election;
    }

    public Mandature getMandature() {
        return mandature;
    }

    public Qualite getInfosQualite() {
        return infosQualite;
    }
}
