package com.sergeik.backtracking;

import com.sergeik.utils.Compare;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
 * You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single
 * dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and
 * "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 */
public class RestoreIPAddress {

    public static void main(String[] args) {
        assert Compare.compareObjArr(
                solution("25525511135").toArray(),
                Arrays.asList("255.255.11.135", "255.255.111.35").toArray()
        );

        assert Compare.compareObjArr(
                solution("0000").toArray(),
                Arrays.asList("0.0.0.0").toArray()
        );

        assert Compare.compareObjArr(
                solution("1111").toArray(),
                Arrays.asList("1.1.1.1").toArray()
        );

        assert Compare.compareObjArr(
                solution("010010").toArray(),
                Arrays.asList("0.10.0.10","0.100.1.0").toArray()
        );

        assert Compare.compareObjArr(
                solution("101023").toArray(),
                Arrays.asList("1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3").toArray()
        );
    }

    private static List<String> solution(String s) {
        List<String> res = new LinkedList<>();
        backtrack(res, s, new LinkedList<>(), 0, 4);
        return res;
    }

    private static void backtrack(List<String> res, String s, List<String> cList, int pos, int segment) {
        if (segment == 1) {
            String seg = s.substring(pos);
            if (isValidSegment(seg)) {
                cList.add(seg);
                res.add(toIp(cList));
                cList.remove(cList.size() - 1);
            }
            return;
        }
        int i = 1;
        while (pos + i < s.length() && i < 4) {
            String seg = s.substring(pos, pos + i);
            if (isValidSegment(seg)) {
                cList.add(seg);
                backtrack(res, s, cList, pos + i, segment - 1);
                cList.remove(cList.size() - 1);
            }
            i++;
        }
    }

    private static String toIp(List<String> ip) {
        StringBuilder sb = new StringBuilder();
        for (String seg: ip) {
            sb.append(seg);
            sb.append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static boolean isValidSegment(String segment) {
        if (segment.length() > 3)
            return false;
        if (segment.length() > 1 && segment.charAt(0) == '0')
            return false; //leading zero
        if (segment.length() == 3) {
            if (segment.charAt(0) > '2')
                return false;
            if (segment.charAt(0) == '2' && segment.charAt(1) > '5')
                return false;
            if (segment.charAt(0) == '2' && segment.charAt(1) == '5' && segment.charAt(2) > '5')
                return false;
        }
        return true;
    }

}
