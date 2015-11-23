package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

/**
 * Created by patrick on 23.11.15.
 */
public class BasicOperationTest {

    private BinaryNumber numberOne = BinaryNumber.of("111000111");

    private BinaryNumber numberTwo = BinaryNumber.of("1001001");

    @Test
    public void test_add_returns_correct_binary_number(){
        BinaryNumber res = BasicOperation.fromString("+").apply(numberOne,numberTwo);
        assertThat(res.removeLeadingZeros(), is(equalTo(BinaryNumber.of("1000010000").removeLeadingZeros())));
    }

    @Test
    public void test_subtract_returns_correct_binary_number(){
        BinaryNumber res = BasicOperation.fromString("-").apply(numberOne,numberTwo);
        assertThat(res.removeLeadingZeros(), is(equalTo(BinaryNumber.of("101111110").removeLeadingZeros())));
    }

    @Test
    public void test_multiply_returns_correct_binary_number(){
        BinaryNumber res = BasicOperation.fromString("*").apply(numberOne,numberTwo);
        assertThat(res.removeLeadingZeros(), is(equalTo(BinaryNumber.of("1000000110111111").removeLeadingZeros())));
    }

    @Test
    public void test_divide_returns_correct_binary_number(){
        BinaryNumber res = BasicOperation.fromString("/").apply(numberOne,numberTwo);
        assertThat(res.removeLeadingZeros(), is(equalTo(BinaryNumber.of("110").removeLeadingZeros())));
    }

    @Test
    public void test_is_valid_operation(){
        boolean t = BasicOperation.isValidOperation("+");
        assertThat(t, is(equalTo(true)));

        boolean f = BasicOperation.isValidOperation("h");
        assertThat(f, is(equalTo(false)));
    }


}