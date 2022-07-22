package world.ntdi.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public GridSquare[][] grid;
    private final int COLS = 3;
    private final int ROWS = 3;

    public int totalTurns = 0;
    public static GameState gameState;
    public int winner = -1;

    private final Scanner in = new Scanner(System.in);

    public void setup() {
        grid = new GridSquare[COLS][ROWS];
        int position = 1;
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                grid[i][j] = new GridSquare(position);
                position++;
            }
        }
        gameState = GameState.RUNNING;

        playGame();
    }

    /**
     * Draws the board to the console
     */
    public void drawBoard() {
        String board =
                " 1 | 2 | 3 " + "\n" +
                "___|___|___" + "\n" +
                " 4 | 5 | 6 " + "\n" +
                "___|___|___" + "\n" +
                " 7 | 8 | 9 " + "\n" +
                "   |   |   " + "\n";

        int pos = 1;
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                board = board.replace("" + pos, grid[i][j].drawSpace() + "");
                pos++;
            }
        }

        System.out.println(board);
    }

    public void makeMove() {
        if (getPlayer() == 'O') {
            boolean validChoice = false;
            while (!validChoice) {
                System.out.println("Player " + getPlayer() + ", Choose your spot: ");
                int spot = in.nextInt();
                if (spot > 0 && spot < 10) {
                    for (int i = 0; i < COLS; i++) {
                        for (int j = 0; j < ROWS; j++) {
                            if (grid[i][j].pos == spot) {
                                if (!(grid[i][j].isOccupied())) {
                                    grid[i][j].state = totalTurns % 2;
                                    validChoice = true;
                                    totalTurns++;
                                    checkWin(i, j, grid[i][j].state);
                                } else {
                                    System.out.println("Spot already taken, try again.");
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            }
        } else {
            boolean notTaken = false;
            while (!notTaken) {
                int randomSpot = (int) (Math.random() * 9) + 1;
                for (int i = 0; i < COLS; i++) {
                    for (int j = 0; j < ROWS; j++) {
                        if (grid[i][j].pos == randomSpot) {
                            if (!(grid[i][j].isOccupied())) {
                                grid[i][j].state = totalTurns % 2;
                                notTaken = true;
                                totalTurns++;
                                checkWin(i, j, grid[i][j].state);
                            }
                        }
                    }
                }
            }
        }
    }

    public void gameOver() {
        drawBoard();
        System.out.println("\nGame over!");
        if (winner == 0) {
            System.out.println("Player O wins!");
        } else if (winner == 1) {
            System.out.println("Player X wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void playGame() {
        while (gameState == GameState.RUNNING) {
            if (getPlayer() == 'O')
                drawBoard();
            makeMove();
        }
        if (gameState == GameState.OVER) {
            gameOver();
        }
    }

    /**
     * Returns the current player.
     * @return Player 'O' or 'X'
     */
    public char getPlayer() {
        if (totalTurns % 2 == 0) {
            return 'O';
        } else {
            return 'X';
        }
    }

    /**
     * Checks if the game is over and a player has won.
     * @param x X coordinate of the square
     * @param y Y coordinate of the square
     * @param turn Current player's turn
     */
    public void checkWin(int x, int y, int turn) {
        int colWin = 0;
        int rowWin = 0;
        int diag1Win = 0;
        int diag2Win = 0;

        for (int next = 0; next < 3; next++) {
            if (grid[x][next].state == turn) {
                colWin++;
            }
            if (grid[next][y].state == turn) {
                rowWin++;
            }
            if (grid[next][next].state == turn) {
                diag1Win++;
            }
            if (grid[next][2 - next].state == turn) {
                diag2Win++;
            }
        }

        if (colWin == 3 || rowWin == 3 || diag1Win == 3 || diag2Win == 3) {
            winner = turn;
            if (winner != -1) {
                gameState = GameState.OVER;
            }
        }
        if (totalTurns == 9) {
            gameState = GameState.OVER;
        }
    }
}
