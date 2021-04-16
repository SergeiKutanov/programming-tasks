package com.sergeik.strings;

public class StrStr {

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        assert 2 == strStr(haystack, needle);

        haystack = "mississippi";
        needle = "issipi";
        assert -1 == strStr(haystack, needle);

        haystack = "mississippi";
        needle = "issip";
        assert 4 == strStr(haystack, needle);

        haystack = "a";
        needle = "";
        assert  0 == strStr(haystack, needle);

        haystack = "hello";
        needle = "lo";
        assert 3 == strStr(haystack, needle);

        haystack = "aaaaaaa";
        needle = "baa";
        assert -1 == strStr(haystack, needle);

        haystack = "";
        needle = "";
        assert 0 == strStr(haystack, needle);

    }

    /**
     * Time - O(n*k)
     * Memory - O(n+k)
     * @param haystack
     * @param needle
     * @return
     */
    private static int strStr(String haystack, String needle) {
        // empty needle appears everywhere, first appears at 0 index
        if (needle.length() == 0)
            return 0;

        for (int i = 0; i < haystack.length(); i++) {
            // no enough places for needle after i
            if (i + needle.length() > haystack.length()) break;

            //iterate over needle
            for (int j = 0; j < needle.length(); j++) {
                //here i is haystack offset, less pain maintaining two points
                if (haystack.charAt(i+j) != needle.charAt(j))
                    break;
                //if j got all the way to needle's length then found
                if (j == needle.length()-1)
                    return i;
            }
        }

        return -1;
    }

}
