package com.trieupk.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for the DateTimeChecker web application.
 * Handles displaying the date input form and processing date validation requests.
 */
@Controller
public class DateTimeController {

    private final DateValidator dateValidator = new DateValidator();

    /**
     * Displays the date input form.
     */
    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    /**
     * Processes the date validation request.
     * Captures Day, Month, and Year inputs from the form,
     * validates them, and returns the result message.
     */
    @PostMapping("/check")
    public String checkDate(
            @RequestParam("day") String day,
            @RequestParam("month") String month,
            @RequestParam("year") String year,
            Model model) {

        String result = dateValidator.getValidationMessage(day, month, year);

        // Determine if the result is a success or error for styling
        boolean isValid = result.contains("is correct date time!");

        model.addAttribute("result", result);
        model.addAttribute("isValid", isValid);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "index";
    }
}
