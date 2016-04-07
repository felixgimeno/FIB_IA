package representacion;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author felix.axel.gimeno
 */
public class ServerSuccessorFunctionR implements SuccessorFunction {

    private int saSeed;
    public ServerSuccessorFunctionR(int saSeed){
        this.saSeed=saSeed;
    }
    @Override
    public List getSuccessors(Object state) {
        final ServerData data = (ServerData) state;
        List<Successor> list = new ArrayList<> ();
        for (int count = 0; count < 100; count += 1){
            final int i = new Random(this.saSeed).nextInt(data.getNservers());
            if (data.getRequests(i).isEmpty()) {
                continue;
            }
            final int j = new Random(this.saSeed).nextInt(data.getNservers());
            final int k = new Random(this.saSeed).nextInt(data.getRequests(i).size());
            
            final boolean moveSelected = 1 == new Random(this.saSeed).nextInt(1+data.getNreq());
            
            if (!data.isPossibleMove(i, j, k)) continue;
            final ServerData dataNew = new ServerData(data);
            dataNew.moveRequest(i, j, k);
            list.add(new Successor(String.format("move i %d j %d k %d",i,j,k), dataNew));
            return list;
            //move far es numberServers^2 * numberRequests
            //swap far es numberServers^2 * numberRequests^2
            // swap far / move far = numberRequests

        }
        assert(!list.isEmpty());
        //System.out.println(list);
        return list;
    }
}