package representacion;

import aima.search.framework.SearchAgent;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ExperimentSA {

    public static void main(String[] args) throws Exception {
        Problem problem = new Problem(
                new ServerData(50, 5, 1234, 200, 5, 1234, 0, 2),
                new ServerSuccessorFunctionR(),
                new ServerGoalTest(),
                new ServerHeuristicFunction(2)
        );
        int steps = 300000;
        int stiter = 100;
        for (double lamb : Arrays.asList(0.000001)) {
            for (int k : Arrays.asList(15)) {
                double ms_avg = 0;
                double hs_avg = 0;
                int n = 1;
                for (int j = 0; j < n; j += 1) {
                    Search search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);

                    long begin = System.nanoTime();
                    SearchAgent agent = new SearchAgent(problem, search);
                    long total = System.nanoTime() - begin;
                    ms_avg += total / 1000000;
                    hs_avg += new ServerHeuristicFunction(2).getHeuristicValue((ServerData) search.getGoalState());

                }
                ms_avg /= n;
                hs_avg /= n;
                System.out.printf("lamb %f k %d tiempo %d heuristico %d \n", lamb, k, (int) (ms_avg), (int) hs_avg);
            }
        }

    }
}
