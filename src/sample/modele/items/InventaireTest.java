package sample.modele.items;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventaireTest {
    private Inventaire inventaire = new Inventaire();

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
    }

    @org.junit.jupiter.api.Test
    void ajouterObjet() {
        int bois = this.inventaire.trouverObjet("Bois");
        this.inventaire.ajouterObjet("Bois", 2);
        Assertions.assertEquals(2, this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.ajouterObjet("Bois", 3);
        Assertions.assertEquals(5,this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.ajouterObjet("Bois", -2);
        Assertions.assertEquals(5,this.inventaire.getListObjet().get(bois).getQuantite(), "Normalement on peut qu'ajouter des objets");
        this.inventaire.ajouterObjet("Bois", 0);
        Assertions.assertEquals(5,this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.ajouterObjet("MineraiBrut", 1);
        Assertions.assertEquals(1,this.inventaire.getListObjet().get(this.inventaire.trouverObjet("MineraiBrut")).getQuantite());
        Assertions.assertEquals(0, this.inventaire.getListObjet().get(this.inventaire.trouverObjet("Viande")).getQuantite());

    }

    @org.junit.jupiter.api.Test
    void enleverObjet() {
        int bois = this.inventaire.trouverObjet("Bois");
        this.inventaire.eneleverObjet("Bois", 2);
        Assertions.assertEquals(0, this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.ajouterObjet("Bois", 10);
        this.inventaire.eneleverObjet("Bois", 5);
        Assertions.assertEquals(5, this.inventaire.getListObjet().get(bois).getQuantite());
        Assertions.assertEquals(0, this.inventaire.getListObjet().get(this.inventaire.trouverObjet("Diamant")).getQuantite());
        this.inventaire.eneleverObjet("Bois", 0);
        Assertions.assertEquals(5, this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.eneleverObjet("Bois", 8);
        Assertions.assertEquals(0, this.inventaire.getListObjet().get(bois).getQuantite());
        this.inventaire.eneleverObjet("Bois", -4);
        Assertions.assertEquals(0, this.inventaire.getListObjet().get(bois).getQuantite());
    }

}