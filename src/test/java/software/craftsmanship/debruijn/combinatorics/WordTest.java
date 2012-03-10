package software.craftsmanship.debruijn.combinatorics;

import static org.junit.Assert.assertEquals;
import static software.craftsmanship.debruijn.combinatorics.Word.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class WordTest<T> {
    private final Word<T> start;
    private final T letter;
    private final Word<T> expectedAfterAppend;
    private final Word<T> expectedAfterPipe;

    public WordTest(Word<T> start, T letter, Word<T> expectedAfterAppend,
            Word<T> expectedAfterPipe) {
        this.start = start;
        this.letter = letter;
        this.expectedAfterAppend = expectedAfterAppend;
        this.expectedAfterPipe = expectedAfterPipe;
    }

    @Test
    public void shouldCorrectlyAppendALetter() {
        Word<T> actual = start.append(letter);

        assertEquals(expectedAfterAppend, actual);
    }

    @Test
    public void shouldCorrectlyPipeALetter() {
        Word<T> actual = start.pipe(letter);

        assertEquals(expectedAfterPipe, actual);
    }

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{word("a"), "a", word("a", "a"), word("a")});
        data.add(new Object[]{word("a"), "b", word("a", "b"), word("b")});
        data.add(new Object[]{word("a", "a"), "a", word("a", "a", "a"), word("a", "a")});
        data.add(new Object[]{word("a", "a"), "b", word("a", "a", "b"), word("a", "b")});
        data.add(new Object[]{word("a", "b"), "a", word("a", "b", "a"), word("b", "a")});
        data.add(new Object[]{word("a", "b"), "b", word("a", "b", "b"), word("b", "b")});
        data.add(new Object[]{word("b", "a"), "a", word("b", "a", "a"), word("a", "a")});
        data.add(new Object[]{word("b", "a"), "b", word("b", "a", "b"), word("a", "b")});
        data.add(new Object[]{word("b", "b"), "a", word("b", "b", "a"), word("b", "a")});
        data.add(new Object[]{word("b", "b"), "b", word("b", "b", "b"), word("b", "b")});
        return data;
    }
}
