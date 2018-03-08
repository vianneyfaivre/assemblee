package re.vianneyfaiv.assemblee.model.json.mandat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Mandature {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datePriseFonction;
    private int placeHemicycle;

    public Date getDatePriseFonction() {
        return datePriseFonction;
    }

    public int getPlaceHemicycle() {
        return placeHemicycle;
    }
}
