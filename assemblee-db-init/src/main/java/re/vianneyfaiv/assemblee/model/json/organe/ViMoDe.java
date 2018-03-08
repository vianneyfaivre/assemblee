package re.vianneyfaiv.assemblee.model.json.organe;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ViMoDe {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
}
