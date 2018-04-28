package jeu;

import joueur.ia.IAJoueurPassif;
import joueur.ia.IA_NonMinimax;
import joueur.ia.IA_aleatoire;
import joueur.ia.IA_minimax;
import mastermind.CodeSolution;
import mastermind.Plateau;

public class Jeu {

	private IAJoueurPassif passif;
	private IA_minimax actif;
	private Plateau p;
	private int nbTrou = 4;
	private int nbCouleur = 6;
	
	public Jeu() {
		p = new Plateau(nbTrou, nbCouleur);
		passif= new IAJoueurPassif(p);
		actif = new IA_minimax(p);
	}
	
	
	public void jouer() {
		
		
		passif.choisirCode(new CodeSolution(nbTrou, nbCouleur));
		
		int nbTour = 0;
		 do{

			nbTour++;
			System.out.println("--------");
			System.out.println("Tour "+nbTour);
			System.out.println("--------");
			actif.resolution();
			passif.resolution();
			
		}while (p.getNbRouge() < nbTrou);
		 
		System.out.println("vous avez gnagné en "+nbTour+ ". La réponse est :");
		System.out.print(passif.getCode().toString());
	}
	
}
