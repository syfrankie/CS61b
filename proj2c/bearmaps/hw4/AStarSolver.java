package bearmaps.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import bearmaps.proj2ab.*;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private ExtrinsicMinPQ<Vertex> Fringe;
    private SolverOutcome result;
    private int count;
    private double time;
    private double finalweight;
    private List<Vertex> solution;

    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Fringe = new ArrayHeapMinPQ<>();
        solution = new ArrayList<>();
        Fringe.add(start, 0.0 + input.estimatedDistanceToGoal(start, end));
        count = 0;
        finalweight = 0.0;

        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(start, 0.0);
        Stopwatch sw = new Stopwatch();

        while (Fringe.size() > 0 && !Fringe.getSmallest().equals(end) && sw.elapsedTime() < timeout) {
            Vertex curr = Fringe.removeSmallest();
            List<WeightedEdge<Vertex>> eList = input.neighbors(curr);
            for (WeightedEdge<Vertex> e: eList) {
                Vertex q = e.to();
                double w = e.weight();
                double h = input.estimatedDistanceToGoal(q, end);
                relax(curr, q, w, h);
            }
            count += 1;
        }

        time = sw.elapsedTime();

        if (Fringe.size() == 0) {
            result = SolverOutcome.UNSOLVABLE;
        } else if (time >= timeout) {
            result = SolverOutcome.TIMEOUT;
        } else {
            result = SolverOutcome.SOLVED;
            finalweight = distTo.get(end);
            Vertex temp = end;
            while (temp != null) {
                solution.add(0, temp);
                temp = edgeTo.get(temp);
            }
        }

    }

    private void relax(Vertex p, Vertex q, double w, double h) {
        if (!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            if (Fringe.contains(q)) {
                Fringe.changePriority(q, distTo.get(q) + h);
            } else {
                Fringe.add(q, distTo.get(q) + h);
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return result;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        if (result == SolverOutcome.SOLVED) {
            return finalweight;
        }
        return 0.0;
    }

    @Override
    public int numStatesExplored() {
        return count;
    }

    @Override
    public double explorationTime() {
        return time;
    }
}
