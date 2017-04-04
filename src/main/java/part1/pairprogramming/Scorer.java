package part1.pairprogramming;

/**
 * Created by Polylanger on 2017/3/27.
 */
public class Scorer {

    private int[] itsThrows = new int[21];
    private int ball;
    private int itsCurrentThrow = 0;

    public void addThrow(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
    }

    public int scoreForFrame(int theFrame) {
        ball = 0;
        int score = 0;
        for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
            if (strike()) {
                score += 10 + nextTwoBallsForStrike();
                ball++;
            } else if (spare()) {
                score += 10 + nextBallsForSpare();
                ball += 2;
            } else {
                score += twoBallsInFrame();
                ball += 2;
            }
        }
        return score;
    }

    private int nextTwoBallsForStrike() {
        return itsThrows[ball + 1] + itsThrows[ball + 2];
    }

    private boolean strike() {
        return itsThrows[ball] == 10;
    }

    private boolean spare() {
        return itsThrows[ball] + itsThrows[ball + 1] == 10;
    }

    private int twoBallsInFrame() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }

    private int nextBallsForSpare() {
        return itsThrows[ball + 2];
    }
}
