import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static final int SIZE = 9;
    static final int[][] canMove = {
            {1,3},
            {0,4,2},
            {1,5},
            {0,4,6},
            {1,3,5,7},
            {2,4,8},
            {3,7},
            {6,4,8},
            {7,5}
    };
    static final int[] board = new int[SIZE];
    private static TwoLists lists;

    public static void main(String[] args) {
        String input = input("Enter initial board position");
        //String input = "361478052";
        //String input = "283164705";
        //static final int[] goalState = {1,2,3,8,0,4,7,6,5};

        if(!validate(input)) {
            print("Invalid board");
            return;
        }

        lists = new TwoLists();
        Node rootNode  = new Node(board, null, 0);
        lists.insertToOpenList(rootNode);
        play(rootNode);
    }


    public static void play(Node parent) {
        Node bestNode = lists.getBestNode();

        //print(bestNode.boardPosition);
        if(bestNode.getHeuristicValue() - bestNode.f == 0) {
            print("Goal Found");
            print("Iteration took: " + lists.closedList.size());
            print(bestNode.boardPosition);
            print("Heuristic Value: " + bestNode.heuristicValue);
            return;
        }
        lists.insertToClosedList(bestNode);

        int[][] possibleMoves = generatePossibleMove(bestNode.boardPosition);

        for(int[] move : possibleMoves) {
            Node moveNode = new Node(move, parent, parent.f + 1);
            if(!lists.checkIfExists(moveNode)){
                //print(move);
                lists.insertToOpenList(moveNode);
            }
        }
        //print("----EndMoves----");
        play(bestNode);
    }




    private static int[][] generatePossibleMove(int[] currentBoard) {
        int emptySlot = indexOf(0, currentBoard);
        int[] canMoveInSlot = canMove[emptySlot];
        int[][] possibleMoves = new int[canMoveInSlot.length][];

        int j = 0;
        for (int i: canMoveInSlot) {
            int[] move = currentBoard.clone();
            move[emptySlot] = move[i];
            move[i] = 0;
            possibleMoves[j] = move;
            j++;
        }
        return possibleMoves;
    }

    private static void print(int[] board){
        for (int i = 0; i < board.length; i++) {
            if(i%3 == 0) System.out.println();
            System.out.print(board[i] + "  ");
        }
        System.out.println();
    }

    private static int indexOf(int num, int[] currentBoard){
        for (int i = 0; i < board.length; i++) {
            if(num == currentBoard[i]) return i;
        }
        return -1;
    }

    private static boolean validate(String input) {
        if(input.length() != 9) return false;
        for (int i = 0; i < input.length(); i++) {
            board[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return true;
    }

    private static void print(String message) {
        System.out.println(message);
    }


    public static String input(String message){
        Scanner myObj = new Scanner(System.in);
        System.out.println(message);
        return myObj.nextLine();
    }
}
