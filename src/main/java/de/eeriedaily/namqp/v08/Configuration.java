package de.eeriedaily.namqp.v08;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Configuration {

    public String getBrokerHostname() {
       return "localhost";
    }

    public int getBrokerPort() {
        return 5672;
    }

    public String getVirtualHost() {
        return "/";
    }

    public String getLogin() {
        return "guest";
    }

    public String getPassword() {
        return "guest";
    }
    
    public short getHeartbeat() {
        return 10;
    }

}
