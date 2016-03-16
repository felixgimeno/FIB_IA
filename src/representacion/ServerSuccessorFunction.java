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
        list.add(new Successor("Exactly nothing", data));
        for (Integer i = 0; i < data.getNservers(); i +=1 ){
            for (Integer j = 0; j < data.getNservers(); j +=1 ){
                if (!Objects.equals(j, i)){
                    for (Integer k : data.getRequests(i)){
                        ServerData dataNew = new ServerData(data);
                        dataNew.moveRequest(i, j, k);
                        list.add(new Successor("move i {} j {} k {}".format(i.toString(),j.toString(),k.toString()), data));
                    }
                }
            }    
        }
        assert(!list.isEmpty());
        
        //claramente mal, lo hago para probarlo
        return list;
    }
}
