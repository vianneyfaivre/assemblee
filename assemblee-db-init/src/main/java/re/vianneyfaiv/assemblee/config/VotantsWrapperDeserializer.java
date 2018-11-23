package re.vianneyfaiv.assemblee.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import re.vianneyfaiv.assemblee.model.json.scrutin.VotantRef;
import re.vianneyfaiv.assemblee.model.json.scrutin.VotantsWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VotantsWrapperDeserializer extends JsonDeserializer<VotantsWrapper> {

    @Override
    public VotantsWrapper deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        VotantsWrapper result = new VotantsWrapper();

        if(node.isObject() && node.has("votant")) {

            List<VotantRef> votants = new ArrayList<>();
            JsonNode votantsNode = node.get("votant");

            // weird structure, it can be an array of VotantRef or a single VotantRef (not in an array)
            if(votantsNode.isArray()) {
                Iterator<JsonNode> votantsIt = votantsNode.iterator();

                while(votantsIt.hasNext()) {
                    JsonNode votantNode = votantsIt.next();
                    votants.add(getVotantRef(votantNode));
                }
            } else {
                votants.add(getVotantRef(votantsNode));
            }

            result.setVotant(votants);
        }

        return result;
    }

    private VotantRef getVotantRef(JsonNode votantNode) {
        VotantRef votantRef = new VotantRef();
        votantRef.setActeurRef(votantNode.get("acteurRef").asText());
        votantRef.setMandatRef(votantNode.get("mandatRef").asText());
        return votantRef;
    }
}
