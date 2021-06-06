package com.sergeik.greedy;

import java.util.Stack;

/**
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 *
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 */
public class MaximumScoreFromRemovingSubstrings {

    public static void main(String[] args) {
        assert 60 == solution("aaaaaabbbbbb", 10, 20);
        assert 3247369 == solution("iaabbbabtbfbyabubbmbjabbabeadaabbdbbdtjbonbabazdbbbaaabdlaababacbbfaaaafaebiabbaabbgbaaabbbbavbaabgbauaaaabbabqabbbfbabadaaaobbbabanabvhabbbziohuqbaatbbaqaabbaabafbbbdabsaatfkabbaabbqababbbbbbaeaabnfkbyboaobabbolabaabbqoabbabaalnkaabbaaabbwawtasdaabaaaababdbbbbobbcubbbaabaxbhbbbhrbbaabbgzbayfbbbbbobaaaalaaaalzxabazobbaabbbabbabaabaaawalaaybaabbafbroazaaerpaaaabnbbfaaraaababbibbgaabaabbbaalbbatbabaabbqpcfbbbabbaakbaabaabbabbabbgaaabgadaaababdhbhbbaaaapzrabayxaahbbaaxaabaafqbbasbbbaaablhaabtubabgzsabwabawbapaabaacbababyaabbbmjbbabaababbcjbbbbbbabbabmyblaataaxbatelaboqaabpbmeabzbaaaaubmbqbbibaarbbkaaadbbvbbbacabaaaakbbbbabbbbbathjqtefbwabajbabaiaqaabaabbaabaakaaabbibabbaabbaabbbloaabaiabbanaabajtxaabbbayzbajpubbaaaabbdqatbaabaaabababaaebbababnrbbvbltpcbaabaakbaaabarbrmaablaaabaabaabagjaabbabybababbaaabrchbtobbhdaobrxmaaucrbdablabacaaaxabgbbbacbbjabbaabzabkbtcarabfbaaeaqjrabgbcydbbabaavrdaaabbaabababwababobabbzabaabbbaqqaasbaababvwaabbbbbabaaabbbbabaaababbrbbbaaababbbtaajfawhobabeaypabbbjaaabaaabbabbaabsfaxvbrboaabbabkbibayabbabxambazbaaaaaaqababbbcnbaaaabhabdbaaakbbavaaacbbaradcbsbuawraboabbaabaoqbbbabbaaalaaabhibaybbwbbbbqfbaaaoaabaaaaoabbapaaaqcybnbbbbibybbauaaaabbaahbbwbdbaatabbaaaabxababahbaiaaqbbqbydrbbcbaablbbamatbbxaabftoaabbbabbraiabkbbbbblaabaoaaelabapabmayapbwbapdakaabkbazvabavboabhbkaaabsfbabwarxbbejhaabbbbmaaagbaarabmmcafcbaaabbaazbbaaabaabbbbbaamaaabubuaabaaabajbaaababazabaiayakmcqababjbaaabbbaaabafbsbbabaaaabobaaabnbrabaadaaabjblabrbvbbabfbbebaatabjbabojabaabaabababbbbyufbbabbabaaaaalhsykabbnbtbbfatapbagabahgearbccbwxsaabbbhbbbbbhnbhcvauaabvababfbbaaabaabzbvaaqbaajnbbbdabzbjbabavabvatpbuavlabbbbbbbbtbaobalbgdbabbanaabbbbabbbaabahaaabatbbbbofbbujababbaambaabaneapbatdaabbyaaaucbbbdaagaababaaabwabbbhbavaaaarbabuabvbaixjkobbibaaubabbbbvbbababaalmfaaiaobeabbaaaabazbaamabbayabbbabyamaavbbbaabmbyibbbbtuaaxbaabbababrrabaabbababcaabbbabbajaabbaacbbamaabiacobbamavbrckaajabbwbatabablbxaabbvbbagbabbbabzavrhbbaaabcaabuaabbbabebqbbcdaabzaapcaasbbaababaubabdqbwcbbbmbabfbjybbaababnaabubabaaabbcnbayhbazbksbhaabbaaaavbabeavababbatoaabaiasbcbabzbbhqbuabaaaggbbbauabaabbctbbbabbkcbpabklbbgbbbabaavapbzaaababpaaaabebjbqssbaaaxaabbabaqabbabdbyavtaaaaajbgebbbaabvatabanaaaaqanabaabaybreababbbabababababbaabbabbvvoafryrgaobaabaalbbybbbbaarjbabuaakbajabaaabybaaababaxalbbjabaaacqbbxaabuabaaabbvbazuabbbbabbbatwsabbaggbaybabajbabjiwabbqbabtaybbabbaaabmaqabbaaaababyaboraabbbbabbpwblxbaabbaybbaadzbababbbapabbaaatbbaaafobaahabaabbopakatebaaqcaaabaabbbbakbbdbbuabsauaadaababjaobzabkaasbasbbbbaahbaubatvbbbgaxhaabbabzbabbhyabbbpybgbisaaedabldbdadaabbbabflbarabwaavbbhlcmaabgbataabsabbdrrabbbbaatdjbabcebabahabfibcuahaanbaaabbnanbaahbbaabaaobbbbabbbjbbabbababbgbmbabmiababaabbbaaabaababbaazaaaaaaablbarbabbbbaabrbbbabaaababeabfbaataaobabbabbabbbybjabaebmabqmanuaaabaababbbazdwbpaaybbaaabaabababakbaaaankababbpaalaaabbbaapratababaibfbobjabbqbbabmpabauaabbbgbbaraorbaanxbbbaaxkbbohcbaasaahbanpaabqawbaagabmabeablaaabbgabaabbayaababkazzuabbaatyaabibabkmbabblbababiaxaababaajabaabxabasbaqbmfamavbatbbabnabaybibbasapabnbhabaaabatvabwwabranvbbjarosabbbibbbiarbrnbauabbbbsbbbbyjwbacoaabbbbbsbbebajempabmazlbpbaabaajbadljaabibhabsqmabdhbbahabbbbbaeabbabbasunwbmatafnbbaaaaabmbpbaknbbaabbhibambxysalabxbaabbbaaaabbadahaaabkbabbahbhbbblbbabbbylbbaesbaabbbaaaoaababbsaybbbabnraaboabbapaabaaapbebawahbvaaebbebcbxaaabubaaaloabcjbtktbtbbaauaabgbb",
                1857,
                5279);
        assert 19 == solution("cdbcbbaaabab", 4, 5);
        assert 20 == solution("aabbaaxybbaabb", 5, 4);
    }

    private static int solution(String s, int x, int y) {
        Stack<Character> stack1 = new Stack<>(), stack2 = new Stack<>();
        char first = x > y ? 'a' : 'b';
        char second = x > y ? 'b' : 'a';
        int max = Math.max(x, y), min = Math.min(x, y), res  = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!stack1.isEmpty() && stack1.peek() == first && s.charAt(i) == second) {
                stack1.pop();
                res += max;
            } else {
                stack1.push(s.charAt(i));
            }
        }

        while (!stack1.isEmpty()) {
            if (!stack2.isEmpty() && stack2.peek() == first && stack1.peek() == second) {
                stack2.pop();
                stack1.pop();
                res += min;
            } else {
                stack2.push(stack1.pop());
            }
        }

        return res;
    }
}
