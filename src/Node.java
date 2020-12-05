import java.util.Arrays;

public class Node {
    int [] boardPosition;
    Node fatherNode;
    double heuristicValue;
    Node ptrToNext;
    static final int[] goalState = {1,2,3,8,0,4,7,6,5};
    int f;

    public void setPtrToNext(Node ptrToNext) {
        this.ptrToNext = ptrToNext;
    }

    public Node(int[] boardPosition, Node fatherNode, int f) {
        this.boardPosition = boardPosition;
        this.fatherNode = fatherNode;
        this.heuristicValue = calculateManhattanDistance(this.boardPosition) + f;
        this.f = f;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }


    private int h(int[] move) {
        int score = 0;
        for(int i = 0; i < move.length; i++) {
            if(move[i] != goalState[i]) {
                score = score + 1;
            }
        }
        return score;
    }
    private double calculateManhattanDistance(int[] possibleMove){
        double distance = 0;
        for (int i = 0; i < possibleMove.length; i++) {
            distance = distance + (Math.abs(possibleMove[i] - goalState[i]));
        }
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return  true;
        if(!(o instanceof Node)) return false;

        Node temp = (Node ) o;
        return (Arrays.equals(this.boardPosition, temp.boardPosition));
    }
}
