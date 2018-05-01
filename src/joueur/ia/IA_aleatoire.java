package joueur.ia;

import mastermind.LigneProposition;
import mastermind.Plateau;

public class IA_aleatoire extends IA{

	
	public IA_aleatoire(Plateau p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * choisir un code/proposition aleatoirement
	 */
	public void resolution() {
		LigneProposition essai = new LigneProposition(p.getNbTrou());
		
		for(int i = 0; i < p.getNbTrou(); i++) {
			essai.setAt(i, 1 + (int)(Math.random() * ((p.getNbCouleur() -1))));
		}
		
		p.ajouterEssai(essai);
	}

}
