/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm63bingogen.Model;

/**
 *
 * @author Kévin
 */
public class Grid {
    
    private int largeur;
    private int longueur;
    private int nbGoals;
    
    public Grid() {
        this.largeur = 5;
        this.longueur = 5;
        this.nbGoals = 25;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
        this.setNbGoals();
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
        this.setNbGoals();
    }

    public int getNbGoals() {
        return nbGoals;
    }

    public void setNbGoals() {
        this.nbGoals = largeur*longueur;
    }
    
    
    
}
