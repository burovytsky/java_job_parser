package com.burovytsky.java_job_parser.controller;

import com.burovytsky.java_job_parser.service.VacancyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final VacancyService vacancyService;

    public IndexController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("vacancies", vacancyService.getAll());
        return "index";
    }

    @GetMapping({"/refresh"})
    public String refresh(Model model) {
        vacancyService.findNewVacancy();
        model.addAttribute("vacancies", vacancyService.getAll());
        return "index";
    }
}