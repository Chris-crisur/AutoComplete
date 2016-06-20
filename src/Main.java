import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String [] strings = {"sick","bright","butter","best","bread","brick","brighter","brightest","a","cow"};
        AutoComplete auto = new AutoComplete(strings);
        TreeSet<String> testSet = new TreeSet<>();
        testSet.addAll(Arrays.asList(strings));
        testSet.add("zzzzz");
        System.out.println(testSet);
        System.out.println(testSet.subSet("z",true,testSet.ceiling("z"),true));

    }
}

class AutoComplete{

    private TreeSet<String> set;

    public AutoComplete(){
        set = new TreeSet<>();
    }

    public AutoComplete(@NotNull Collection<String> strings){
        set = new TreeSet<>(strings);
    }

    public AutoComplete(@NotNull String [] strings){
        this(Arrays.asList(strings));
    }

    public void add(@NotNull String string){
        set.add(string);
    }

    public void addAll(@NotNull String [] strings){
        addAll(Arrays.asList(strings));
    }

    public void addAll(@NotNull Collection<String> string){
        set.addAll(string);
    }

    public TreeSet<String> get(){
        return set;
    }

    public Set<String> getMatches(@NotNull String prefix){
        char [] prefixChars = prefix.toCharArray();
        int c;
        boolean incremented = false;
        for(c=prefixChars.length-1;c>=0;c--){
            if(prefixChars[c]<'z'){
                prefixChars[c]+=1;
                incremented=true;
                break;
            }
        }
        String upper = new String(prefixChars);
        if(!incremented){
            upper = set.ceiling(upper) == null? upper: set.ceiling(upper);
        }
        return set.subSet(prefix, true, upper,true);
    }

    public Set<String> getMatchesAlt(@NotNull String prefix){
        Set<String> collected = new TreeSet<>();
        Set<String> tail = set.tailSet(prefix,true);
        collected.addAll(tail.stream().filter(s -> s.startsWith(prefix)).collect(Collectors.toList()));
        return collected;
    }

    public Set<String> getMatchesAltJava7(@NotNull String prefix){
        Set<String> collected = new TreeSet<>();
        Set<String> tail = set.tailSet(prefix,true);
        for(String s: tail){
            if(s.startsWith(prefix)){
                collected.add(s);
            }
        }
        return collected;
    }

}