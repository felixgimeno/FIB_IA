package representacion;

import aima.search.framework.HeuristicFunction;
import java.util.Optional;

/**
 * Created by josep on 3/14/16.
 */
public class ServerHeuristicFunction implements HeuristicFunction {
    public double getHeuristicValue(Object state) {
        ServerData data = (ServerData) state;
        //criterio calidad 1a
        return (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get();
    }
}
