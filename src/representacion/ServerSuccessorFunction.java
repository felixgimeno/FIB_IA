package representacion;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.List;

/**
 * Created by josep on 3/14/16.
 */
public class ServerSuccessorFunction implements SuccessorFunction {

    public List getSuccessors(Object state) {
        ServerData data = (ServerData) state;
        //Do shit
        return null;
    }
}
