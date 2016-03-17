package representacion;

import IA.DistFS.Requests;
import IA.DistFS.Servers;
import java.util.ArrayList;

public class ServerData2 extends ServerData {

    private final ArrayList<Integer> myState; // funcion requestid a serverid

    public ServerData2(int ns, int nr, int s1, int nu, int nrq, int s2, int j) {
        super(ns, nr, s1, nu, nrq, s2, j);
        myState = new ArrayList<>(super.getRdata().size());
    }

    public int serverOf(int request_id) {
        return myState.get(request_id);
    }

    public void setServer(int request_id, int server_id) {
        myState.set(request_id, server_id);
    }

    public void swapServers(int r1, int r2) {
        final int s1 = myState.get(r1);
        final int s2 = myState.get(r2);
        myState.set(r1, s2);
        myState.set(r2, s1);
    }

    @Deprecated
    @Override
    public ArrayList<Integer> getRequests(int server_id) {
        return null; // to do
    }

    @Override
    public void addRequest(int server_id, int req) {
        this.setServer(req, server_id);
    }

    @Override
    public void moveRequest(int server1, int server2, int req) {
        this.setServer(req, server2);
    }

    @Override
    public void swapRequest(int server1, int r1, int server2, int r2) {
        this.swapServers(r1, r2);
    }
}
/*
public class ServerData2 {
    private static int nserv;
    private static int nrep;
    private static int sseed;
    private static int nusers;
    private static int nreq;
    private static int rseed;
    private static Servers sdata;
    private static Requests rdata;
    private ArrayList<Integer> state; // funcion requestid a serverid

    public ServerData2(int ns, int nr, int s1, int nu, int nrq, int s2) {
        try {
            nserv = ns;
            nrep = nr;
            sseed = s1;
            nusers = nu;
            nreq = nrq;
            rseed = s2;
            sdata = new Servers(ns, nr, s1);
            rdata = new Requests(nu, nrq, s2);
            state = new ArrayList<>(rdata.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Operators

    public void setServer(int request_id, int server_id) {
        state.set(request_id, server_id);
    }

    public void swapServers(int r1, int r2) {
        int s1 = state.get(r1);
        int s2 = state.get(r2);
        state.set(r1, s2);
        state.set(r2, s1);
    }

    //Getters

    public int getNservers() { return nserv; }

    public int getNrep() { return nrep; }

    public int getSseed() { return sseed; }

    public int getNusers() { return nusers; }

    public int getNreq() { return nreq; }

    public int getRseed() { return rseed; }

    public Servers getSdata() { return sdata; }

    public Requests getRdata() { return rdata; }

    public int serverOf(int request_id) {
        return state.get(request_id);
    }
}
*/
