package representacion;

import java.util.ArrayList;

import IA.DistFS.Requests;
import IA.DistFS.Servers;

public class ServerData {

    private static int nserv;
    private static int nrep;
    private static int sseed;
    private static int nusers;
    private static int nreq;
    private static int rseed;
    private static Servers sdata;
    private static Requests rdata;
    private ArrayList<ArrayList<Integer>> state;

    public ServerData(int ns, int nr, int s1, int nu, int nrq, int s2) {
        try {
            nserv = ns;
            nrep = nr;
            sseed = s1;
            nusers = nu;
            nreq = nrq;
            rseed = s2;
            sdata = new Servers(ns, nr, s1);
            rdata = new Requests(nu, nrq, s2);
            state = new ArrayList<>(ns);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerData(ServerData old) {
        nserv = old.nserv;
        nrep = old.nrep;
        sseed = old.sseed;
        nusers = old.nusers;
        nreq = old.nreq;
        rseed = old.rseed;
        sdata = old.sdata;
        rdata = old.rdata;
        state = (ArrayList<ArrayList<Integer>>) old.state.clone();
    }
    //Operators

    public void addRequest(int server_id, int req) {
        state.get(server_id).add(req);
    }

    public void moveRequest(int server1, int server2, int req) {
        int n = state.get(server1).indexOf(req);
        state.get(server1).remove(n);
        state.get(server2).add(req);
    }

    public void swapRequest(int server1, int r1, int server2, int r2) {
        int i = state.get(server1).indexOf(r1);
        int j = state.get(server2).indexOf(r2);
        state.get(server1).remove(i);
        state.get(server2).remove(j);
        state.get(server1).add(i, r2);
        state.get(server2).add(j, r1);
    }

    //Getters
    public int getNservers() {
        return nserv;
    }

    public int getNrep() {
        return nrep;
    }

    public int getSseed() {
        return sseed;
    }

    public int getNusers() {
        return nusers;
    }

    public int getNreq() {
        return nreq;
    }

    public int getRseed() {
        return rseed;
    }

    public Servers getSdata() {
        return sdata;
    }

    public Requests getRdata() {
        return rdata;
    }

    public ArrayList<Integer> getRequests(int server_id) {
        return state.get(server_id);
    }
}
