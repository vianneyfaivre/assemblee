package re.vianneyfaiv.assemblee.batch.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurJson;
import re.vianneyfaiv.assemblee.model.json.acteur.ActeurWrapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class ActeurItemReader implements ItemReader<ActeurJson> {

    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${assemblee.acteurs.source-path}")
    private String acteursSourcePath;

    private Iterator<File> acteurFiles;

    @PostConstruct
    public void init() throws IOException {
        File[] files = new File(acteursSourcePath).listFiles();
        this.acteurFiles = Arrays.asList(files).iterator();
    }

    @Override
    public ActeurJson read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(this.acteurFiles.hasNext()) {
            File acteurFile = this.acteurFiles.next();

            try(InputStream json = new FileInputStream(acteurFile)) {
                ActeurWrapper acteur = jsonMapper.readValue(json, ActeurWrapper.class);

                return acteur.getActeur();
            }
        } else {
            return null;
        }
    }
}
