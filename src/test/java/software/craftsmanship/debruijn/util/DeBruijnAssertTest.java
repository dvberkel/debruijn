package software.craftsmanship.debruijn.util;

import static software.craftsmanship.debruijn.combinatorics.Word.word;

import org.junit.Test;

public class DeBruijnAssertTest {

    @Test
    public void shouldCorrectlyAssertADeBruijnSequence() {
        DeBruijnAssert.assertDeBruijn(2, 1, word("a", "b"));
        DeBruijnAssert.assertDeBruijn(2, 2, word("a", "a", "b", "b"));
        DeBruijnAssert.assertDeBruijn(2, 3, word("a", "a", "a", "b", "b", "b", "a", "b"));
    }

}
