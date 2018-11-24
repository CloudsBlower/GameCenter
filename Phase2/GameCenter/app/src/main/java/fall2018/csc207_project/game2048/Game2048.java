package fall2018.csc207_project.game2048;

import java.io.Serializable;

public class Game2048 implements Serializable {

    private static final long serialVersionUID = 772895212901L;

    private Board board;
    private int complexity;
    private int undoSteps;
    private int score;
    private int highestTile;
    private boolean moveAvailable;

//    Game2048(Board board){
//        this.board = board;
//        this.complexity = board.getBoardSize();
//        this.score = 0;
//        //TODO: LATER
//    }

    Game2048(int complexity){
        this.board = new Board(complexity);
        this.complexity = complexity;
        this.score = 0;
    }

    public int getHighestTile(){return highestTile;}

    public int getComplexity() {
        return complexity;
    }

    public Board getBoard(){
        return board;
    }

    private boolean doMove(int countDownFrom, int yDirection, int xDirection) {
        boolean moved = false;
        int target = 2048;

        for (int i = 0; i < complexity * complexity; i++) {
            int j = Math.abs(countDownFrom - i);

            int r = j / complexity;
            int c = j % complexity;

            if (board.getTile(r,c).getNum() == 0)
                continue;

            int nextR = r + xDirection;
            int nextC = c + yDirection;

            while (nextR >= 0 && nextR < complexity && nextC >= 0 && nextC < complexity) {

                Tile next = board.getTile(nextR,nextC);
                Tile curr = board.getTile(r,c);

                if (next.getNum() == 0) {

                    if (moveAvailable)
                        return true;

                    next.setNum(curr.getNum());
                    curr.setNum(0);
                    r = nextR;
                    c = nextC;
                    nextR += xDirection;
                    nextC += yDirection;
                    moved = true;

                } else if (next.equals(curr) && !next.getMergedState()) {

                    if (moveAvailable)
                        return true;

                    int value = next.merge(curr);
                    if (value > highestTile)
                        highestTile = value;
                    score += value;
                    board.getTile(r,c).setNum(0);
                    moved = true;
                    break;

                } else
                    break;
            }
        }


        if (moved) {
            if (highestTile < target) {
                board.clearMerged();
                board.addRandomTile();
                if (!movesAvailable()) {
                    System.out.println("Game Over!");
                }
            } else if (highestTile == target)
                System.out.println("You Won!");

        }

        System.out.println("Your Score: -----------------------------> "+score+"!!!!!!!!!!!!!!!!!!!");

        return moved;
    }

    public boolean touchUp() {
        return doMove(0,-1,0);
    }

    public boolean touchDown() {
        return doMove(complexity * complexity - 1,1,0);
    }

    public boolean touchLeft() {
        return doMove(0,0,-1);
    }

    public boolean touchRight() {
        return doMove(complexity * complexity - 1, 0, 1);
    }


    public boolean movesAvailable(){
        moveAvailable = true;
        boolean hasMoves = touchUp() || touchDown() || touchLeft() || touchRight();
        moveAvailable = false;
        return hasMoves;
    }


}