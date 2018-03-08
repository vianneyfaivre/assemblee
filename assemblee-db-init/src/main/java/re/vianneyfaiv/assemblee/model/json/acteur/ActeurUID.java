package re.vianneyfaiv.assemblee.model.json.acteur;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActeurUID {

    @JsonProperty("#text")
    private String id;

    public String getId() {
        return id;
    }
}
