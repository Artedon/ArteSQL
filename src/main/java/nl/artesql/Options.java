package nl.artesql;

import java.util.HashMap;

public class Options {

    public String host;
    public String port;
    public String user;
    public String pass;
    public String name;

    public Options(HashMap<String, String> options) {
        if(options.get("host") != null) host = options.get("host");
        if(options.get("port") != null) port = options.get("port");
        if(options.get("user") != null) user = options.get("user");
        if(options.get("pass") != null) pass = options.get("pass");
        if(options.get("name") != null) name = options.get("name");
    }

}
