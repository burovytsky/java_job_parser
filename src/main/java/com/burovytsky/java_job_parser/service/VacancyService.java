package com.burovytsky.java_job_parser.service;

import com.burovytsky.java_job_parser.model.Vacancy;
import com.burovytsky.java_job_parser.parser.RabotaUaParser;
import com.burovytsky.java_job_parser.repository.VacancyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final RabotaUaParser rabotaUaParser;

    public VacancyService(VacancyRepository vacancyRepository, RabotaUaParser rabotaUaParser) {
        this.vacancyRepository = vacancyRepository;
        this.rabotaUaParser = rabotaUaParser;
    }

    public List<Vacancy> getAll() {
        return new ArrayList<>(vacancyRepository.findAll(Sort.by(Sort.Direction.ASC, "created")));
    }

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public void findNewVacancy() {
        List<Vacancy> vacancies = rabotaUaParser.getVacancies("https://rabota.ua/zapros/java/pg1");
        try {
            vacancies.forEach(this::save);
        } catch (Exception e) {
            System.out.println("vacancy already exist");;
        }
    }

    public Vacancy findById(int id) {
        return vacancyRepository.findById(id).get();
    }
}
