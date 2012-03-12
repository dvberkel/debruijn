package software.craftsmanship.debruijn.util;

import static java.lang.Math.pow;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.combinatorics.block.WordYieldBlock;

public class DeBruijnAssert extends Assert {
    public static <U> void assertDeBruijn(int expectedK, int expectedN, Word<U> word) {
	final Set<Word<U>> words = new HashSet<Word<U>>();
	word.subwords(expectedN, new WordYieldBlock<U>(){

	    @Override
	    public void yield(Word<U> word) {
		words.add(word);
	    }
	});
	assertEquals((int) pow(expectedK, expectedN), words.size());
    }
    
}
