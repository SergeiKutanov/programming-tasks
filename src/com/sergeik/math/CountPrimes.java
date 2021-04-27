package com.sergeik.math;

import com.sergeik.utils.Timer;

import java.util.concurrent.Callable;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {

    public static void main(String[] args) {
        int res = Timer.runWithTimer("499979", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return countPrimes(499979);
            }
        });
        assert 41537 == res;
        assert 0 == solution(2);
        assert 168 == solution(1000);
        assert 4 == solution(10);
        assert 0 == solution(0);
        assert 0 == solution(1);
    }

    private static int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] numbers = new boolean[n];
        for (int p = 2; p <= (int)Math.sqrt(n); p++) {
            if (numbers[p] == false) {
                for (int j = p*p; j < n; j += p) {
                    numbers[j] = true;
                }
            }
        }

        int numberOfPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (numbers[i] == false) {
                ++numberOfPrimes;
            }
        }

        return numberOfPrimes;
    }


    private static int solution(int n) {
        boolean[] primes = new boolean[n];

        for (int i = 2; i < n; i++) {
            primes[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime(i))
                continue;
            for (int j = i * i; j < n ; j += i) {
                primes[j] = false;
            }
        }

        int res = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i])
                res++;
        }
        return res;
    }

    private static boolean isPrime(int n) {
       if (n <= 1)
           return false;
       for (int i = 2; i * i <= n; i++) {
           if (n % i == 0)
               return false;
       }
       return true;
    }

}
