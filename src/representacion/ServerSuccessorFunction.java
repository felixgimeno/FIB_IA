package representacion;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by josep on 3/14/16.
 */
public class ServerSuccessorFunction implements SuccessorFunction {

    @Override
    public List getSuccessors(Object state) {
        ServerData data = (ServerData) state;
        List list = new ArrayList<ServerData> ();
        list.add(new Successor(null, data));
        //claramente mal, lo hago para probarlo
        return list;
    }
}
