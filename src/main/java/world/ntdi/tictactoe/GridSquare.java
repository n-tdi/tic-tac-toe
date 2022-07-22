package world.ntdi.tictactoe;

public class GridSquare {
    /*
    Position Variables
     */
    public int state;
    public int pos;

    /**
     * Constructor of a square of the tictactoe game.
     * @param x Position in the board.
     */
    public GridSquare(int x) {
        this.pos = x;
        this.state = -1;
    }

    /**
     * Gets the state of the square.
     * @return Char state of game.
     */
    public char drawSpace() {
        if (this.state == 1) {
            return 'X';
        } else if (this.state == 0) {
            return 'O';
        } else {
            return ' ';
        }
    }

    /**
     * Check's if the space is taken.
     * @return {@code true} if the space is taken, {@code false} otherwise.
     */
    public boolean isOccupied() {
        return this.state != -1;
    }
}
