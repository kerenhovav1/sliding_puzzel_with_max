public class Tile {
    private int value;

    /**
     * Constructs a new Tile object with the specified value.
     * @param value the value of the tile
     */
    public Tile(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the tile.
     * @return the value of the tile
     */
    public int getTileNum() {
        return value;
    }

    /**
     * Sets the value of the tile.
     * @param value the new value of the tile
     */
    public void setTileNum(int value){
        this.value = value;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}