/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

public class DataValidation {
    public static boolean isEmpty(String inputString) {
        if (inputString.isEmpty() || inputString.isBlank() ||
                        inputString == null) {
            return true;
        } else {
            return false;
        }
    }
}
