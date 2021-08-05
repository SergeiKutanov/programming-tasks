package com.sergeik.strings;

/**
 * You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.
 *
 * You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes. Then,
 * group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits
 * are then grouped as follows:
 *
 * 2 digits: A single block of length 2.
 * 3 digits: A single block of length 3.
 * 4 digits: Two blocks of length 2 each.
 * The blocks are then joined by dashes. Notice that the reformatting process should never produce any blocks of
 * length 1 and produce at most two blocks of length 2.
 *
 * Return the phone number after formatting.
 */
public class ReformatPhoneNumber {

    public static void main(String[] args) {
        assert "123-45-67".equals(solution("--123-4-567"));
    }

    private static String solution(String number) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= '0' && ch <= '9')
                res.append(ch);
        }
        StringBuilder ans = new StringBuilder();
        int i = 0;
        for (i = 0; i < res.length() - 4; i += 3) {
            ans.append(res.substring(i, i + 3));
            ans.append('-');
        }
        if (res.length() - i < 4) {
            ans.append(res.substring(i));
        } else {
            ans.append(res.substring(i, i + 2))
                    .append('-')
                    .append(res.substring(i + 2));
        }
        return ans.toString();
    }

}
