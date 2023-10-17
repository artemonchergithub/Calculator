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
        String regex = "^(\\+|-)?(\\d+((\\.|,)\\d*)?|(\\.|,)\\d+)$";
        model.addAttribute("firstNumber", number_1);
        model.addAttribute("secondNumber", number_2);
        if (!number_1.matches(regex) || !number_2.matches(regex) || (operations.equals("division") && new BigDecimal(number_2.replace(',', '.')).compareTo(BigDecimal.ZERO) == 0)){
            model.addAttribute("result", "Неверные входные данные");
            return "calculator";
        }
        CalculationComponents components = new CalculationComponents(number_1.replace(',','.'), number_2.replace(',','.'), operations);
        model.addAttribute("result", components.getResult());
        return "calculator";
    }
    @PostMapping(value = "/calculator", params = "shutdown")
    public void shutdownServer(){
        CalculatorApplication.exitApplication();
    }

}
