package com.pfs.quizsystem;

public class Student {
    private String studName;
    private String topicChosen;
    private static int totalCorrect;
    private int totalWrong;
    private double score;
    private char grade;
    private String comment;

    /**
     * The constructor for the Student
     * @param name The name of the student
     */
    public Student(String name) {
        studName = name;
        totalCorrect = 0;
        totalWrong = 0;
        score = 0;
    }

    /**
     * This method is used to return the name of the student
     * @return The name of the student
     */
    public String getStudName() {
        return studName;
    }

    /**
     * This method is used to set the topic which is the question set chosen by the student
     * @param questionSet The question set represented by number
     */
    public void setTopicChosen(String questionSet) {
        topicChosen = questionSet;
    }

    /**
     * This method is used to get the total number of correct answers by the Student
     * @return The total number of correct answers by the Student
     */
    public static int getTotalCorrect() {
        return totalCorrect;
    }

    /**
     * This method is used to set the total number of questions that the Student answered correctly
     * @param correct The number of questions that the Student answered correctly
     */
    public static void setTotalCorrect(int correct) {
        totalCorrect = correct;
    }

    /**
     * This method is used to calculate the total number of questions that the Student answered wrongly and return it
     * @return The number of questions that the Student answered wrongly
     */
    public int getTotalWrong() {
        totalWrong = QuestionSet.getTotalQuestions() - totalCorrect;
        return totalWrong;
    }

    /**
     * This method is used to calculate the score obtained by the Student
     * @return The score obtained by the Student
     */
    public double getScore() {
        score = ((double)totalCorrect / (double)QuestionSet.getTotalQuestions()) * 100.0;
        return score;
    }

    /**
     * This method is used to return the grade obtained by the Student based on their score
     * @return The grade obtained by the Student
     */
    public char getGrade() {
        if (score >= 80) {
            grade = 'A';
        } else if (score >= 65 && score <= 79) {
            grade = 'B';
        } else if (score >= 50 && score <= 64) {
            grade = 'C';
        } else if (score >= 40 && score <= 49) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }

    /**
     * This method is used to return the comment based on Student's grade
     * @return The comment based on Student's grade
     */
    public String getComment() {
        switch (grade) {
            case 'A':
                comment = "Excellent!";
                break;
            case 'B':
                comment = "Keep it up :)";
                break;
            case 'C':
                comment = "Passed";
                break;
            case 'D':
                comment = "Need to improve";
                break;
            case 'F':
                comment = "Failed. You need to work hard!";
                break;
            default:
                // do nothing
                break;
        }
        return comment;
    }

    /**
     * This method is used to print the final result of the Student after answered all the questions
     */
    public void showStudResult() {
        System.out.println(getStudName() + ", you answered " + totalCorrect + " Questions Right, " +
                getTotalWrong() + " Questions Wrong for a Total of " +
                QuestionSet.getTotalQuestions() + " Questions.\n");
        String content = "--------------Report Card--------------" +
                "\nName: " + getStudName() +
                "\nSet: " + topicChosen +
                "\nDate: " + java.time.LocalDate.now() +
                "\nScore: " + String.format("%.2f", getScore()) + "%" +
                "\nGrade: " + getGrade() +
                "\nComment: " + getComment() +
                "\n─────────────────────────────" +
                "\n|    Grade    |    Score    |" +
                "\n─────────────────────────────" +
                "\n|      A      |   80 - 100  |" +
                "\n─────────────────────────────" +
                "\n|      B      |   65 - 79   |" +
                "\n─────────────────────────────" +
                "\n|      C      |   50 - 64   |" +
                "\n─────────────────────────────" +
                "\n|      D      |   40 - 49   |" +
                "\n─────────────────────────────" +
                "\n|      F      |    0 - 39   |" +
                "\n─────────────────────────────";
        System.out.println(content);
    }
}
