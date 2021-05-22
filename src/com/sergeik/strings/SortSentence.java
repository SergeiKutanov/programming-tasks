package com.sergeik.strings;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * Each word consists of lowercase and uppercase English letters.
 *
 * A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging
 * the words in the sentence.
 *
 * For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1"
 * or "is2 sentence4 This1 a3".
 * Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.
 */
public class SortSentence {

    public static void main(String[] args) {
        assert "This is a sentence".equals(sortSentence("is2 sentence4 This1 a3"));
    }

    public static String sortSentence(String s) {
        String[] parts = s.split(" ");
        String[] sentence = new String[parts.length];
        for (String str: parts) {
            sentence[str.charAt(str.length() - 1) - '1'] = str.substring(0, str.length() - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.length - 1; i++) {
            sb.append(sentence[i]);
            sb.append(" ");
        }
        sb.append(sentence[sentence.length - 1]);
        return sb.toString();
    }

}
