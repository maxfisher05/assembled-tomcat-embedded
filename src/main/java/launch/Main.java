package launch;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));


        //This is to compensate for the different paths that the appassembler-program and jsw daemon use
        String basedir = System.getProperty("basedir");
        String deamonWebapp = System.getProperty("deamon-webapp");
        if(null != basedir){
            basedir += "/webapp";
        } else if(null!= deamonWebapp){
            basedir = deamonWebapp;
        } else{
            basedir = "src/main/webapp";
        }

        System.out.println("Basedir: " + basedir);
        String webappLocation = new File(basedir).getAbsolutePath();
        System.out.println("configuring app with basedir: " + webappLocation);
        tomcat.addWebapp("/", webappLocation);

        tomcat.start();
        tomcat.getServer().await();
    }
}