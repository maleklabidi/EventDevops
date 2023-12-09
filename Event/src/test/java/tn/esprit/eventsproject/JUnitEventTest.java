package tn.esprit.eventsproject;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JUnitEventTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Autowired
    private EventServicesImpl eventServices;

    @Test
    @Order(1)
    void addParticipantTest() {
        Participant participant = new Participant();
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.ORGANISATEUR);

        Participant savedParticipant = eventServices.addParticipant(participant);

        assertNotNull(savedParticipant);
        assertNotNull(savedParticipant.getIdPart());
        assertEquals("John", savedParticipant.getNom());
        assertEquals("Doe", savedParticipant.getPrenom());
        assertEquals(Tache.ORGANISATEUR, savedParticipant.getTache());

        System.out.println("addParticipantTest: Ok");
    }

    @Test
    @Order(2)
    void getLogisticsDatesTest() {
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = LocalDate.now().plusDays(5);
        List<Logistics> logisticsList = eventServices.getLogisticsDates(dateDebut, dateFin);
        assertNotNull(logisticsList);
        assertFalse(logisticsList.isEmpty());

        System.out.println("getLogisticsDatesTest: Ok");
    }
}






