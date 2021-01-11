package com.burovytsky.java_job_parser.parser;

import com.burovytsky.java_job_parser.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class WorkUaParser implements Parser {
    private final ArrayList<Vacancy> result = new ArrayList<>();
    @Override
    public List<Vacancy> getVacancies() {
        try {
            Document doc = Jsoup.connect("https://www.work.ua/jobs-java/?page=1").get();
            Elements cards = doc.select(".card-visited");
            for (Element element : cards) {
                String text = element.child(1).text();
                String name = element.child(1).child(0).attr("title");
                String vacancyLink = "www.work.ua" + element.child(1).child(0).attr("href");
                Vacancy vacancy = new Vacancy(text, vacancyLink, null);
                if (!vacancy.getName().contains("Middle") && !vacancy.getName().contains("Senior")) {
                    result.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
