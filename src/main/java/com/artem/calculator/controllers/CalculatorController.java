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
        model.addAttribute("firstNumber", BigDecimal.ZERO);
        model.addAttribute("secondNumber", BigDecimal.ZERO);
        model.addAttribute("thirdNumber", BigDecimal.ZERO);
        model.addAttribute("fourthNumber", BigDecimal.ZERO);
        model.addAttribute("operation_1", "sum");
        return "calculator";
    }
    @PostMapping(value = "/calculator", params = "submit")
    public String postCalculator(@RequestParam String number_1, @RequestParam String number_2,
                                 @RequestParam String number_3, @RequestParam String number_4,
                                 @RequestParam String operation_1, @RequestParam String operation_2, @RequestParam String operation_3,
                                 @RequestParam String roundingMode,
                                 Model model){
        model.addAttribute("firstNumber", number_1);
        model.addAttribute("secondNumber", number_2);
        model.addAttribute("thirdNumber", number_3);
        model.addAttribute("fourthNumber", number_4);
        if (!number_1.matches(regex)){
            model.addAttribute("result", "Неверная запись числа 1");
            return "calculator";
        }
        if (!number_2.matches(regex)){
            model.addAttribute("result", "Неверная запись числа 2");
            return "calculator";
        }
        if (!number_3.matches(regex)){
            model.addAttribute("result", "Неверная запись числа 3");
            return "calculator";
        }
        if (!number_4.matches(regex)){
            model.addAttribute("result", "Неверная запись числа 4");
            return "calculator";
        }
//        if ((operations.equals("division") && new BigDecimal(number_2.replace(',', '.').replaceAll(" ","")).compareTo(BigDecimal.ZERO) == 0)) {
//            model.addAttribute("result", "Нельзя делить на 0");
//            return "calculator";
//        }

        String[] operations = new String[]{operation_1, operation_2, operation_3};
        CalculationComponents components;
        try {
            components = new CalculationComponents(
                    number_1.replace(',','.').replaceAll(" ", ""),
                    number_2.replace(',','.').replaceAll(" ", ""),
                    number_3.replace(',','.').replaceAll(" ", ""),
                    number_4.replace(',','.').replaceAll(" ", ""),
                    operations,
                    roundingMode);
        } catch (ArithmeticException e) {
            model.addAttribute("result", "Деление на 0");
            return "calculator";
        }
        model.addAttribute("result", components.getFormattedResult());
        model.addAttribute("roundedResult", components.getRoundedResult());
        return "calculator";
    }
    @PostMapping(value = "/calculator", params = "shutdown")
    public void shutdownServer(){
        CalculatorApplication.exitApplication();
    }

}
