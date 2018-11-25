package fall2018.csc207_project.GameCenter;

import java.util.List;

import fall2018.csc207_project.SlidingTileGame.SlidingTileGame;

public class GameFactory {
    public static final String SLIDINGTILEGAME = "slidingTileGame";
    public static final String GAME2048 = "2048Game";
    public static final String MINESWEEPER = "minesweeperGame";

    public Game createGame(String gameName, List<Object> settings) {
        if(gameName.equals(SLIDINGTILEGAME))
            return new SlidingTileGame(settings);
        else if(gameName.equals(GAME2048)) {
            return null;
        }
        else if(gameName.equals(MINESWEEPER)) {
            return null;
        }
        return null;
    }
}
