package re.vianneyfaiv.assemblee.batch.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeJson;
import re.vianneyfaiv.assemblee.model.json.organe.OrganeWrapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

@Component
public class OrganeItemReader implements ItemReader<OrganeJson> {

    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${assemblee.organes.source-path}")
    private String organesSourcePath;

    private Iterator<File> organeFiles;

    @PostConstruct
    public void init() throws IOException {
        File[] files = new File(organesSourcePath).listFiles();
        this.organeFiles = Arrays.asList(files).iterator();
    }

    @Override
    public OrganeJson read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(this.organeFiles.hasNext()) {
            File organeFile = this.organeFiles.next();

            try(InputStream json = new FileInputStream(organeFile)) {
                OrganeWrapper organe = jsonMapper.readValue(json, OrganeWrapper.class);

                return organe.getOrgane();
            }
        } else {
            return null;
        }
    }
}
