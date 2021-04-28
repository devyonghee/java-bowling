package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int LAST_ROUND = 10;

    private final User user;
    private final List<FrameStrategy> frames;

    public Frames(User user) {
        this.user = user;
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public User getUser() {
        return user;
    }

    public List<FrameStrategy> getFrames() {
        return frames;
    }

    public FrameStrategy frame(int index) {
        return frames.get(index);
    }

    public void proceedRound(int frameNumber, PinNumber pinNumber) {
        frames.get(frameNumber - 1).play(pinNumber);

        if (!hasRemainTurn(frameNumber) && frameNumber < LAST_ROUND) {
            frames.add(frames.get(frameNumber - 1).newNextFrame(frameNumber));
        }
    }

    public boolean hasRemainTurn(int frameNumber) {
        return frames.get(frameNumber - 1).hasNext();
    }

    public void calculateScore(int frameNumber) {
        user.recordScore(frames.get(0), null);
        for (int i = 1; i < frameNumber; i++) {
            user.recordScore(frames.get(i), frames.get(i -1));
        }
    }
}