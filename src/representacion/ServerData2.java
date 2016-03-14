package representacion;

import java.util.ArrayList;

public class ServerData2 {
    private int nr;
    private ArrayList<Integer> requests;

    public ServerData2(int n) {
        nr = n;
        requests = new ArrayList<>(n);
    }

    //Operators

    public void setServer(int request_id, int server_id) {
        requests.set(request_id,server_id);
    }

    public void swapServers(int r1, int r2) {
        int s1 = requests.get(r1);
        int s2 = requests.get(r2);
        requests.set(r1,s2);
        requests.set(r2,s1);
    }

    //Getters

    public int getNr() { return nr; }

    public int serverOf(int request_id) {
        return requests.get(request_id);
    }

}
