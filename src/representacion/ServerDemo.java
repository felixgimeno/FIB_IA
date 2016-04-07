package representacion;

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
            System.out.println("Bienvenido");
            boolean saORhc = false;
            final Integer heuristicCriteria = 1; //unicos valores 1 y 5
            final int criterioGeneracionEstadosIniciales = 2; //fijo para siempre
            final int randomSeed = 1; //seed de generacion inicial de asignaciones
            Problem problem;
            Search search;

            if (saORhc){
                problem = new Problem(
                               //ns, nrep, seed1, nu, nreq, seed2, criteria
                    new ServerData(50, 5, 1234, 200, 5, 1234,criterioGeneracionEstadosIniciales, randomSeed),
                    new ServerSuccessorFunction(heuristicCriteria),
                    new ServerGoalTest(),
                    new ServerHeuristicFunction(heuristicCriteria)
                );
                search = new HillClimbingSearch();
            } else {
                final int saSeed = 2; //seed para el random de SA
                problem = new Problem(
                    new ServerData(50, 5, 1234, 200, 5, 1234,criterioGeneracionEstadosIniciales, randomSeed),
                    new ServerSuccessorFunctionR(saSeed),
                    new ServerGoalTest(),
                    new ServerHeuristicFunction(heuristicCriteria)
                );
                int steps = 100000;
                int stiter = 100;
                int k = 20;
                double lamb =  0.005;
                search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
            }
            System.out.println("Procederemos a ejecutar la busqueda");
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
            System.out.println("Total: " + total/1000000 + " ms");

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
