package re.vianneyfaiv.assemblee.batch.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatJson;
import re.vianneyfaiv.assemblee.model.json.mandat.MandatWrapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

@Component
public class MandatItemReader implements ItemReader<MandatJson> {

    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${assemblee.mandats.source-path}")
    private String acteursSourcePath;

    private Iterator<File> mandatFiles;

    @PostConstruct
    public void init() throws IOException {
        File[] files = new File(acteursSourcePath).listFiles();
        this.mandatFiles = Arrays.asList(files).iterator();
    }

    @Override
    public MandatJson read() throws Exception {

        if(this.mandatFiles.hasNext()) {
            File mandatFile = this.mandatFiles.next();

            try(InputStream json = new FileInputStream(mandatFile)) {
                MandatWrapper mandat = jsonMapper.readValue(json, MandatWrapper.class);

                return mandat.getMandat();
            }
        } else {
            return null;
        }
    }
}
