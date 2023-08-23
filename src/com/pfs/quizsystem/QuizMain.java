package com.pfs.quizsystem;

import java.util.Scanner;

/**
 * <h1>Multiple Choice Questions (MCQ) system</h1>
 * <p>This system can handle <b>multiple</b> sets of MCQs. For example, Java Basics, Control Structure, HTML Basics, etc.
 * After selecting the set, the system should display the questions and options from the selected list.
 * Each question consist of <b>2 to 4 options</b> and an answer. There can be <b>multiple answers</b> to a question.
 * The users will answer and calculate the score based on their answer.
 * For example, the <b>score</b> should be 90% for 9 questions answered correctly out of 10 questions.
 * A <b>grade</b> followed by a comment is assign to the student based on the score.</p>
 *
 * @author Ch'ng Sin Yi
 */

public class QuizMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // prompt to input name
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        Student stud = new Student(name);
        System.out.println("\n" + stud.getStudName() + "! Welcome to the Quiz Test");

        JavaBasicsSet set1 = new JavaBasicsSet("Java Basics");
        JavaOopSet set2 = new JavaOopSet("Java OOP");
        HtmlBasicsSet set3 = new HtmlBasicsSet("HTML Basics");

        System.out.println("Choose your Multiple Choice Question Set. The Options are: ");
        // list all the question set available
        set1.displaySetName();
        set2.displaySetName();
        set3.displaySetName();

        // display questions based on chosen set
        System.out.print("Please choose one set based on the number listing: ");
        String questionSet;
        // prompt again until get a valid input from Student
        boolean prompt = false;
        do {
            questionSet = input.nextLine();
            switch (questionSet) {
                case "1":
                    stud.setTopicChosen(set1.getQuizSetName());
                    set1.displayQuestions();
                    stud.showStudResult();
                    break;
                case "2":
                    stud.setTopicChosen(set2.getQuizSetName());
                    set2.displayQuestions();
                    stud.showStudResult();
                    break;
                case "3":
                    stud.setTopicChosen(set3.getQuizSetName());
                    set3.displayQuestions();
                    stud.showStudResult();
                    break;
                default:
                    System.out.print("Sorry, the question set selected was not found.\nPlease choose again: ");
                    prompt = true;
            }

        } while (prompt);
    }
}
