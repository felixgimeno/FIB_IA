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
        //list.add(new Successor("Exactly nothing", data));
        for (Integer i = 0; i < data.getNservers(); i +=1 ){
            if (data.getRequests(i).isEmpty()) continue;
            for (Integer j = 0; j < data.getNservers(); j +=1 ){
                if (!Objects.equals(j, i)){
                    for (Integer k : (ArrayList<Integer>)data.getRequests(i).clone()){
                        if (!data.isPossibleMove(i, j, k)) continue;
                        ServerData dataNew = new ServerData(data);
                        dataNew.moveRequest(i, j, k);
                        list.add(new Successor(String.format("move i %d j %d k %d",i,j,k), dataNew));
                    }
                }
            }    
        }
        assert(!list.isEmpty());
        System.out.println(list);
        return list;
    }
}
