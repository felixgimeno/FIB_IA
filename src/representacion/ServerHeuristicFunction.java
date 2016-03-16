package representacion;

import aima.search.framework.HeuristicFunction;
import java.util.Optional;

/**
 * Created by josep on 3/14/16.
 */
public class ServerHeuristicFunction implements HeuristicFunction {
    int selection = 1;
    public ServerHeuristicFunction(int selection){
        this.selection = selection;
    }
    @Override
    public double getHeuristicValue(Object state) {
        ServerData data = (ServerData) state;
        
        if (1 == selection) {return (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get();}
        if (2 == selection) {return (double)data.getQuality().stream().map((a)->a*a).reduce(0,(a,b)->a+b);}
        double avg = (double)data.getQuality().stream().mapToDouble((a)->a).average().getAsDouble();
        if (3 == selection) {
            return -avg + (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get();
        }
        if (4 == selection) {
            return -avg*avg*data.getNservers() + (double)data.getQuality().stream().map((a)->a*a).reduce(0,(a,b)->a+b);
        }
        return 0.0;
    }
}
