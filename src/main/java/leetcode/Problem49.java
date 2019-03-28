package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0 )return null;
        List<List<String>> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap();
        for(String s: strs) {
            int hashCode = hashCode(s);
            if(!map.containsKey(hashCode))
                map.put(hashCode, list.size());
            if(map.get(hashCode) == list.size()) {
                list.add(new ArrayList<>());
                list.get(map.get(hashCode)).add(s);
            } else {
                String first = list.get(map.get(hashCode)).get(0);
                if(first.length() != s.length())  {
                    list.add(new ArrayList<>());
                    list.get(list.size()-1).add(s);
                } else {
                    Map<Character, Integer> tmp = new HashMap<>();
                    for(int i = 0; i < first.length(); i++) {
                        char ll = first.charAt(i);
                        if(!tmp.containsKey(ll)){
                            tmp.put(ll, 0);
                        }
                        tmp.put(ll, tmp.get(ll) +1);
                    }
                    boolean isSame = true;
                    for(int i = 0; i < s.length(); i++) {
                        if(!tmp.containsKey(s.charAt(i)) || tmp.get(s.charAt(i)) == 0) {
                            isSame = false;
                            break;
                        }
                        tmp.put(s.charAt(i), tmp.get(s.charAt(i))-1);
                    }
                    if(isSame) list.get(map.get(hashCode)).add(s);
                    else {
                        list.add(new ArrayList<>());
                        list.get(list.size()-1).add(s);
                    }
                }
            }

        }
        return list;
    }
    public int hashCode(String s) {
        int hashCode = 0;
        for(int i = 0; i < s.length(); i++) {
            hashCode +=s.charAt(i);
        }
        return hashCode;
    }
}
