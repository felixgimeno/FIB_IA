package representacion;

import aima.search.framework.HeuristicFunction;

import java.util.ArrayList;
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
        
        if (1 == selection) {
            //Valor del estado: tiempo maximo
            return (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get();
        }
        if (2 == selection) {
            //Valor del estado: sumatorio de tiempos al cuadrado
            double n = data.getQuality().stream().mapToDouble((a)->a*a).reduce(0,(a,b)->a+b);
            if (n < 0) {
                n = Double.MAX_VALUE;
            }
            return (double)n;
        }


        double avg = (double)data.getQuality().stream().mapToDouble((a)->a).average().getAsDouble();


        if (3 == selection) {
            //Valor del estado: la diferencia entre el tiempo maximo y la media de los tiempos
            return (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get() - avg;
        }
        if (4 == selection) {
            /*Valor del estado: sumatorio del tiempos al cuadrado menos
             * el cuadrado de la media multiplicado por el numero de servidores
             */
            return (double)data.getQuality().stream().map((a)->a*a).reduce(0,(a,b)->a+b) - avg*avg*data.getNservers();
        }
        if (5 == selection) {
            //Valor del estado: tiempo maximo mas el sumatorio de las diferencias de tiempos de servidor respecto a la media
            double n = (double)data.getQuality().stream().max((a,b)-> Integer.compare(a,b)).get();
            double sum = data.getQuality().stream().mapToDouble((a)->a).reduce(0,(a,b)->a+b);
            sum = Math.abs(sum - avg*data.getNservers());
            return n + sum;
        }       
        return 0.0;
    }
}
