package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.NotThrown;
import bowling.utility.Assert;

import java.util.Objects;

public final class FrameState {

    private static final NotThrown DEFAULT_STATE = NotThrown.INSTANCE;

    private final BallState state;
    private final Score score;

    private FrameState(BallState state, Score score) {
        Assert.notNull(state, "state must not be null");
        Assert.notNull(score, "score must not be null");
        this.state = state;
        this.score = score;
    }

    public static FrameState init() {
        return from(DEFAULT_STATE);
    }

    public static FrameState from(BallState state) {
        Assert.notNull(state, "state must not be null");
        return of(state, Score.init(state.restCount()));
    }

    public static FrameState of(BallState state, Score score) {
        return new FrameState(state, score);
    }

    public boolean isEndState() {
        return state.isEnd();
    }

    public FrameState nextState(Pins pins) {
        validateState();
        BallState nextState = this.state.state(pins);
        return of(nextState, score.changeRestCount(nextState.restCount()));
    }

    public boolean hasRestCount() {
        return score.hasRestCount();
    }

    public FrameState addScore(Pins pins) {
        return of(state, score.addValue(pins.count()));
    }

    public Score score() {
        return score;
    }

    public BallState state() {
        return state;
    }

    private void validateState() {
        if (isEndState()) {
            throw new IllegalStateException(String.format("frame state(%s) is already end state", state));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameState that = (FrameState) o;
        return Objects.equals(state, that.state) && Objects.equals(score, that.score);
    }

    @Override
    public String toString() {
        return "FrameState{" +
                "state=" + state +
                ", score=" + score +
                '}';
    }
}