package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException("this num can't be less than 2");
        }
        for (int i = 2; i <= (number / 2); i++) {
            if (number % i == 0) {
               return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int res = 0;
        while (number != 0) {
            res += number % 10;
            number /= 10;
        }
        return res;
    }
}
