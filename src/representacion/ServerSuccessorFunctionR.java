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

    @Override
    public List getSuccessors(Object state) {
        final ServerData data = (ServerData) state;
        List<Successor> list = new ArrayList<> ();
        for (int count = 0; count < 100; count += 1){
            int i = new Random().nextInt(data.getNservers());
            if (data.getRequests(i).isEmpty()) {
                continue;
            }
            int j = new Random().nextInt(data.getNservers());
            int k = new Random().nextInt(data.getRequests(i).size());
            if (!data.isPossibleMove(i, j, k)) continue;
            final ServerData dataNew = new ServerData(data);
            dataNew.moveRequest(i, j, k);
            list.add(new Successor(String.format("move i %d j %d k %d",i,j,k), dataNew));
            return list;
        }
        assert(!list.isEmpty());
        //System.out.println(list);
        return list;
    }
}