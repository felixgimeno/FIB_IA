package representacion;

import java.util.ArrayList;
import IA.DistFS.Servers;

public class ServerData {
    private int ns;

    private ArrayList<ArrayList<Integer>> servers;

    public ServerData(int s) {
        ns = s;
        servers = new ArrayList<>(s);
    }

    //Operators

    public void addRequest(int server_id, int req) {
        servers.get(server_id).add(req);
    }

    public void moveRequest(int server1, int server2, int req) {
        int n = servers.get(server1).indexOf(req);
        servers.get(server1).remove(n);
        servers.get(server2).add(req);
    }

    public void swapRequest(int server1, int r1, int server2, int r2) {
        int i = servers.get(server1).indexOf(r1);
        int j = servers.get(server2).indexOf(r2);
        servers.get(server1).remove(i);
        servers.get(server2).remove(j);
        servers.get(server1).add(i,r2);
        servers.get(server2).add(j,r1);
    }

    //Getters

    public int getNs() { return ns; }

    public ArrayList<Integer> getAllRequests(int server_id) {
        return servers.get(server_id);
    }
}
