package tn.esprit.spring.kaddem.entities;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EquipeTest {

    @Test
    void defaultConstructor() {
        Equipe equipe = new Equipe();
        assertNull(equipe.getIdEquipe());
        assertNull(equipe.getNomEquipe());
        assertNull(equipe.getNiveau());
        assertNull(equipe.getEtudiants());
        assertNull(equipe.getDetailEquipe());
    }

    @Test
    void parameterizedConstructor() {
        String nomEquipe = "TestEquipe";
        Niveau niveau = Niveau.JUNIOR;
        Set<Etudiant> etudiants = new HashSet<>();
        DetailEquipe detailEquipe = new DetailEquipe();

        Equipe equipe = new Equipe(nomEquipe, niveau, etudiants, detailEquipe);

        assertNull(equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }

    @Test
    void gettersAndSetters() {
        Equipe equipe = new Equipe();

        Integer idEquipe = 1;
        String nomEquipe = "TestEquipe";
        Niveau niveau = Niveau.JUNIOR;
        Set<Etudiant> etudiants = new HashSet<>();
        DetailEquipe detailEquipe = new DetailEquipe();

        equipe.setIdEquipe(idEquipe);
        equipe.setNomEquipe(nomEquipe);
        equipe.setNiveau(niveau);
        equipe.setEtudiants(etudiants);
        equipe.setDetailEquipe(detailEquipe);

        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }
}
