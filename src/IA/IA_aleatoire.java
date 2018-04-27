package IA;

import mastermind.Plateau;

public class IA_aleatoire extends IA{

	public IA_aleatoire(Plateau p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	
	//choisir un code aleatoirement
	public void resolution() {
		int[] essai = new int[p.getNbTrou()];
		
		for(int i = 0; i < p.getNbTrou(); i++) {
			essai[i] = 1 + (int)(Math.random() * ((p.getNbCouleur()) + 1));
		}
		
		System.out.println("Proposition de l'IA :" + essai[0]+essai[1]+essai[2]+essai[3]);
		p.ajouterEssai(essai);
	}

}
