package com.farmsoft.youskyblock;

import com.farmsoft.youskyblock.island.LevelData;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class loadData {

    public static Object readFile() {
        Yaml yaml = new Yaml();
        LevelData levelData = null;
        try {
            levelData = yaml.load(new FileReader(YouSkyBlockMod.CONFIGPATH + "\\levelConfig.yml"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return levelData;
    }

    static void writeFile(Object obj) {
        Yaml yaml = new Yaml();
        try {
            yaml.dump(obj, new FileWriter(YouSkyBlockMod.CONFIGPATH + "\\levelConfig.yml"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
