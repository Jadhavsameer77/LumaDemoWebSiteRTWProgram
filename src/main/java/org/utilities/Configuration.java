package org.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

    Properties conf;
    FileInputStream fis;

    public Configuration() {
        File file = new File("src/test/resources/megento_testdata.properties");
        try {
            fis = new FileInputStream(file);

            conf = new Properties();
            conf.load(fis);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  String getValues(String str) {

        return 	conf.getProperty(str);
    }
}
