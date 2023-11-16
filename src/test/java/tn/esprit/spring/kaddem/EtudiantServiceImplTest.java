package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import static org.mockito.Mockito.when;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        // Add some sample etudiants to the list
        etudiants.add(new Etudiant("Will", "Smith"));
        etudiants.add(new Etudiant("Ben", "Bane"));

        // Mock the behavior of the repository
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        assertNotNull(retrievedEtudiants);
        assertEquals(2, retrievedEtudiants.size());
    }

    @Test
    public void testAddEtudiant() {
        Etudiant newEtudiant = new Etudiant("Seth", "Rogue");

        // Mock the behavior of the repository
        when(etudiantRepository.save(newEtudiant)).thenReturn(newEtudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(newEtudiant);

        assertNotNull(addedEtudiant);
        assertEquals(newEtudiant.getIdEtudiant(), addedEtudiant.getIdEtudiant());
        assertEquals(newEtudiant.getNomE(), addedEtudiant.getNomE());
    }

    // Add test cases for other methods like updateEtudiant, retrieveEtudiant, removeEtudiant, assignEtudiantToDepartement, and addAndAssignEtudiantToEquipeAndContract.

    @Test
    public void testGetEtudiantsByDepartement() {
        int departementId = 5;
        Departement departement = new Departement(departementId, "Dept 5");
        List<Etudiant> etudiantsInDepartement = new ArrayList<>();

        // Add some etudiants to the list
        etudiantsInDepartement.add(new Etudiant("John", "Doe"));
        etudiantsInDepartement.add(new Etudiant("Clide", "Wilson"));

        departement.setEtudiants(new HashSet<>(etudiantsInDepartement));

        // Mock the behavior of the repository
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(departementId)).thenReturn(etudiantsInDepartement);

        List<Etudiant> etudiantsByDepartement = etudiantService.getEtudiantsByDepartement(departementId);

        assertNotNull(etudiantsByDepartement);
        assertEquals(2, etudiantsByDepartement.size());
    }
}