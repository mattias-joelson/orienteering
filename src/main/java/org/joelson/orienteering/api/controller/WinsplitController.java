package org.joelson.orienteering.api.controller;

import org.joelson.orienteering.api.model.EventDTO;
import org.joelson.orienteering.api.service.WinsplitEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/winsplit")
public class WinsplitController {

    @Autowired
    WinsplitEventsService eventsService;

    @GetMapping(path = "/events")
    public List<EventDTO> getEvents() {
        return eventsService.getEvents();
    }
}
