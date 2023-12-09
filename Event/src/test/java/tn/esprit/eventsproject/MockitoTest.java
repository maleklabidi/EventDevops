package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @Test
    void testAddParticipant() {
        // Create a sample participant for testing
        Participant participant = new Participant();
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.ORGANISATEUR);

        // Mock the behavior of the participantRepository.save method
        when(participantRepository.save(participant)).thenReturn(participant);

        // Call the method to be tested
        Participant savedParticipant = eventServices.addParticipant(participant);

        // Verify that the save method was called with the correct participant
        verify(participantRepository, times(1)).save(participant);

        // Assert that the returned participant is not null
        assertNotNull(savedParticipant);

        // You can add more assertions based on your specific requirements
    }
}
