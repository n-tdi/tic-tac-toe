package world.ntdi.tictactoe;

public class GridSquare {
    public int state;
    public int pos;

    public GridSquare(int x) {
        this.pos = x;
        this.state = -1;
    }

    public char drawSpace() {
        if (this.state == 1) {
            return 'X';
        } else if (this.state == 0) {
            return 'O';
        } else {
            return ' ';
        }
    }

    public boolean isOccupied() {
        return this.state != -1;
    }
}
