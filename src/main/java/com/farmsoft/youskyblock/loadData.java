package com.farmsoft.youskyblock;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;

public class loadData {

    public static Object readFile(String fileName) {
        copyFromRes(fileName);
        Yaml yaml = new Yaml();
        Object object = null;
        try {
            object = yaml.load(new FileReader(YouSkyBlockMod.CONFIGPATH + fileName));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    static void writeFile(Object obj, String fileName) {
        Yaml yaml = new Yaml();
        try {
            yaml.dump(obj, new FileWriter(YouSkyBlockMod.CONFIGPATH + "\\" + fileName));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void copyFromRes(String resName) {
        File f = new File(YouSkyBlockMod.CONFIGPATH + "\\" + resName);
        if( !(f.exists() && !f.isDirectory())) {

            try {
                URL inputUrl = loadData.class.getClassLoader().getResource(resName);
                File dest = new File(YouSkyBlockMod.CONFIGPATH + "\\" + resName);
                FileUtils.copyURLToFile(inputUrl, dest);
            } catch (Exception e) {

            }
    }}

}
