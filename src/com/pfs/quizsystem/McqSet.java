package com.pfs.quizsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class McqSet extends QuestionSet {
    private int totalAnswered = 0;

    /**
     * The constructor for the McqSet
     * @param fileName The CSV file name
     */
    public McqSet(String fileName) {
        super(fileName);
    }

    /**
     * This abstract method is used to read the CSV file and start the quiz after the student had chosen a question set
     * This will be different for handling different type of questions such as MCQ and textual answer
     */
    @Override
    public void displayQuestions() {
        System.out.println("Set: " + getQuizSetName() + "\nGood Luck :>");
        try {
            FileReader file = new FileReader(getPath());
            BufferedReader br = new BufferedReader(file);
            String row;
            // skipping the first row in CSV file
            br.readLine();
            while ((row = br.readLine()) != null) {
                // set the limit to negative so that it will not discard trailing empty strings
                String[] columns = row.split(",", -1);
                int questionNo = Integer.parseInt(columns[0]);
                System.out.println("\nQuestion " + questionNo);
                // print the question statement
                System.out.println(columns[1]);
                // print the options list
                int totalOptions = 0;
                if (!columns[2].equals("")) {
                    System.out.println("A) " + columns[2]);
                    totalOptions++;
                }
                if (!columns[3].equals("")) {
                    System.out.println("B) " + columns[3]);
                    totalOptions++;
                }
                if (!columns[4].equals("")) {
                    System.out.println("C) " + columns[4]);
                    totalOptions++;
                }
                if (!columns[5].equals("")) {
                    System.out.println("D) " + columns[5]);
                    totalOptions++;
                }
                System.out.println();

                // store all the correct answers in array
                ArrayList<String> correctAnsList = new ArrayList<>();
                // make sure each of them in uppercase for validate answer purpose
                correctAnsList.add(columns[6].toUpperCase());
                correctAnsList.add(columns[7].toUpperCase());
                correctAnsList.add(columns[8].toUpperCase());
                correctAnsList.add(columns[9].toUpperCase());
                correctAnsList.removeAll(Arrays.asList(null, ""));
                int totalCorrectAns = correctAnsList.size();

                // get student's answer input
                String[] inputAns = inputAnswer(totalOptions, totalCorrectAns);

                // convert the array of input answer into ArrayList
                ArrayList<String> inputAnsList = new ArrayList<>(Arrays.asList(inputAns));
                // validation of answer
                boolean isCorrect = checkAnswer(inputAnsList, correctAnsList);
                // get all the exact correct answer
                String[] correctAns = columns[10].split(";");

                // give feedback after Student answered the question
                if (isCorrect) {
                    if (totalCorrectAns > 1) {
                        System.out.println("All answers are correct.");
                    } else if (totalCorrectAns == 1) {
                        System.out.println("Correct Answer.");
                    }
                } else {
                    System.out.print("Wrong Answer. ");
                    if (totalCorrectAns > 1) {
                        System.out.println("The correct answers are ");
                    } else if (totalCorrectAns == 1) {
                        System.out.print("The correct answer is ");
                    }
                    // list out the exact correct answers
                    for (int i = 0; i < correctAns.length; i++) {
                        System.out.println(correctAns[i]);
                    }
                }

                // get the explanation if it is not blank
                if (!columns[11].equals("")) {
                    System.out.println("Explanation: " + columns[11]);
                }
                // add 1 to the total number of question that Student answered
                totalAnswered++;
            }

            // close the Buffered Reader
            br.close();

            // set the total questions of this set
            setTotalQuestions(totalAnswered);
            System.out.println("\n────────────────────────────────End of Questions────────────────────────────────\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the Student's answer from input
     * @param totalOptions The total options of the question
     * @param totalCorrectAns The total correct answers of the question
     * @return The array of the Student's answers
     */
    public String[] inputAnswer(int totalOptions, int totalCorrectAns) {
        String[] inputAns = new String[totalCorrectAns];
        Scanner input = new Scanner(System.in);

        // prompt to input answer
        if (totalCorrectAns > 1) {
            System.out.println("Choose multiple answers");
        } else if (totalCorrectAns == 1) {
            System.out.print("Your answer is: ");
        }

        // user input answer and convert into uppercase for validate answer purpose
        for (int i = 0; i < inputAns.length; i++) {
            if (totalCorrectAns > 1) {
                System.out.print("Your answer " + (i + 1) + " is: ");
            }
            inputAns[i] = input.nextLine().toUpperCase();
            // different feedback to prompt for choose again based on different number of options
            switch (totalOptions) {
                case 2:
                    while(!inputAns[i].matches("[abAB]")) {
                        System.out.print("Please choose A or B: ");
                        inputAns[i] = input.nextLine().toUpperCase();
                    }
                    break;
                case 3:
                    while(!inputAns[i].matches("[abcABC]")) {
                        System.out.print("Please enter an option from A to C: ");
                        inputAns[i] = input.nextLine().toUpperCase();
                    }
                    break;
                default:
                    while(!inputAns[i].matches("[abcdABCD]")) {
                        System.out.print("Please enter an option from A to D: ");
                        inputAns[i] = input.nextLine().toUpperCase();
                    }
            }
        }
        return inputAns;
    }

    /**
     * This method is used to compare the Student's answers and the correct answers and add 1 to the total correct
     * answer of the Student if answered correctly
     * @param inputAnsList The ArrayList of the Student's answers for the question
     * @param correctAnsList The ArrayList of the correct answers of the question
     * @return true if Student's answers are correct, false otherwise
     */
    public boolean checkAnswer(ArrayList<String> inputAnsList, ArrayList<String> correctAnsList) {
        // sort the student's input answers and correct answers in ascending order
        Collections.sort(inputAnsList);
        Collections.sort(correctAnsList);
        // if the answer is correct, return true, false otherwise
        if (inputAnsList.equals(correctAnsList)) {
            // add 1 to the current total correct answers of student
            Student.setTotalCorrect(Student.getTotalCorrect() + 1);
            return true;
        } else {
            return false;
        }
    }
}
