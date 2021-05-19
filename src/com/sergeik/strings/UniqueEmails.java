package com.sergeik.strings;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmails {

    public static void main(String[] args) {
        assert 2 == numUniqueEmails(new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"});
    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email: emails) {
            String vemail = getEmail(email);
            if (vemail != null && !set.contains(vemail))
                set.add(vemail);
        }
        return set.size();
    }

    private static String getEmail(String str) {
        String[] parts = str.split("@");
        if (parts.length != 2)
            return null;

        String local = parts[0];
        int plusIndex = local.indexOf('+');
        if (plusIndex > -1) {
            local = local.substring(0, plusIndex);
        }
        local = local.replaceAll("\\.", "");
        return local + "@" + parts[1];
    }
}
