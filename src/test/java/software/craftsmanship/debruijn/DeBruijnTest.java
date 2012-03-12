package software.craftsmanship.debruijn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.util.DeBruijnAssert;

@RunWith(Parameterized.class)
public class DeBruijnTest {
    private final int k;
    private final int n;

    public DeBruijnTest(int k, int n){
	this.k = k;
	this.n = n;
    }
    
    @Test
    public void shouldCreateADeBruijnSequence() {
	Word<String> sequence = DeBruijn.overAlphabet("a", "b", "c").sequence(k, n);
	
	DeBruijnAssert.assertDeBruijn(k, n, sequence);
    }
    
    @Parameters
    public static Collection<Object[]> data() {
	List<Object[]> data = new ArrayList<Object[]>();
	data.add(new Object[]{1, 1});
	data.add(new Object[]{2, 1});
	data.add(new Object[]{2, 2});
	data.add(new Object[]{3, 1});
	data.add(new Object[]{3, 2});
	data.add(new Object[]{3, 3});
	return data;
    }
   

}
