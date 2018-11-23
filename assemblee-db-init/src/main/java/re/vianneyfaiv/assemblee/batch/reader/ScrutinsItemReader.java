package re.vianneyfaiv.assemblee.batch.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinJson;
import re.vianneyfaiv.assemblee.model.json.scrutin.ScrutinsWrapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Component
public class ScrutinsItemReader implements ItemReader<ScrutinJson> {

    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${assemblee.scrutins.source-path}")
    private String scrutinsSourcePath;

    private Iterator<ScrutinJson> scrutins;

    @PostConstruct
    public void init() throws IOException {
        try(InputStream json = new FileInputStream(new File(scrutinsSourcePath))) {
            ScrutinsWrapper scrutins = jsonMapper.readValue(json, ScrutinsWrapper.class);

            this.scrutins = scrutins.getScrutins().getScrutin().iterator();
        }
    }

    @Override
    public ScrutinJson read() {

        if(this.scrutins.hasNext()) {
            return scrutins.next();
        } else {
            return null;
        }
    }
}
