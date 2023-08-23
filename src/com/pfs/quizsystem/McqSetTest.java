package com.pfs.quizsystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class McqSetTest {
    JavaBasicsSet testSet;
    ArrayList<String> inputAns = new ArrayList<>();
    ArrayList<String> correctAns = new ArrayList<>();
    @BeforeEach
    void setUp() {
        testSet = new JavaBasicsSet("Java Basics");
        // Test for single answer (Expected output: True)
        /*inputAns.add("A");
        correctAns.add("A");*/

        // Test for single answer (Expected output: False)
        /*inputAns.add("A");
        correctAns.add("B");*/

        // Test for multiple answer in sorted way (Expected output: True)
        /*inputAns.add("A");
        inputAns.add("B");
        correctAns.add("A");
        correctAns.add("B");*/

        // Test for multiple answer in sorted way (Expected output: False)
        /*inputAns.add("A");
        inputAns.add("B");
        correctAns.add("C");
        correctAns.add("D");*/

        // Test for multiple answer in unsorted way (Expected output: True)
        inputAns.add("C");
        inputAns.add("A");
        correctAns.add("A");
        correctAns.add("C");
    }

    @AfterEach
    void tearDown() {
        testSet = null;
    }

    @Test
    // Expected output: True
    void checkAnswer() {
        boolean isCorrect;
        isCorrect = testSet.checkAnswer(inputAns, correctAns);
        assertTrue(isCorrect);
    }
    // Expected output: False
    /*void checkAnswer() {
        boolean isCorrect;
        isCorrect = testSet.checkAnswer(inputAns, correctAns);
        assertFalse(isCorrect);
    }*/
}