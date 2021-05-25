package com.sergeik.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums that consists of non-negative integers. Let us define rev(x)
 * as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21.
 * A pair of indices (i, j) is nice if it satisfies all of the following conditions:
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.
 */
public class CountNicePairsInAnArray {

    public static void main(String[] args) {
        assert 678 == solution(new int[] {8047408,192867140,497837845,279787822,151999002,168514912,193424242,399636844,132424231,476736524,267958611,493350382,476382727,232939232,197000791,295291645,126313621,374645524,7956597,1376731,496463745,234481430,359130803,287625836,250572050,42311324,477434624,493231448,493231244,150494051,184645534,365252413,495764582,335976531,384564332,377151623,198736741,335161533,245552540,194897341,83911938,220562020,496645745,287151782,374635526,372483324,485101584,271797172,244949442,254333303,251635002,459181805,472392123,241350140,256121502,336895621,354635302,358909704,194525491,3606063,194150341,63477436,341936141,60299206,69811896,369928813,229926920,435310522,299542980,463777364,164534512,305885501,437181734,74288247,487281835,171161022,423966312,496989544,452633252,252433101,141565141,315895501,478897927,232532230,472451262,160504114,476666674,6179716,251483002,474777474,443532332,475808424,457514604,400936002,384878483,172616122,283292232,165645615,392000144,378636873});
        assert 6 == solution(new int[] {432835222,112141211,5408045,456281503,283322436,414281561,37773,286505682});
        assert 4 == solution(new int[] {13,10,35,24,76});
        assert 2 == solution(new int[] {42,11,1,97});
    }

    private static int solution(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int counter = 0;
        for (int n: nums) {
            int rev = 0;
            int num = n;
            int base = 10;
            while (num > 0) {
                rev *= base;
                int d = num % 10;
                rev = rev + d;
                num /= 10;
            }
            int revDiff = n - rev;
            int v = freq.getOrDefault(revDiff, 0);
            freq.put(revDiff, v + 1);

            counter = (counter + v) % 1000000007;

        }
        return counter;
    }


}
