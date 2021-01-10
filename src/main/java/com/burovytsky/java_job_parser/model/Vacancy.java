package com.burovytsky.java_job_parser.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String link;
    private LocalDateTime created;

    public Vacancy() {
    }

    public Vacancy(String name, String link, LocalDateTime created) {
        this.name = name;
        this.link = link;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime date) {
        this.created = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, created);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", date=" + created +
                '}';
    }
}
