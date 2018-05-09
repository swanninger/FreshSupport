package net.freshservers.support.services;

import lombok.Getter;
import lombok.Setter;
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
public class WallpaperServiceImpl implements WallpaperService {
    @Getter @Setter
    private Set<String> androidWallpapers = new HashSet<>();
    @Getter @Setter
    private Set<String> ipadWallpapers = new HashSet<>();

    private final File ipadDir = new File("static/images/wallpaper/ipad");
    private final File androidDir = new File("static/images/wallpaper/android");

    private final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp"
    };

    private final FilenameFilter IMAGE_FILTER = (dir, name) -> {
        for (final String ext : EXTENSIONS) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
    };

    public WallpaperServiceImpl() {
        scanFolder();
    }

    @Scheduled(cron="0 0 * * * *")
    private void scanFolder(){
        log.warn("Wallpapers scanned");
        if(ipadDir.isDirectory()){
            for(File f : Objects.requireNonNull(ipadDir.listFiles(IMAGE_FILTER))){
                ipadWallpapers.add(f.getName());
            }
        }
        if(androidDir.isDirectory()){
            for(File f : Objects.requireNonNull(androidDir.listFiles(IMAGE_FILTER))){
                androidWallpapers.add(f.getName());
            }
        }
    }
}
