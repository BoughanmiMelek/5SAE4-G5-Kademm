package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;


    @InjectMocks
    private ContratServiceImpl contratService;

    public ContratServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllContrats() {
        List<Contrat> contrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findAll()).thenReturn(contrats);
        List<Contrat> result = contratService.retrieveAllContrats();
        assertEquals(contrats, result);
    }

    @Test
    void updateContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat result = contratService.updateContrat(contrat);
        assertEquals(contrat, result);
    }

    @Test
    void addContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat result = contratService.addContrat(contrat);
        assertEquals(contrat, result);
    }

    @Test
    void retrieveContrat() {
        int idContrat = 1;
        Contrat contrat = new Contrat();
        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));
        Contrat result = contratService.retrieveContrat(idContrat);
        assertEquals(contrat, result);
    }

    @Test
    void removeContrat() {
        int idContrat = 1;
        Contrat contrat = new Contrat();
        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));
        contratService.removeContrat(idContrat);
        verify(contratRepository, times(1)).delete(contrat);
    }


    @Test
    void nbContratsValides() {
        Date startDate = new Date();
        Date endDate = new Date();

        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        int result = contratService.nbContratsValides(startDate, endDate);
        assertEquals(5, result);
    }



    @Test
    void getChiffreAffaireEntreDeuxDates() {
        Date startDate = new Date();
        Date endDate = new Date();

        Contrat contrat1 = new Contrat();
        contrat1.setSpecialite(Specialite.IA);

        Contrat contrat2 = new Contrat();
        contrat2.setSpecialite(Specialite.CLOUD);

        Contrat contrat3 = new Contrat();
        contrat3.setSpecialite(Specialite.RESEAUX);

        Contrat contrat4 = new Contrat();
        contrat4.setSpecialite(Specialite.SECURITE);

        List<Contrat> contrats = Arrays.asList(contrat1, contrat2, contrat3, contrat4);
        when(contratRepository.findAll()).thenReturn(contrats);

        float expected = (float) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24 * 30))
                * (4 * 300 + 400 + 350 + 450);

        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        assertEquals(expected, result);
    }

}
