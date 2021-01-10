package com.burovytsky.java_job_parser.repository;

import com.burovytsky.java_job_parser.model.Vacancy;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
    Collection<Vacancy> findAll(Sort id);
}
