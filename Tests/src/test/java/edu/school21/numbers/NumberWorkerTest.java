package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    private NumberWorker numWorker = new NumberWorker();

    @ParameterizedTest(name = "number {index} is prime")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19})
    public void isPrimeNumberTest(int num) {
        Assertions.assertTrue(numWorker.isPrime(num));
    }

    @ParameterizedTest(name = "number {index} don't use to be prime")
    @ValueSource(ints = {4, 6, 8, 10, 12, 14, 16, Integer.MAX_VALUE - 1})
    public void notPrimeNumberTest(int num) {
        Assertions.assertFalse(numWorker.isPrime(num));
    }

    @ParameterizedTest(name = "number {index} need to make exception")
    @ValueSource(ints = {1, 0, -1, -200, -243, -32, -5, Integer.MAX_VALUE + 1})
    public void primerNumberExceptionsTest(int num) {
        Exception e = Assertions.assertThrows(RuntimeException.class, () -> {
            numWorker.isPrime(num);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void sumOfDigitsTest(int num, int expected) {
        Assertions.assertEquals(expected, numWorker.digitsSum(num));
    }
}
