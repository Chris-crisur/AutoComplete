import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Chris on 2016/06/20.
 */
public class AutoCompleteTest {
    AutoComplete auto;
    TreeSet<String> testSet;
    @org.junit.Before
    public void setUp() throws Exception {
        auto = new AutoComplete();
        testSet = new TreeSet<>();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void add() throws Exception {
        auto.add("sick");
        testSet.add("sick");
        assertEquals(testSet,auto.get());
        auto.add("brick");
        testSet.add("brick");
        assertEquals(testSet,auto.get());
        auto.add("zzzzzzz");
        testSet.add("zzzzzzz");
        assertEquals(testSet,auto.get());
        auto.add("aaaaaa");
        testSet.add("aaaaaa");
        assertEquals(testSet,auto.get());
    }

    @org.junit.Test
    public void addAll() throws Exception {
        String [] strings = {"sick","bright","butter","best","bread","brick","brighter","brightest","a","cow"};
        auto.addAll(strings);
        testSet.addAll(Arrays.asList(strings));
        assertEquals(testSet,auto.get());
        List<String> toAdd = new ArrayList<>();
        toAdd.add("test");
        toAdd.add("best");
        toAdd.add("set");
        auto.addAll(toAdd);
        testSet.addAll(toAdd);
        assertEquals(testSet,auto.get());
    }

    @org.junit.Test
    public void get() throws Exception {
        this.add();
    }

    @org.junit.Test
    public void getMatches() throws Exception {
        String [] strings = {"sick","bright","butter","best","bread","brick","brighter","brightest","a","cow"};
        auto.addAll(strings);
        assertEquals("[sick]",auto.getMatches("s").toString());
        assertEquals("[best, bread, brick, bright, brighter, brightest, butter]",auto.getMatches("b").toString());
        assertEquals("[bread, brick, bright, brighter, brightest]",auto.getMatches("br").toString());
        assertEquals("[brick, bright, brighter, brightest]",auto.getMatches("bri").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatches("brig").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatches("brigh").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatches("bright").toString());
        assertEquals(new TreeSet<>(),auto.getMatches("zzzz"));
        assertEquals(new TreeSet<>(),auto.getMatches("aa"));
        assertEquals("[a]",auto.getMatches("a").toString());
        auto.add("zzzzz");
        assertEquals("[zzzzz]",auto.getMatches("z").toString());
        assertEquals("[zzzzz]",auto.getMatches("zzzz").toString());
        assertEquals("[zzzzz]",auto.getMatches("zzzzz").toString());
        assertEquals("[]",auto.getMatches("zzzzzz").toString());
    }

    @org.junit.Test
    public void getMatchesAlt() throws Exception {
        String [] strings = {"sick","bright","butter","best","bread","brick","brighter","brightest","a","cow"};
        auto.addAll(strings);
        assertEquals("[sick]",auto.getMatchesAlt("s").toString());
        assertEquals("[best, bread, brick, bright, brighter, brightest, butter]",auto.getMatchesAlt("b").toString());
        assertEquals("[bread, brick, bright, brighter, brightest]",auto.getMatchesAlt("br").toString());
        assertEquals("[brick, bright, brighter, brightest]",auto.getMatchesAlt("bri").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAlt("brig").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAlt("brigh").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAlt("bright").toString());
        assertEquals(new TreeSet<>(),auto.getMatchesAlt("zzzz"));
        assertEquals(new TreeSet<>(),auto.getMatchesAlt("aa"));
        assertEquals("[a]",auto.getMatchesAlt("a").toString());
        auto.add("zzzzz");
        assertEquals("[zzzzz]",auto.getMatchesAlt("z").toString());
        assertEquals("[zzzzz]",auto.getMatchesAlt("zzzz").toString());
        assertEquals("[zzzzz]",auto.getMatchesAlt("zzzzz").toString());
        assertEquals("[]",auto.getMatchesAlt("zzzzzz").toString());
    }

    @org.junit.Test
    public void getMatchesAltJava7() throws Exception {
        String [] strings = {"sick","bright","butter","best","bread","brick","brighter","brightest","a","cow"};
        auto.addAll(strings);
        assertEquals("[sick]",auto.getMatchesAltJava7("s").toString());
        assertEquals("[best, bread, brick, bright, brighter, brightest, butter]",auto.getMatchesAltJava7("b").toString());
        assertEquals("[bread, brick, bright, brighter, brightest]",auto.getMatchesAltJava7("br").toString());
        assertEquals("[brick, bright, brighter, brightest]",auto.getMatchesAltJava7("bri").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAltJava7("brig").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAltJava7("brigh").toString());
        assertEquals("[bright, brighter, brightest]",auto.getMatchesAltJava7("bright").toString());
        assertEquals(new TreeSet<>(),auto.getMatchesAltJava7("zzzz"));
        assertEquals(new TreeSet<>(),auto.getMatchesAltJava7("aa"));
        assertEquals("[a]",auto.getMatchesAltJava7("a").toString());
        auto.add("zzzzz");
        assertEquals("[zzzzz]",auto.getMatchesAltJava7("z").toString());
        assertEquals("[zzzzz]",auto.getMatchesAltJava7("zzzz").toString());
        assertEquals("[zzzzz]",auto.getMatchesAltJava7("zzzzz").toString());
        assertEquals("[]",auto.getMatchesAltJava7("zzzzzz").toString());
    }
}