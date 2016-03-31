package representacion;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * copipasteado de private static void eightPuzzleDLSDemo() ...
 *
 * Since 2016_03_16
 *
 * @author felix.axel.gimeno
 *
 */
public class ServerDemo {

    public static void main(String[] args) {
        try {
            boolean saORhc = true;
            final Integer heuristicCriteria = 1;
            final int criterioGeneracionEstadosIniciales = 1; 
            Problem problem;
            Search search;

            if (saORhc){
                problem = new Problem(
                               //ns, nrep, seed1, nu, nreq, seed2, criteria
                    new ServerData(50, 5, 1234, 200, 5, 1234,criterioGeneracionEstadosIniciales),
                    new ServerSuccessorFunction(),
                    new ServerGoalTest(),
                    new ServerHeuristicFunction(heuristicCriteria)
                );
                search = new HillClimbingSearch();
            } else {
                problem = new Problem(
                    new ServerData(50, 5, 1234, 200, 5, 1234,criterioGeneracionEstadosIniciales),
                    new ServerSuccessorFunctionR(),
                    new ServerGoalTest(),
                    new ServerHeuristicFunction(heuristicCriteria)
                );               
                search = new SimulatedAnnealingSearch();
            }

            long begin = System.nanoTime();
 
            SearchAgent agent = new SearchAgent(problem, search);

            long total = System.nanoTime() - begin;

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            ServerData goal = (ServerData) search.getGoalState();
            if (goal != null){
                System.out.println(goal.getQuality());
                System.out.println(new ServerHeuristicFunction(heuristicCriteria).getHeuristicValue(goal));
                double n = goal.getQuality().stream().mapToDouble((a)->a).reduce(0,(a,b)->a+b);
                System.out.println("Sum of transmisison times: "+n);
            }
            int min = (int)(total/1000000000)/60;
            int sec = (int)(total/1000000000)%60;
            System.out.println("Time: " + min + " min, " + sec + " s");
            System.out.println("Total: " + total + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
    }

    private static void printActions(List actions) {
        actions.stream().map((action1) -> action1.toString()).
                forEach((action) -> {
                    System.out.println(action);
                });
    }
}
