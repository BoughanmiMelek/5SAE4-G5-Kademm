package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;



    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Test
    void retrieveAllUniversites() {
        Universite universite1 = new Universite();
        Universite universite2 = new Universite();
        List<Universite> universites = Arrays.asList(universite1, universite2);

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(universites, result);
    }

    @Test
    void addUniversite() {
        Universite universite = new Universite();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    void updateUniversite() {
        Universite universite = new Universite();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    void retrieveUniversite() {
        Universite universite = new Universite();
        int idUniversite = 1;

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(idUniversite);

        assertEquals(universite, result);
    }

    @Test
    void deleteUniversite() {
        Universite universite = new Universite();
        int idUniversite = 1;

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(idUniversite);

        verify(universiteRepository, times(1)).delete(universite);
    }



    @Test
    void retrieveDepartementsByUniversite() {
        Universite universite = new Universite();
        Set<Departement> departements = new HashSet<>();
        universite.setDepartements(departements);
        int idUniversite = 1;

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(idUniversite);

        assertEquals(departements, result);
    }
}
