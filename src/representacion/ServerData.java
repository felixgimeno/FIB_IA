package representacion;

import java.util.ArrayList;

import IA.DistFS.Requests;
import IA.DistFS.Servers;
import java.util.Set;

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
            state = new ArrayList<>();
            for (Integer i = 0; i < ns; i +=1){
                state.add(new ArrayList<>() );
            }
            this.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init(){
        for (int i = 0; i < rdata.size();  i+=1){
            final int fileid = rdata.getRequest(i)[1];
            final Set<Integer> s = sdata.fileLocations(fileid);
            final int server_dest_id = s.iterator().next();
            this.addRequest(server_dest_id,i);
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
    public boolean isPossibleMove(int server1, int server2, int req){
        return state.get(server1).contains(req) &&
            sdata.fileLocations(rdata.getRequest(req)[1]).contains(server2);
    }
    public void moveRequest(int server1, int server2, int req) {
        //System.out.println(String.format("server1 %d server %d req %d",server1,server2,req));
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

    /**
     *
     * @return arraylist del tiempo total de transmision de cada servidor
     */
    public ArrayList<Integer> getQuality(){
        ArrayList<Integer> here = new ArrayList<> ();
        for (Integer i = 0; i < this.nserv; i +=1){
            final Integer serverid = i;
            here.add(this.getRequests(serverid).stream().map((a) -> sdata.tranmissionTime(serverid, rdata.getRequest(a)[0])).reduce(0,(a,b)->a+b));
        }
        return here;
    }
}
