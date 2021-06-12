package sample.modele.items.Armes;

import sample.modele.Projectile;

import java.util.ArrayList;

public class ShopInventory {
    ArrayList<Arme> inventoryShop;

    public ShopInventory(){
        this.inventoryShop=new ArrayList<Arme>();
        inventoryShop.add(new Epee());
        inventoryShop.add(new EpeeLongue());
        inventoryShop.add(new Gourdin());
        inventoryShop.add(new Hache());
        inventoryShop.add(new Katana());
        inventoryShop.add(new Lance());
        inventoryShop.add(new Marteau());
    }

    public String getArmeDescritpion(String nom){
        for(Arme a : inventoryShop) {
            if (nom.endsWith(a.getClass().getSimpleName()))
                return a.getShopDescription();
        }
        return "";
    }
    public Arme chercherArme(String nom){
        for (Arme a : inventoryShop){
            if (a.getNom().equals(nom))
                return a;
        }
        return null;
    }
}
