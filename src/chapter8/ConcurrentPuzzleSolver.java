package chapter8;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentPuzzleSolver<P,M> {

    private final Puzzle<P,M> puzzle;

    private final ExecutorService exec = Executors.newCachedThreadPool();

    private final ConcurrentHashMap<P,Boolean> seen = new ConcurrentHashMap<>();

    final ValueLatch<Node<P,M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }


    public List<M> solve(){

        try {
            P p = puzzle.initialPosition();

            exec.execute(newTask(p, null, null));

            Node<P,M> solNode = solution.getValue();

            return solNode == null ? null : solNode.asMoveList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            exec.shutdown();
        }

        return null;
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P,M> implements Runnable{


        public SolverTask(P p, M m, Node<P, M> n) {
            super(p, m, n);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos,true) != null) {
                return ;
            }

            if (puzzle.isGoal(pos)) {
                solution.setValue(this);
            }else{
                for (M legalMove : puzzle.legalMoves(pos)) {
                    exec.execute(new SolverTask(puzzle.move(pos, legalMove), legalMove, this));
                }
            }


        }
    }

}
