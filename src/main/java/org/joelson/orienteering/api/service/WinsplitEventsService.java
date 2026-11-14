package org.joelson.orienteering.api.service;

import org.joelson.orienteering.api.model.EventDTO;
import org.joelson.orienteering.util.DateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WinsplitEventsService {

    @Value("${winsplit.index.url}")
    String indexUrl;

    @Value("${winsplit.events.url}")
    String eventsUrl;

    public List<EventDTO> getEvents() {
        List<EventDTO> competitions = new ArrayList<>();
        Document document;
        try {
            Connection session = Jsoup.newSession();
            session.newRequest(indexUrl).get(); // needed to get events through eventsUrl
            document = session.newRequest(eventsUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element container = document.getElementsByClass("container").first();
        Element table = container.child(0);
        Element tableBody = table.child(0);
        for (int i = 2; i < tableBody.childrenSize() - 1; i += 1) {
            Element row = tableBody.child(i);
            LocalDate date = DateUtil.parseDate(row.child(0).text());
            Element link = row.child(1).child(0).child(0);
            String urlPart = link.attr("href");
            String name = link.text();
            String organizer = row.child(2).text();
            int classes = Integer.parseInt(row.child(4).text());
            competitions.add(new EventDTO(date, urlPart, name, organizer, classes));
        }

        return competitions;
    }
}
