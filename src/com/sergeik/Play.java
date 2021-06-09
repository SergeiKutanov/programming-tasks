package com.sergeik;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Play {

    public static void main(String[] args) {

        assert 1 == foo(
                Arrays.asList("anything anything", "anything"),
                Arrays.asList("orange", "mango", "banana")
        );
        assert 1 == foo(Arrays.asList("orange", "apple apple", "banana orange apple", "banana"), Arrays.asList("orange"));
    }

    public static int foo(List<String> codeList, List<String> shoppingCart) {

        StringBuilder sb = new StringBuilder();
        for (String s: shoppingCart) {
            sb.append(s);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        String purch = sb.toString();

        int idx = 0;
        for (String code: codeList) {
            List<String> wildcards = Arrays.asList(code.split("anything"));
            if (wildcards.size() == 0) {
                wildcards = Arrays.asList("");
            }
            for (String wildcard: wildcards) {
                if (wildcard.equals(" ") || wildcard.isEmpty()) {
                    while (idx < purch.length() && purch.charAt(idx) != ' ')
                        idx++;
                    idx++;
                } else {
                    int indexOf = purch.indexOf(wildcard, idx);
                    if (indexOf != idx)
                        return 0;
                    idx += purch.length();
                }
            }
        }

        return 1;
    }

}
