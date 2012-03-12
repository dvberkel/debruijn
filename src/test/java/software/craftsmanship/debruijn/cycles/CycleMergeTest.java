package software.craftsmanship.debruijn.cycles;

import static org.junit.Assert.assertEquals;
import static software.craftsmanship.debruijn.combinatorics.Word.word;
import static software.craftsmanship.debruijn.cycles.Cycle.cycleOver;
import static software.craftsmanship.debruijn.cycles.util.GraphFactory.cyclicGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import software.craftsmanship.debruijn.combinatorics.Word;

@RunWith(Parameterized.class)
public class CycleMergeTest {
    private final String[] aSequence;
    private final String[] otherSequence;
    private Cycle<String> cycle;
    private final Word<String> expectedWord;

    public CycleMergeTest(String[] aSequence, String[] otherSequence, Word<String> expectedWord){
	this.aSequence = aSequence;
	this.otherSequence = otherSequence;
	this.expectedWord = expectedWord;
    }
    
    @Before
    public void createCycle() {
	Cycle<String> aCycle = cycleOver(cyclicGraph(aSequence)).startingAt(word(aSequence[0]));
	Cycle<String> otherCycle = cycleOver(cyclicGraph(otherSequence)).startingAt(word(otherSequence[0]));
	this.cycle = aCycle.merge(otherCycle);
    }
    
    @Test
    public void shouldCorrectlyMergeTwoCycles() {
	CycleWordCollector<String> collector = new CycleWordCollector<String>();
	
	cycle.allEdges(collector);
	
	assertEquals(expectedWord, collector.word());
	
    }
    
    @Parameters
    public static Collection<Object[]> data(){
	List<Object[]> data = new ArrayList<Object[]>();
	data.add(new Object[]{new String[]{"a", "b", "c"}, new String[] {"b", "d"}, word("a", "b", "d", "b", "c")});
	data.add(new Object[]{new String[]{"a", "b", "c"}, new String[] {"c", "d"}, word("a", "b", "c", "d", "c")});
	data.add(new Object[]{new String[]{"a", "b", "c"}, new String[] {"b", "d", "e"}, word("a", "b", "d", "e", "b", "c")});
	data.add(new Object[]{new String[]{"a", "b", "c"}, new String[] {"c", "d", "e"}, word("a", "b", "c", "d", "e", "c")});
	data.add(new Object[]{new String[]{"a", "b", "c", "d"}, new String[] {"b", "d", "e", "c", "f"}, word("a", "b", "d", "e", "c", "f", "b", "c", "d")});
	return data;
    }

}
