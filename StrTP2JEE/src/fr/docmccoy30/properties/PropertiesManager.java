package fr.docmccoy30.properties;

import javax.servlet.GenericServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesManager {

    public static Properties readFileProperties(GenericServlet servlet) {
        Properties props = new Properties();
        String propertyFilePathAndName = servlet.getServletContext().getInitParameter("app_properties");
        InputStream inputStream = servlet.getServletContext().getResourceAsStream(propertyFilePathAndName);
        try {
            props.load(inputStream);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return props;
    }

    public static void toString(Properties props) {
        Enumeration<?> enumeration = props.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            System.out.println("Key: "+key+"   Value: " + props.getProperty(key));
        }
    }
}

