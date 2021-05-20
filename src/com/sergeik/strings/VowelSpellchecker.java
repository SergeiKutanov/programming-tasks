package com.sergeik.strings;

import java.util.*;

/**
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned
 * with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
 * it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 */
public class VowelSpellchecker {

    public static void main(String[] args) {



        assert Arrays.equals(
                new String[] {"","","inf","ngn","ouv","","","","","","","","","","dtk","","zvi","","","","","mcf","vow","","","jcg","kqd","","","","iaf","fvm","","vxu","ztv","nxa","","kfk","vxu","","vla","uab","","gjp","","","","syu","","xss","","","","","","bys","lnt","","","syu","","efn","","uoo","","vsi","bud","hdg","","","","","rhv","","yff","","wka","","","gte","","gjp","","","","","","kjv","hdg","xoe","whj","","","mbd","bud","vxu","dgp","","",""},
                solution(
                        new String[] {"dtk","oag","pad","nfs","xej","bys","dgp","hev","hsk","gws","kqd","ztv","fvi","irw","rhv","dys","ofl","lnt","vmq","vsp","kbv","fof","ako","gbu","mbd","szy","zlr","cpt","xck","hdg","uoo","fvm","vla","fpe","mpk","abv","mcf","ibp","num","ouv","icx","uab","wka","ozz","gte","vpv","rvd","hed","fcl","iaf","sba","wxa","gjp","qzh","kjv","fxr","msf","bwj","wqp","whj","vxu","xoe","wwh","ray","jor","vsi","yft","ngn","inf","ggw","kwj","irk","vqs","zvi","lwx","ooc","fdi","ana","jcg","rga","vow","gia","nxa","pgr","ymw","kfk","rur","bud","cfe","ffn","wnr","uzh","yff","ucx","xss","mbi","tph","efn","syu","sqz"},
                        new String[] {"nrm","szv","inf","ngn","Ouv","mqk","bra","pie","xyz","mif","hjz","hlr","ltt","zce","dtK","lyw","zvi","yha","bMi","eyy","xoc","MCF","vOW","tvv","wpv","jcg","kqd","hvi","wmz","nmf","aiF","fvm","puk","vxi","ztv","NxA","rwo","kFK","vxu","esi","vla","uub","fom","gJp","ahb","bJW","ipv","syU","nyg","xss","iom","qnp","soy","smv","zzo","Bys","lnt","wuc","uqk","syu","aok","efn","dju","ooe","ipu","VSi","bod","hdg","wux","vex","qee","ueq","rhv","czm","yff","npo","wka","vmm","jtk","gto","rjx","gjp","nza","idj","xuf","yzp","nhc","kjv","hdG","xOE","whj","eox","lcv","Mbd","bud","vxe","dgp","smo","qdv","bav"}
                )
        );

        assert Arrays.equals(
                new String[] {"kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"},
                solution(
                        new String[] {"KiTe","kite","hare","Hare"},
                        new String[] {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"}
                )
        );
    }

    private static String[] solution(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>();
        HashMap<String, String> caseMatch = new HashMap<>();
        HashMap<String, String> vowelMatch = new HashMap<>();
        for (String w: wordlist) {
            words.add(w);
            String lc = w.toLowerCase();
            caseMatch.putIfAbsent(lc, w);
            vowelMatch.putIfAbsent(replaceVowels(lc), w);
        }

        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (words.contains(queries[i])) {
                res[i] = queries[i];
                continue;
            }
            String lowerCase = queries[i].toLowerCase();
            String match = caseMatch.get(lowerCase);
            if (match != null) {
                res[i] = match;
                continue;
            }

            String noVowels = replaceVowels(lowerCase);
            match = vowelMatch.get(noVowels);
            if (match != null) {
                res[i] = match;
                continue;
            }
            res[i] = "";
        }
        return res;
    }

    private static String replaceVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                ch = 0;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

}
