package representacion;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

/**
 * Created by josep on 3/14/16.
 */
public class ServerSuccessorFunction implements SuccessorFunction {

    @Override
    public List getSuccessors(Object state) {
        ServerData data = (ServerData) state;
        List<Successor> list = new ArrayList<> ();
        for (Integer i = 0; i < data.getNservers(); i +=1 ){
            if (data.getRequests(i).isEmpty()) continue;
            for (Integer j = 0; j < data.getNservers(); j +=1 ){
                if (!Objects.equals(j, i)){
                    for (Integer k : (ArrayList<Integer>)data.getRequests(i)){
                        /*if (!data.isPossibleMove(i, j, k)) continue;
                        ServerData dataNew = new ServerData(data);
                        dataNew.moveRequest(i, j, k);
                        list.add(new Successor(String.format("move s1 %d s2 %d req %d",i,j,k), dataNew));*/

                        //añadir intercambios
                        for (Integer l : (ArrayList<Integer>)data.getRequests(j)) {
                            if (data.isPossibleSwap(i, k, j, l)) {
                                ServerData dataNew2 = new ServerData(data);
                                dataNew2.swapRequest(i, k, j, l);
                                list.add(new Successor(String.format("swap s1 %d req1 %d s2 %d req2 %d", i, k, j, l), dataNew2));
                            }
                            else if (!data.isPossibleMove(i, j, k)) {
                                ServerData dataNew = new ServerData(data);
                                dataNew.moveRequest(i, j, k);
                                list.add(new Successor(String.format("move s1 %d s2 %d req %d",i,j,k), dataNew));
                            }
                        }
                    }
                }
            }    
        }
        assert(!list.isEmpty());
        //System.out.println(list);
        return list;
    }
}
