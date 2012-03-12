package software.craftsmanship.debruijn.cycles;

public class EmptyCycle<T> extends Cycle<T> {

    @Override
    public Cycle<T> merge(Cycle<T> otherCycle) {
    	return otherCycle;
    }
}
