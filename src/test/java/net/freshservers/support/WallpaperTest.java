package net.freshservers.support;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class WallpaperTest {

    private Set<String> wallpapers = new HashSet<>();
    private final File dir = new File("images/wallpaper");
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

    private void scanFolder(){
        if(dir.isDirectory()){
            for(File f : Objects.requireNonNull(dir.listFiles(IMAGE_FILTER))){
                wallpapers.add(f.getName());
//                log.info(f.getName());
            }
        }
    }

    @Test
    public void test(){
        scanFolder();
        for (String s:wallpapers){
            log.info(s);
        }
    }

    @Test
    public void testStringSplitter(){

    }


}
