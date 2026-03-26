public class SolvableBoard extends SudokuBoard{
    private int[][] unsolvedBoard;
    private int mistakes, maxMistakes, numRemovedValues;
    private char difficulty;
    public SolvableBoard(String d){
        super();
        difficulty = d.toLowerCase().charAt(0);
        setDifficulty(difficulty);
        unsolvedBoard = createSolvableBoard(board);
        mistakes = 0;
    }
    
    public int[][] createSolvableBoard(int[][] originalBoard){
        int[][] unsolved = new int[originalBoard.length][originalBoard[0].length];
        for (int i = 0; i < originalBoard.length; i++) { // references for some reason
            unsolved[i] = originalBoard[i].clone(); 
        }
        int emptyCount = 0;
        while (emptyCount < numRemovedValues){
            outer:
            for (int r = 0; r < originalBoard.length; r++){
                for (int c = 0; c < originalBoard[0].length; c++){
                    if (unsolved[r][c] != 0 && (int)(Math.random() * 4) == 1){
                        unsolved[r][c] = 0;
                        emptyCount++;
                        if (emptyCount >= numRemovedValues) break outer;
                    }
                }
            }
        }
        return unsolved;
    }

    public void setDifficulty(char difficulty){
        switch (difficulty){
            case 'e':
                numRemovedValues = 20;
                maxMistakes = 3;
                break;
            case 'n':
                numRemovedValues = 30;
                maxMistakes = 3;
                break;
            case 'h':
                numRemovedValues = 40;
                maxMistakes = 2;
                break;
            case 'i':
                numRemovedValues = 50;
                maxMistakes = 1;
                break;
            default: // same thing as normal
                numRemovedValues = 30;
                maxMistakes = 3;
                System.out.println("The difficulty has been defaulted to normal.");
                break;
        }
    }

    public boolean isCorrectGuess(int guessedValue, int row, int col){
        if (board[row][col] == guessedValue){
            setBoardValue(guessedValue, row, col);
            return true;
        }
        mistakes++;
        return false;
    }

    public int getMistakes(){
        return mistakes;
    }

    public int getMaxMistakes(){
        return maxMistakes;
    }
    
    public int[][] getBoard(){
        return board;
    }

    public int[][] getUnsolvedBoard(){
        return unsolvedBoard;
    }

    public void setBoardValue(int value, int row, int col){
        unsolvedBoard[row][col] = value;
    }

    public boolean isLost(){
        return mistakes >= maxMistakes;
    }

    public boolean isWon(){
        int[] availableCoords = findNextEmpty(unsolvedBoard);
        return availableCoords[0] == -1 && mistakes < maxMistakes;
    }
    
    public boolean isOccupied(int row, int col){
        return unsolvedBoard[row][col] != 0;
    }
}
