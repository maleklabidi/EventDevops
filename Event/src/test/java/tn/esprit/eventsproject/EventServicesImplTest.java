package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetLogisticsDates() {
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = LocalDate.now().plusDays(5);

        Event event1 = new Event();
        event1.setIdEvent(1);

        Event event2 = new Event();
        event2.setIdEvent(2);

        Logistics logistics1 = new Logistics();
        logistics1.setIdLog(1);
        logistics1.setReserve(true);

        Logistics logistics2 = new Logistics();
        logistics2.setIdLog(2);
        logistics2.setReserve(false);

        event1.setLogistics(new HashSet<>(Collections.singletonList(logistics1)));
        event2.setLogistics(new HashSet<>(Collections.singletonList(logistics2)));

        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepository.findByDateDebutBetween(dateDebut, dateFin)).thenReturn(events);

        List<Logistics> result = eventServices.getLogisticsDates(dateDebut, dateFin);

        assertEquals(logistics1, result.get(0));

    }
}
