package tn.esprit.spring.kaddem.entities;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ContratTest {

    @Test
    void defaultConstructor() {
        Contrat contrat = new Contrat();
        assertNull(contrat.getIdContrat());
        assertNull(contrat.getDateDebutContrat());
        assertNull(contrat.getDateFinContrat());
        assertNull(contrat.getSpecialite());
        assertNull(contrat.getArchive());
        assertNull(contrat.getMontantContrat());
        assertNull(contrat.getEtudiant());
    }

    @Test
    void parameterizedConstructor() {
        Date dateDebutContrat = new Date();
        Date dateFinContrat = new Date();
        Specialite specialite = Specialite.IA;
        Boolean archive = true;
        Integer montantContrat = 1000;
        Etudiant etudiant = new Etudiant();

        Contrat contrat = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, montantContrat);

        assertNull(contrat.getIdContrat());
        assertEquals(dateDebutContrat, contrat.getDateDebutContrat());
        assertEquals(dateFinContrat, contrat.getDateFinContrat());
        assertEquals(specialite, contrat.getSpecialite());
        assertEquals(archive, contrat.getArchive());
        assertEquals(montantContrat, contrat.getMontantContrat());
        assertNull(contrat.getEtudiant());
    }

    @Test
    void gettersAndSetters() {
        Contrat contrat = new Contrat();

        Integer idContrat = 1;
        Date dateDebutContrat = new Date();
        Date dateFinContrat = new Date();
        Specialite specialite = Specialite.CLOUD;
        Boolean archive = false;
        Integer montantContrat = 1500;
        Etudiant etudiant = new Etudiant();

        contrat.setIdContrat(idContrat);
        contrat.setDateDebutContrat(dateDebutContrat);
        contrat.setDateFinContrat(dateFinContrat);
        contrat.setSpecialite(specialite);
        contrat.setArchive(archive);
        contrat.setMontantContrat(montantContrat);
        contrat.setEtudiant(etudiant);

        assertEquals(idContrat, contrat.getIdContrat());
        assertEquals(dateFinContrat, contrat.getDateFinContrat());
        assertEquals(specialite, contrat.getSpecialite());
        assertEquals(archive, contrat.getArchive());
        assertEquals(montantContrat, contrat.getMontantContrat());
        assertEquals(etudiant, contrat.getEtudiant());
    }
}
