package representacion;

import aima.search.framework.HeuristicFunction;

/**
 * Created by josep on 3/14/16.
 */
public class ServerHeuristicFunction implements HeuristicFunction {
    public double getHeuristicValue(Object state) {
        ServerData data = (ServerData) state;
        //Do shit
        return 0.0;
    }
}
