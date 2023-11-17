package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContratRestControllerTest {

    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @Test
    void getContrats() {
        List<Contrat> expectedContrats = new ArrayList<>();
        when(contratService.retrieveAllContrats()).thenReturn(expectedContrats);

        List<Contrat> result = contratRestController.getContrats();

        assertEquals(expectedContrats, result);
        verify(contratService, times(1)).retrieveAllContrats();
    }

    @Test
    void retrieveContrat() {
        Integer contratId = 1;
        Contrat expectedContrat = new Contrat();
        when(contratService.retrieveContrat(contratId)).thenReturn(expectedContrat);

        Contrat result = contratRestController.retrieveContrat(contratId);

        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).retrieveContrat(contratId);
    }

    @Test
    void addContrat() {
        Contrat inputContrat = new Contrat();
        Contrat expectedContrat = new Contrat();
        when(contratService.addContrat(inputContrat)).thenReturn(expectedContrat);

        Contrat result = contratRestController.addContrat(inputContrat);

        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).addContrat(inputContrat);
    }

    @Test
    void removeContrat() {
        Integer contratId = 1;

        contratRestController.removeContrat(contratId);

        verify(contratService, times(1)).removeContrat(contratId);
    }

    @Test
    void updateContrat() {
        Contrat inputContrat = new Contrat();
        Contrat expectedContrat = new Contrat();
        when(contratService.updateContrat(inputContrat)).thenReturn(expectedContrat);

        Contrat result = contratRestController.updateContrat(inputContrat);

        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).updateContrat(inputContrat);
    }

    @Test
    void assignContratToEtudiant() {
        Integer contratId = 1;
        String nomE = "John";
        String prenomE = "Doe";
        Contrat expectedContrat = new Contrat();
        when(contratService.affectContratToEtudiant(contratId, nomE, prenomE)).thenReturn(expectedContrat);

        Contrat result = contratRestController.assignContratToEtudiant(contratId, nomE, prenomE);

        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).affectContratToEtudiant(contratId, nomE, prenomE);
    }

    @Test
    void getnbContratsValides() {
        Date startDate = new Date();
        Date endDate = new Date();
        Integer expectedCount = 5;
        when(contratService.nbContratsValides(startDate, endDate)).thenReturn(expectedCount);

        Integer result = contratRestController.getnbContratsValides(startDate, endDate);

        assertEquals(expectedCount, result);
        verify(contratService, times(1)).nbContratsValides(startDate, endDate);
    }

    @Test
    void majStatusContrat() {
        contratRestController.majStatusContrat();

        verify(contratService, times(1)).retrieveAndUpdateStatusContrat();
    }

    @Test
    void calculChiffreAffaireEntreDeuxDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedChiffreAffaire = 1000.0f;
        when(contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        float result = contratRestController.calculChiffreAffaireEntreDeuxDates(startDate, endDate);

        assertEquals(expectedChiffreAffaire, result);
        verify(contratService, times(1)).getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }
}
