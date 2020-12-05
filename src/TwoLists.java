import java.util.LinkedList;

public class TwoLists {
    LinkedList<Node> openList, closedList;

    public TwoLists() {
        this.openList = new LinkedList<>();
        this.closedList = new LinkedList<>();
    }


    public Node getBestNode() {
        double minHValue  = 999999999;
        int minIndex = 0;
        Node bestNode = this.openList.peekFirst();
        for(int i = 0; i < openList.size(); i ++) {
            if(openList.get(i).getHeuristicValue() < minHValue) {
                bestNode = openList.get(i);
                minHValue = openList.get(i).getHeuristicValue();
                minIndex = i;
            }
        }
        print("--- Contents of Open list ---");
        for(Node n : openList) {
            print(n.boardPosition);
            print("Heuristic Value: " + n.getHeuristicValue() + "\n");
        }
        /* removing best Node*/
        openList.remove(minIndex);
        return bestNode;
    }

    public void insertToOpenList(Node node) {
        openList.add(node);
    }

    public boolean checkIfExists(Node node) {
        return openList.contains(node) || closedList.contains(node);
    }

    public void insertToClosedList(Node node) {
        closedList.add(node);
    }

    private void print(int[] board){
        for (int i = 0; i < board.length; i++) {
            if(i%3 == 0) System.out.println();
            System.out.print(board[i] + "  ");
        }
        System.out.println();
    }

    private static void print(String message) {
        System.out.println(message);
    }

}
