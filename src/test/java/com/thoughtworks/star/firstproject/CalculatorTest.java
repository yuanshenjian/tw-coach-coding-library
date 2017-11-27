package com.thoughtworks.star.firstproject;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class CalculatorTest {

    @Test
    void three_step_of_a_test() {
        // 1. 准备数据，定义输入
        double number1 = 5.0;
        double number2 = 9.0;

        // 2. 执行调用，获取结果
        Calculator calculator = new Calculator();
        double result = calculator.sum(number1, number2);

        // 3. 断言
        assertThat(result, is(14.0));
    }

    /**
     * 1. 统计列表中偶数的和
     * Input: Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
     * Output: 30
     * 考核扩展：Output: 21(3 + 6 + 9 )
     */
    @Test
    void should_sum_evens() {
        Calculator calculator = new Calculator();
        int result = calculator.sumEvens(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertThat(result, is(30));
    }


    /**
     * 2. 按照名字拼音顺序统计名字的长度，输入为名字列表，输出为名字-长度映射
     * Input: Arrays.asList("ynpan", "yzqi", "ybowang", "qiqzhao", "yibtan", "abc", "sjyuan")
     * Output: Map<String, Integer>: {"abc": 3, qiqzhao": 7, "sjyuan": 6, "ybowang": 7, "yibtan": 6, "ynpan": 5, "yzqi": 4 }
     * 考核扩展：Output: Map<String, Integer>: {"abc": 3, "yzqi": 4, ynpan: 5, "sjyuan": 6,  yibtan: 6, qiqzhao": 7, "ybowang": 7 }
     */
    @Test
    void should_sort_and_count_names() {
        Calculator calculator = new Calculator();

        Map<String, Integer> result = calculator.sortAndCount(Arrays.asList("ynpan", "yzqi", "ybowang", "qiqzhao",
                "yibtan", "abc", "sjyuan"));
        assertThat(result.keySet(), contains("abc", "qiqzhao", "sjyuan", "ybowang", "yibtan", "ynpan", "yzqi"));
        assertThat(result.values(), contains(3, 7, 6, 7, 6, 5, 4));
    }

    /**
     * 3. 按照名字拼音顺序统计名字的长度，输入为名字列表，输出为名字-长度映射
     * Input: "aababbbcabcdabcde"
     * Output: "5(a) < 6(b) < 3(c) < 2(d) < 1(e)"
     * 考核扩展：Output: "6(b) > 5(a) > 3(c) > 2(d) > 1(e)"
     */
    @Test
    void should_sort_and_count_words() {
        String input = "aababbbcabcdabcde";
        Formatter formatter = new FormatterImpl();
        Sorter sorter = new Sorter(formatter);
        String result = sorter.present(input);
        assertThat(result, is("5(a) < 6(b) < 3(c) < 2(d) < 1(e)"));
    }

    @Test
    void should_order_random() {
        List<Integer> randomNumbers = new Calculator().generateRandomNumbers(5);
        assertThat(randomNumbers, containsInAnyOrder(1, 2, 3, 4, 5));
        Logger.getLogger(this.getClass().getName()).info(randomNumbers.toString());
    }
}
