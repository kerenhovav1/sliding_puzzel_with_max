import java.sql.SQLOutput;
import java.util.Arrays;

public class Board {
    //attributes
    private Tile[][] board;
    private  int row;
    private  int col;
    private final char emptySpace = '_';
    private int[] location_of_empty_tile;
    private final String board_as_a_String;



    /**
     * Constructs a Board object with the specified board configuration string.
     * @param string the board configuration string
     */
    public Board(String string) {
        this.board_as_a_String = string;
        this.location_of_empty_tile = new int[2];
        fillBoard(string);

    }

    /**
     * Constructs a copy of the given Board object.
     * @param another the Board object to copy
     */
    public Board(Board another){
        this.board_as_a_String = another.board_as_a_String;
        this.row = another.row;
        this.col = another.col;
        this.board = new Tile[row][col];
        this.location_of_empty_tile = new int[2];
        location_of_empty_tile[0] = another.location_of_empty_tile[0];
        location_of_empty_tile[1] = another.location_of_empty_tile[1];

        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                board[i][j] = new Tile(another.board[i][j].getTileNum());
            }
        }
    }

    /**
     * Returns the tile at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @return the tile at the specified row and column
     */
    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    /**
     * Returns the number of rows in the board.
     * @return the number of rows in the board
     */
    public int getNumR() {
        return row;
    }

    /**
     * Returns the number of columns in the board.
     * @return the number of columns in the board
     */
    public int getNumC() {
        return col;
    }

    /**
     * Returns the character representing the empty space in the board.
     * @return the character representing the empty space
     */
    public char getEmptySpace() { return emptySpace; }

    /**
     * Returns the location of the empty tile as an array of size 2.
     * The first element is the row index, and the second element is the column index.
     * @return the location of the empty tile
     */
    public int[] getLocation_of_empty_tile (){

        int[] copyLocationEmptyTile = new int[2];
        copyLocationEmptyTile[0] = this.location_of_empty_tile[0];
        copyLocationEmptyTile[1]=this.location_of_empty_tile[1];

        return copyLocationEmptyTile;
    }

    /**
     * Sets the location of the empty tile.
     * @param row the row index of the empty tile
     * @param col the column index of the empty tile
     */
    public void setLocation_of_empty_tile(int row, int col) {
        this.location_of_empty_tile[0] = row;
        this.location_of_empty_tile[1] = col;
    }

    /**
     * Returns the entire board as a 2D array of Tile objects.
     * @return the board as a 2D array of Tile objects
     */
    public Tile[][] getBoard() {
        return board;
    }


    /**
     * Fills the board with tiles based on the given string representation of the board configuration.
     * The string should contain rows separated by '|' and elements within each row separated by spaces.
     * The empty space is represented by the character defined as 'emptySpace'.
     * @param string the string representation of the board configuration
     */
    private void fillBoard(String string) {
        String [] rows = string.split("\\|");
        this.row = rows.length;
        String [] cols = rows[0].split(" ");
        this.col = cols.length;

        board = new Tile[row][col];


        for (int i = 0; i <rows.length ; i++) {


            for (int j = 0; j <cols.length ; j++) {
                if(cols[j].charAt(0) == emptySpace) {
                    board[i][j] = new Tile((int)emptySpace);
                    location_of_empty_tile[0] = i;
                    location_of_empty_tile[1] = j;
                }
                else {
                    board[i][j] = new Tile(Integer.parseInt(cols[j]));
                }
            }

            if(i != rows.length - 1) {
                cols = rows[i + 1].split(" ");
            }
        }

    }

    /**
     * Checks if the current board configuration is solvable.
     * The board is considered solvable if the number of inversions is even.
     * (according to research in the internet we conducted.)
     * @return true if the board is solvable, false otherwise
     */
    public boolean isSolvable() {
        int n = row * col;
        int[] flat_board = new int[n];
        int index = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int tileNum = getTile(i,j).getTileNum();
                if(tileNum != (int)'_') {
                    flat_board[index++] = getTile(i,j).getTileNum();
                }
            }
        }
        int inversions = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(flat_board[i] > flat_board[j]) {
                    inversions++;
                }
            }
        }
        return (inversions % 2 == 0);
    }


    //given code
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(this.board, board.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }


    @Override
    public String toString() {
        String builder = new String("");
        for (int row = 0; row < this.row; row++) {
            builder += "\n";
            for (int col = 0; col < this.col; col++) {
                builder+=(board[row][col].getTileNum() + " ");
            }
        }
        return builder;
    }
}