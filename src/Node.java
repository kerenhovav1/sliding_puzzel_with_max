/**
 * Represents a node in a search algorithm for solving a problem.
 */
public class Node {
    private State currentState;
    private Node parent;
    private Action previousAction;

    /**
     * Constructs a node with the specified current state, parent node, and previous action.
     * @param currentState the current state of the node
     * @param parent the parent node of the current node
     * @param previousAction the action that led to the current state from the parent node
     */
    public Node(State currentState, Node parent, Action previousAction) {
        this.currentState = currentState;
        this.parent = parent;
        this.previousAction = previousAction;
    }

    /**
     * Returns the current state of the node.
     * @return the current state
     */
    public State getState() {
        return currentState;
    }

    /**
     * Returns the parent node of the current node.
     * @return the parent node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Returns the previous action that led to the current state from the parent node.
     * @return the previous action
     */
    public Action getAction() {
        return previousAction;
    }

    /**
     * Expands the current node by generating child nodes for each valid action in the current state.
     * @return an array of expanded nodes
     */
    public Node [] expand () {
        Action[] actions = currentState.actions();
        Node[] expendedNodes = new Node[actions.length];
        int validNodeCount = 0;

        // Iterate through all the actions and create child nodes for valid actions
        for (Action action: actions) {
            State newState = currentState.result(action);

            if (!newState.equals(currentState)){
                Node newNode = new Node(newState,this, action);
                expendedNodes[validNodeCount++] = newNode;
            }
        }
        // Create a new array with only the valid child nodes
        Node[] validNode = new Node[validNodeCount];
        for(int i = 0; i < validNodeCount; i++){
            validNode[i] = expendedNodes[i];
        }
        return validNode;
    }

    /**
     * Calculates the Manhattan distance for the current state according to
     * a well known algorithm
     * @return the Manhattan distance - int
     */
    public int calculateManhattanDistance() {
        int distance = 0;
        State currentState = this.currentState;
        int numRows = currentState.getBoard().getNumR();
        int numCols = currentState.getBoard().getNumC();
        Tile[][] board = currentState.getBoard().getBoard();
        Tile[][] targetBoard = currentState.getTargetBoard().getBoard();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int value = board[i][j].getTileNum();
                if (value != (int)'_') {
                    int targetRow = (value - 1) / numRows;
                    int targetCol = (value - 1) % numCols;
                    int rowDiff = i - targetRow;
                    int colDiff = j - targetCol;
                    distance += (rowDiff >= 0 ? rowDiff : -rowDiff) + (colDiff >= 0 ? colDiff : -colDiff);
                }
            }
        }
        return distance;
    }

    /**
     * Calculates the total Hamming distance for the entire board in the current state
     * according to a well known algorithm.
     * @return the total Hamming distance
     */
    public int calculateTotalHammingDistance() {
        int totalDistance = 0;
        Board board = this.currentState.getBoard();
        int numRows = board.getNumR();
        int numCols = board.getNumC();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int tile =  board.getBoard()[row][col].getTileNum();
                if(tile != (int)'_' && tile != row*numCols + col + 1) {
                    totalDistance++;
                }
            }
        }
        return totalDistance;
    }

    /**
     * Calculates the heuristic value using both Manhattan and Hamming distance.
     * The division between Manhattan and Hamming was performed arbitrarily so that we get the
     * best result - Trial and error. We tried multiplying between the values
     * and multiplying by a constant
     * @return the heuristic value
     */
    public double heuristicValue() {
        int manhattanDistance = calculateManhattanDistance();
        int hammingDistance = calculateTotalHammingDistance();
        return  ((manhattanDistance*1.2)*(hammingDistance*2.3));
    }
}


