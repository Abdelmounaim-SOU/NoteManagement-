package com.example.notemanagment.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    public static class ValidationResult {
        private final boolean isValid;
        private final String message;

        public ValidationResult(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * Validate marks based on given rules.
     *
     * @param marks List of marks to validate.
     * @return ValidationResult containing validation status and message.
     */
    public ValidationResult isValider(List<Integer> marks) {
        if (marks == null || marks.isEmpty()) {
            return new ValidationResult(false, "Khwi rassek rah nsiti chi hed.");
        }

        for (Integer mark : marks) {
            if (mark == null) {
                return new ValidationResult(false, "Khwi rassek rah nsiti chi hed.");
            }
            if (mark < 0 || mark > 20) {
                return new ValidationResult(false, "Khwi rassek rah zti/nassti fih.");
            }
        }

        return new ValidationResult(true, "Validé");
    }

    /**
     * Example route to demonstrate how to validate marks.
     */
    @GetMapping("/validateMarks")
    public String validateMarks(Model model) {
        List<Integer> exampleMarks = List.of(15, 18, null); // Example marks
        ValidationResult result = isValider(exampleMarks);

        model.addAttribute("isValid", result.isValid());
        model.addAttribute("validationMessage", result.getMessage());
        return "validateMarksView"; // Replace with your actual view name
    }
}