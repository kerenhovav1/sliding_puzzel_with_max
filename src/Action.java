import java.sql.SQLOutput;

/**
 * Represents an action in the game, which consists of moving a tile in a specific direction.
 */
public class Action {
    private Tile tileToMove;
    private Direction directionToMove;
    private boolean possible;

    /**
     * Constructs an Action object with the specified tile to move, direction, and possibility.
     * @param tileToMove     the tile to be moved
     * @param direction      the direction of the move
     * @param possible       indicates whether the move is possible or not
     */
    public Action(Tile tileToMove, Direction direction, boolean possible) {
        this.tileToMove = tileToMove;
        this.directionToMove = direction;
        this.possible = possible;
    }
    /**
     * Returns the tile to be moved.
     * @return the tile to be moved
     */
    public Tile getTileToMove() {
        return tileToMove;
    }

    /**
     * Returns the direction of the move.
     * @return the direction of the move
     */
    public Direction getDirectionToMove() {
        return directionToMove;
    }

    /**
     * Returns whether the move is possible or not.
     * @return true if the move is possible, false otherwise
     */
    public boolean getIsPossible() {
        return possible;
    }

    /**
     * Returns a string representation of the action.
     * The format is "Move <tile number> <direction>".
     * @return a string representation of the action
     */
    @Override
    public String toString(){
        return "Move " + tileToMove.getTileNum() +" " + directionToMove.name().toLowerCase();
    }
}
