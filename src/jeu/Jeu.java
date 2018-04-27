package jeu;

import IA.IA_Indice;
import IA.IA_aleatoire;
import mastermind.Plateau;

public class Jeu {

	private IA_Indice passif;
	private IA_aleatoire actif;
	private Plateau p;
	private int nbTrou = 4;
	private int nbCouleur = 6;
	
	public Jeu() {
		p = new Plateau(nbTrou, nbCouleur);
		passif= new IA_Indice(p);
		actif = new IA_aleatoire(p);
	}
	
	
	public void jouer() {
		
		passif.choisirCode();
		
		int nbTour = 0;
		 do{
			nbTour++;
			System.out.println("Tour "+nbTour);
			actif.resolution();
			passif.resolution();
			
		}while (p.nbIndiceRouge() < nbTrou);
		 
		System.out.println("vous avez gnagné en "+nbTour+ ". La réponse est :");
		for(int i = 0; i < nbTrou; i++) {
			System.out.print(passif.getCode()[i]);
		}
	}
	
}
