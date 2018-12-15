package re.vianneyfaiv.assemblee.model.jpa;

import re.vianneyfaiv.assemblee.dao.PoliticalBodyTypeConverter;
import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyType;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "organes")
public class PoliticalBody {

    @Id
    @Column(name = "organe_id")
    private String id;

    @Convert(converter = PoliticalBodyTypeConverter.class)
    private PoliticalBodyType type;

    @Column(name = "libelle")
    private String label;

    @Column(name = "dateDebut")
    private Date startDate;

    @Column(name = "dateFin")
    private Date endDate;

    private String regime;

    private int legislature;

    public String getId() {
        return id;
    }

    public PoliticalBodyType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getRegime() {
        return regime;
    }

    public int getLegislature() {
        return legislature;
    }

    @Override
    public String toString() {
        return "PoliticalBody{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", label='" + label + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", regime='" + regime + '\'' +
                ", legislature=" + legislature +
                '}';
    }
}
