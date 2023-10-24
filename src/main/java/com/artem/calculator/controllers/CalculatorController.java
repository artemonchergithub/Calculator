package com.artem.calculator.controllers;

import com.artem.calculator.CalculatorApplication;
import com.artem.calculator.models.CalculationComponents;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CalculatorController {
    String regex = "^[+-]?(\\d{1,3}(\\s?\\d{3})*([.,]\\d*)?|[.,]\\d+)$";
    @GetMapping("/")
    public String starting(Model model){
        return "calculator";
    }

    @GetMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("components", new CalculationComponents());
        return "calculator";
    }
    @PostMapping(value = "/calculator", params = "submit")
    public String postCalculator(@RequestParam String number_1, @RequestParam String number_2, @RequestParam String operations, Model model){
        model.addAttribute("firstNumber", number_1);
        model.addAttribute("secondNumber", number_2);
        if (!number_1.matches(regex) || !number_2.matches(regex)){
            model.addAttribute("result", "Неверные входные данные");
            return "calculator";
        }
        if ((operations.equals("division") && new BigDecimal(number_2.replace(',', '.').replaceAll(" ","")).compareTo(BigDecimal.ZERO) == 0)) {
            model.addAttribute("result", "Нельзя делить на 0");
            return "calculator";
        }

        CalculationComponents components = new CalculationComponents(
                number_1.replace(',','.').replaceAll(" ", ""),
                number_2.replace(',','.').replaceAll(" ", ""),
                operations);
        model.addAttribute("result", components.getFormattedResult());
        return "calculator";
    }
    @PostMapping(value = "/calculator", params = "shutdown")
    public void shutdownServer(){
        CalculatorApplication.exitApplication();
    }

}
