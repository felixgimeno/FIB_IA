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
            final Integer heuristicCriteria = 1;
            final int criterioGeneracionEstadosIniciales = 1; 
            Problem problem = new Problem(
                    new ServerData(11, 5, 11432342, 10, 11, 11433411,criterioGeneracionEstadosIniciales),
                    new ServerSuccessorFunction(),
                    new ServerGoalTest(),
                    new ServerHeuristicFunction(heuristicCriteria)
            );
            //Search search = new DepthLimitedSearch(3);
            //Search search = new GreedyBestFirstSearch(new GraphSearch());
            Search search = new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            ServerData goal = (ServerData) search.getGoalState();
            System.out.println(goal.getQuality());
            System.out.println(new ServerHeuristicFunction(heuristicCriteria).getHeuristicValue(goal));
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
        actions.stream().map((action1) -> (String) action1).
                forEach((action) -> {
                    System.out.println(action);
                });
    }
}
