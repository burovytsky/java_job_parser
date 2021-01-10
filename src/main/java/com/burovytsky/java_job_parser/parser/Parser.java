package com.burovytsky.java_job_parser.parser;

import com.burovytsky.java_job_parser.model.Vacancy;

import java.util.List;


public interface Parser {
    List<Vacancy> getVacancies(String link);
}
