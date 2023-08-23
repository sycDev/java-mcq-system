package com.pfs.quizsystem;

public abstract class QuestionSet {
    private String quizSetName;
    private String path;
    private static int counter = 0;
    private int quizID;
    private static int totalQuestions;

    /**
     * The constructor for the QuestionSet
     * @param fileName The CSV file name
     */
    public QuestionSet(String fileName) {
        quizSetName = fileName;
        quizID = ++counter; // ID auto increment by 1 as new object is created
    }

    /**
     * This method is used to get the question set name of the object
     * @return The question set name
     */
    public String getQuizSetName() {
        return quizSetName;
    }

    /**
     * This method is used to get the path of the question set CSV file
     * @return The path of the question set CSV file
     */
    public String getPath() {
        path = "csv/" + getQuizSetName() + ".csv";
        return path;
    }

    /**
     * This method is used to get the total number of questions in the question set
     * @return The total number of questions
     */
    public static int getTotalQuestions() {
        return  totalQuestions;
    }

    /**
     * This method is used to set the total number of questions from the question set
     * @param totalQs The total number of the questions
     */
    public static void setTotalQuestions(int totalQs) {
        totalQuestions = totalQs;
    }

    /**
     * This method is used to print out the id followed by the question set name
     */
    public void displaySetName() {
        System.out.println(quizID + ". " + quizSetName);
    }

    /**
     * This abstract method is used to read the CSV file and start the quiz after the student had chosen a question set
     * This will be different for handling different type of questions such as MCQ and textual answer
     */
    public abstract void displayQuestions();
}
