package net.freshservers.support.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.configuration.FreshProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class AppServiceImpl implements AppService {
    @Getter
    private Set<String> ipadApps = new HashSet<>();
    @Getter
    private Set<String> androidApps = new HashSet<>();
    @Getter
    private Set<String> techApps = new HashSet<>();

    private final File androidDir = new File("static/apps/android");
    private final File ipadDir = new File("static/apps/ipad");
    private final File techDir = new File("static/apps/tech");

    private final FreshProperties properties;

    private final List<String> EXTENSIONS;

    private final FilenameFilter FILE_FILTER;

    public AppServiceImpl(FreshProperties properties) {
        this.properties = properties;
        EXTENSIONS = properties.getExtensionTypes();

        FILE_FILTER = (dir, name) -> {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        };

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
        if(techDir.isDirectory()){
            for(File f : Objects.requireNonNull(techDir.listFiles(FILE_FILTER))){
                techApps.add(f.getName());
            }
        }
    }
}
