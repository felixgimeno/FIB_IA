package representacion;

import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.ArrayList;

public class ServerData2 {
    private static int nserv;
    private static int nrep;
    private static int sseed;
    private static int nusers;
    private static int nreq;
    private static int rseed;
    private static Servers sdata;
    private static Requests rdata;
    private ArrayList<Integer> state;

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
