package sample.modele.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventaireTest {
    private Inventaire inventaire = new Inventaire();
    private Inventaire invExpected = new Inventaire();

    @Test
    void testEstDisponible() {
        this.inventaire.ajouterObjet("Miel", 2);
        Assertions.assertTrue(this.inventaire.estDisponible("Miel",1));
        Assertions.assertFalse(this.inventaire.estDisponible("Or",1), "Or n'est pas un objet");
        Assertions.assertFalse(this.inventaire.estDisponible("Miel", 10));
        Assertions.assertTrue(this.inventaire.estDisponible("Miel", 0));
        Assertions.assertFalse(this.inventaire.estDisponible("Miel", -2));
    }

    @org.junit.jupiter.api.Test
    void trouverObjet() {
//       0 Argent | 1 Bois | 2 Diamant | 3 Fer | 4 Miel | 5 MineraiBrut | 6 Nouilles | 7 Potion | 8 Viande
        Assertions.assertEquals(2, this.inventaire.trouverObjet("Diamant"));
        Assertions.assertNotEquals(0, this.inventaire.trouverObjet("Bois") );
        Assertions.assertEquals(-1, this.inventaire.trouverObjet("bonjour"), "Bonjour n'est pas un objet");
        Assertions.assertEquals(-1, this.inventaire.trouverObjet("Or"), "Or n'est pas un objet");
    }

    @org.junit.jupiter.api.Test
    void ajouterObjet() {
        this.inventaire.setNbrOr(10);
        this.invExpected.getListObjet().get(this.invExpected.trouverObjet("Bois")).setQuantite(2);
//        Assertions.assertEquals(this.invExpected, this.inventaire.ajouterObjet("Bois", 2));
    }

    @org.junit.jupiter.api.Test
    void acheterObjet() {
    }

    @org.junit.jupiter.api.Test
    void enleverObjet() {

    }



}