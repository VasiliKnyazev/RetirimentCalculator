package com.luxoft.rcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CalculatorController {
    private Integer age;
    private Integer retireAge;

    @GetMapping("/")
    public String enter() {
        return "enter";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "age", required = false) Integer age,
                            @RequestParam(value = "retireAge", required = false) Integer retireAge,
                            Model model) {
        try {
            this.age = age;
            this.retireAge = retireAge;
            return result(model);
        } catch (NullPointerException e) {
            return enter();
        }
    }

    @GetMapping("/result")
    public String result(Model model) {
        try {
            LocalDate localDate = LocalDate.now();
            int presentYear = localDate.getYear();
            int ageDiff = this.retireAge - this.age;
            if(ageDiff > 0) {
                model.addAttribute("leftRetireAge", ageDiff);
                model.addAttribute("presentYear", presentYear);
                model.addAttribute("retireYear", presentYear + ageDiff);
            } else {
                model.addAttribute("leftRetireAge", 0);
                model.addAttribute("presentYear", presentYear);
            }
            return "result";
        } catch (NullPointerException e) {
            return enter();
        }
    }

}
