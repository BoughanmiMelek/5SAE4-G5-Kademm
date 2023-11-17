package tn.esprit.spring.kaddem.entities;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UniversiteTest {

    @Test
    void defaultConstructor() {
        Universite universite = new Universite();
        assertNull(universite.getIdUniv());
        assertNull(universite.getNomUniv());
        assertNull(universite.getDepartements());
    }

    @Test
    void parameterizedConstructor() {
        Integer idUniv = 1;
        String nomUniv = "Test University";
        Universite universite = new Universite(idUniv, nomUniv);

        assertEquals(idUniv, universite.getIdUniv());
        assertEquals(nomUniv, universite.getNomUniv());
        assertNull(universite.getDepartements());
    }

    @Test
    void gettersAndSetters() {
        Universite universite = new Universite();

        Integer idUniv = 1;
        String nomUniv = "Test University";

        Set<Departement> departements = new HashSet<>();
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        departements.add(departement1);
        departements.add(departement2);

        universite.setIdUniv(idUniv);
        universite.setNomUniv(nomUniv);
        universite.setDepartements(departements);

        assertEquals(idUniv, universite.getIdUniv());
        assertEquals(nomUniv, universite.getNomUniv());
        assertEquals(departements, universite.getDepartements());
    }
}
