package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UniversiteServiceTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        // Add some sample universites to the list
        universites.add(new Universite(1, "Universite 1"));
        universites.add(new Universite(2, "Universite 2"));

        // Mock the behavior of the repository
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();

        assertNotNull(retrievedUniversites);
        assertEquals(2, retrievedUniversites.size());
    }

    @Test
    public void testAddUniversite() {
        Universite newUniversite = new Universite(3, "Universite 3");

        // Mock the behavior of the repository
        when(universiteRepository.save(newUniversite)).thenReturn(newUniversite);

        Universite addedUniversite = universiteService.addUniversite(newUniversite);

        assertNotNull(addedUniversite);
        assertEquals(newUniversite.getIdUniv(), addedUniversite.getIdUniv());
        assertEquals(newUniversite.getNomUniv(), addedUniversite.getNomUniv());
    }

}