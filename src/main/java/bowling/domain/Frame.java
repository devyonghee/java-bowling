package bowling.domain;

import static bowling.domain.Trial.NOT_PLAYED_SIGN;

import bowling.exception.CannotBowlException;

public interface Frame {

  static Frame of(Round round) {
    if (round.isFinal()) {
      return new FinalFrame();
    }

    return NormalFrame.of(round);
  }

  int getRound();

  Frame getNextFrame();

  Frame roll(int pinCount) throws CannotBowlException;

  Score calculateBonusScore(int bonusBowlCount);

  Score calculateScore();

  String visualize();

  boolean isEnd();


  Frame NULL_FRAME = new Frame() {
    @Override
    public Frame roll(int pinCount) {
      return null;
    }

    @Override
    public int getRound() {
      return 0;
    }

    @Override
    public Frame getNextFrame() {
      return null;
    }

    @Override
    public Score calculateBonusScore(int bonusBowlCount) {
      return null;
    }

    @Override
    public Score calculateScore() {
      return null;
    }

    @Override
    public String visualize() {
      return NOT_PLAYED_SIGN;
    }

    @Override
    public boolean isEnd() {
      return false;
    }
  };
}
