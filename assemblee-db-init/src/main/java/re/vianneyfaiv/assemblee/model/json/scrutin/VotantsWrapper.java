package re.vianneyfaiv.assemblee.model.json.scrutin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import re.vianneyfaiv.assemblee.config.VotantsWrapperDeserializer;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = VotantsWrapperDeserializer.class)
public class VotantsWrapper {

    private List<VotantRef> votant;

    public List<VotantRef> getVotant() {
        return votant;
    }

    public void setVotant(List<VotantRef> votant) {
        this.votant = votant;
    }
}
