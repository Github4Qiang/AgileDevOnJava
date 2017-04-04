package pairprogramming;

/**
 * Create by Polylanger on 2017/3/27 12:53:00
 */
public class Game {

    private int itsCurrentFrame = 1;
    private boolean firstThrowInFrame = true;
    private Scorer scorer = new Scorer();

    public int score() {
        return scoreForFrame(itsCurrentFrame);
    }

    public int scoreForFrame(int theFrame) {
        return scorer.scoreForFrame(theFrame);
    }

    public void add(int pins) {
        scorer.addThrow(pins);
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins) {
        if (lastBallsInFrame(pins)) {
            advanceFrame();
        } else {
            firstThrowInFrame = false;
        }
    }

    private boolean lastBallsInFrame(int pins) {
        return strike(pins) || !firstThrowInFrame;
    }

    private boolean strike(int pins) {
        return firstThrowInFrame && pins == 10;
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
        firstThrowInFrame = true;
    }


}
