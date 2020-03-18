package chapter8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SequentialPuzzleSolver<P,M> {

    private final Puzzle<P,M> puzzle;

    private final Set<P> seen = new HashSet<>();


    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve(){
        P pos = puzzle.initialPosition();
        return search(new Node<P, M>(pos, null, null));
    }

    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos)) {
                return node.asMoveList();
            }else{
                for (M legalMove : puzzle.legalMoves(node.pos)) {
                    Node<P, M> childNode = new Node<>(puzzle.move(node.pos, legalMove), legalMove, node);
                    List<M> result = search(childNode);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return  null;
    }

}
