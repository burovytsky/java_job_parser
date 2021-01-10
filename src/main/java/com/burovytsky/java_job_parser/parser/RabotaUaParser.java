package com.burovytsky.java_job_parser.parser;

import com.burovytsky.java_job_parser.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RabotaUaParser implements Parser {
    private final ArrayList<Vacancy> result = new ArrayList<>();

    public RabotaUaParser() {
    }

    @Override
    public ArrayList<Vacancy> getVacancies(String link) {
        try {
            Document doc = Jsoup.connect(link).get();
            Elements cards = doc.select(".card-title");
            String vacancyLink;
            for (Element element : cards) {
                vacancyLink = element.child(0).attr("href");
                Vacancy vacancy = getVacancyDetail(vacancyLink);
                if (!vacancy.getName().contains("Middle") && !vacancy.getName().contains("Senior")) {
                    result.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Vacancy> getList() {
        return result;
    }
    private Vacancy getVacancyDetail(String link) {
        StringBuilder fullLink = new StringBuilder();
        fullLink.append("https://rabota.ua").append(link);
        Document doc = null;
        try {
            doc = Jsoup.connect(fullLink.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        String name = doc.select(".vacancy-ssr-title").text();
        String stringDate = doc.select(".vacancy-ssr-info").get(0).child(2).text();
        LocalDateTime date = new RabotaUaDateConverter().formatDate(stringDate);
        return new Vacancy(name, fullLink.toString(), date);
    }
}
