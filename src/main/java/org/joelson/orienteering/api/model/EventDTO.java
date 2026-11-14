package org.joelson.orienteering.api.model;

import java.time.LocalDate;

public record EventDTO(
        LocalDate date,
        String urlPart,
        String name,
        String organizer,
        int classes
) {
}
