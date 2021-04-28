package bowlingRefactor.domain.state.completeState;

import bowlingRefactor.domain.Pin;

import java.util.Arrays;
import java.util.List;

public class Miss extends Complete {

    public Miss(int first, int second) {
        this.pins = Arrays.asList(Pin.of(first), Pin.of(second));
    }

    public Miss(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}