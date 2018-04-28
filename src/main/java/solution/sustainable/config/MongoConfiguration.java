package solution.sustainable.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Setter
@Getter
public class MongoConfiguration {
    private List<String> servers;
    private String database;
    private String username;
    private String password;

    public String getServers() {
        StringBuilder sb = new StringBuilder();
        for(String server: servers)
            sb.append(server).append(",");
        String serverString = sb.toString();
        return serverString.substring(0,serverString.length()-1);
    }
}
