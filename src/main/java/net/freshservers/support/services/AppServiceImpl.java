package net.freshservers.support.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class AppServiceImpl implements AppService {
    @Getter
    private Set<String> ipadApps = new HashSet<>();
    @Getter
    private Set<String> androidApps = new HashSet<>();

    private final File androidDir = new File("static/apps/android");
    private final File ipadDir = new File("static/apps/ipad");

    private final String[] EXTENSIONS = new String[]{
            "apk", "ipa"
    };

    private final FilenameFilter FILE_FILTER = (dir, name) -> {
        for (final String ext : EXTENSIONS) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
    };

    public AppServiceImpl() {
        scanFolder();
    }
    @Scheduled(cron="0 0 * * * *")
    private void scanFolder(){
        log.warn("Apps scanned");
        if(ipadDir.isDirectory()){
            for(File f : Objects.requireNonNull(ipadDir.listFiles(FILE_FILTER))){
                ipadApps.add(f.getName());
            }
        }
        if(androidDir.isDirectory()){
            for(File f : Objects.requireNonNull(androidDir.listFiles(FILE_FILTER))){
                androidApps.add(f.getName());
            }
        }
    }
}
