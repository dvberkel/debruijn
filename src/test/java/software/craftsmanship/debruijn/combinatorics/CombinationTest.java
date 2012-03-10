package software.craftsmanship.debruijn.combinatorics;

import static org.junit.Assert.assertEquals;
import static software.craftsmanship.debruijn.combinatorics.Combination.combination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CombinationTest<T> {
    private final Combination<T> start;
    private final T letter;
    private final Combination<T> expectedAfterAppend;
    private final Combination<T> expectedAfterPipe;

    public CombinationTest(Combination<T> start, T letter, Combination<T> expectedAfterAppend,
            Combination<T> expectedAfterPipe) {
        this.start = start;
        this.letter = letter;
        this.expectedAfterAppend = expectedAfterAppend;
        this.expectedAfterPipe = expectedAfterPipe;
    }

    @Test
    public void shouldCorrectlyAppendALetter() {
        Combination<T> actual = start.append(letter);

        assertEquals(expectedAfterAppend, actual);
    }

    @Test
    public void shouldCorrectlyPipeALetter() {
        Combination<T> actual = start.pipe(letter);

        assertEquals(expectedAfterPipe, actual);
    }

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{combination("a"), "a", combination("a", "a"), combination("a")});
        data.add(new Object[]{combination("a"), "b", combination("a", "b"), combination("b")});
        data.add(new Object[]{combination("a", "a"), "a", combination("a", "a", "a"), combination("a", "a")});
        data.add(new Object[]{combination("a", "a"), "b", combination("a", "a", "b"), combination("a", "b")});
        data.add(new Object[]{combination("a", "b"), "a", combination("a", "b", "a"), combination("b", "a")});
        data.add(new Object[]{combination("a", "b"), "b", combination("a", "b", "b"), combination("b", "b")});
        data.add(new Object[]{combination("b", "a"), "a", combination("b", "a", "a"), combination("a", "a")});
        data.add(new Object[]{combination("b", "a"), "b", combination("b", "a", "b"), combination("a", "b")});
        data.add(new Object[]{combination("b", "b"), "a", combination("b", "b", "a"), combination("b", "a")});
        data.add(new Object[]{combination("b", "b"), "b", combination("b", "b", "b"), combination("b", "b")});
        return data;
    }
}
