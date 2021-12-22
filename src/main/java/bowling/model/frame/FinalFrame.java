package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.state.Ready;
import bowling.model.state.States;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States(new Ready());
    }

    @Override
    public Frame bowl(int knockedDownPin) {
        whenFinishResetStateForBonusBowl();
        states.add(states.bowl(Pins.knockedDown(knockedDownPin)));
        return this;
    }

    private void whenFinishResetStateForBonusBowl() {
        if(states.isFinish()) {
            states.add(new Ready());
        }
    }
}