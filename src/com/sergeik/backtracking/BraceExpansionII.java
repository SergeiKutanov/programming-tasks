package com.sergeik.backtracking;

import java.util.*;

public class BraceExpansionII {

    private static Stack<String> stack = new Stack<>();
    private static List<String> res = new ArrayList<>();
    private static Set<String> set = new HashSet<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"a","b","xc","xdy","xey"},
                braceExpansionII("{a,b},x{c,{d,e}y}").toArray()
        );
        assert Arrays.equals(
                new String[] {"ac", "ad", "ae", "bc", "bd", "be"},
                braceExpansionII("{a,b}{c,{d,e}}").toArray()
        );
    }

    public static List<String> braceExpansionII(String expression) {
        String s = expression;
        char preSign = ',';
        Stack<List<String>> stack = new Stack<>();// Save List<String>
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // case 1. {...} recursive, stack.operate(resList) by preSign
            if (c == '{'){
                int j = i, p = 1;
                while (s.charAt(j) != '}' || p != 0){
                    j++;
                    if (s.charAt(j) == '{') p++;
                    if (s.charAt(j) == '}') p--;
                }
                List<String> slist = braceExpansionII(s.substring(i+1, j));
                if (preSign == '*'){
                    stack.push(merge(stack.pop(), slist));
                }
                else stack.push(slist);
                i = j;
                //default preSign is *
                preSign = '*';
            }
            // case 2 letter operate by preSign
            else if (Character.isLetter(c)){
                List<String> slist = new ArrayList<>();
                slist.add(""+c);
                if (preSign == '*'){
                    stack.push(merge(stack.pop(), slist));
                }
                else stack.push(slist);
                // //default preSign is *
                preSign = '*';
            }
            // case 3. if  == ", ", preSign is  plus, (default preSign is *);
            if (c ==',' || i == s.length()-1){
                preSign = ',';
            }
        }
        // output stack to one dimesion list;
        List<String> res = new ArrayList<>();
        while(!stack.isEmpty()){
            for (String l : stack.pop())
                if (!res.contains(l))res.add(l);
        }
        // sort by lexi-order
        Collections.sort(res);
        return res;
    }
    // multiply operation of 2 List<letter>
    public static List<String> merge(List<String> list1, List<String> list2){
        List<String> res = new ArrayList<>();
        for (String l1 : list1){
            for (String l2 : list2){
                res.add(l1+l2);
            }
        }
        return res;
    }

    private static List<String> solution(String expression) {
        stack.push(expression);
        iterDfs();
        Collections.sort(res);
        return res;
    }

    private static void iterDfs(){
        while(!stack.isEmpty()) {
            String str = stack.pop();
            if (str.indexOf('{') == -1) {
                if (!set.contains(str)) {
                    res.add(str);
                    set.add(str);
                }
                continue;
            }

            int i = 0, l = 0, r = 0;
            while (str.charAt(i) != '}') {
                if (str.charAt(i++) == '{')
                    l = i - 1;
            }
            r = i;

            String before = str.substring(0, l);
            String after = str.substring(r + 1);
            String[] args = str.substring(l + 1, r).split(",");

            for (String s : args) {
                sb.setLength(0);
                stack.push(
                        sb.append(before).append(s).append(after).toString()
                );
            }
        }
    }

}
