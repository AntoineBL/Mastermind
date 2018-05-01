package jeu;

import java.util.Scanner;

import joueur.ia.IA;
import joueur.ia.IAJoueurPassif;
import joueur.ia.IA_NonMinimax;
import joueur.ia.IA_aleatoire;
import joueur.ia.IA_minimax;
import mastermind.CodeSolution;
import mastermind.Plateau;

public class Jeu {

	private IAJoueurPassif passif;
	private IA actif;
	private Plateau p;
	private int nbTrou = 4;
	private int nbCouleur = 6;
	
	public Jeu() {
		p = new Plateau(nbTrou, nbCouleur);
		passif= new IAJoueurPassif(p);
		
	}
	
	
	public void jouer() {
		
		shooseIA();
		
		//choisir le code secret
		passif.choisirCode(new CodeSolution(nbTrou, nbCouleur));
		
		
		//boucle de jeu
		int nbTour = 0;
		 do{

			nbTour++;
			System.out.println("--------");
			System.out.println("Tour "+nbTour);
			System.out.println("--------");
			actif.resolution();
			System.out.print("Proposition: "+p.getLastLigneProposition().toString());
			passif.resolution();
			System.out.println("  ---  Indice : "+p.getIndices().toString()+"\n");
			
		}while (p.getNbRouge() < nbTrou);
		 
		System.out.print("vous avez gnagné en "+nbTour+ " coups. La réponse est :");
		System.out.print(passif.getCode().toString());
	}
	
	
	public void shooseIA() {
		
		System.out.println("Choisir l'IA pour résoudre le Mastermind : ");
		System.out.println("1 - IA aléatoire");
		System.out.println("2 - IA sans minimax");
		System.out.println("3 - IA avec minimax");
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		
		switch(str){
			case "1" : actif = new IA_aleatoire(p); System.out.println("IA aléatoire"); break;
			case "2" : actif = new IA_NonMinimax(p); System.out.println("IA sans minimax"); break;
			default : actif = new IA_minimax(p); System.out.println("IA avec minimax"); break;
		}
	}
}
