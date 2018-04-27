package joueur.ia;

import java.util.ArrayList;
import java.util.Scanner;

import mastermind.CodeSolution;
import mastermind.LigneIndice;
import mastermind.PionIndice;
import mastermind.Plateau;

public class IAJoueurPassif extends IA{
	
	CodeSolution code;
	
	
	public IAJoueurPassif(Plateau p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	
	public CodeSolution getCode() {
		return code;
	}


	//ajout les indices pour le dernier essai
	public void resolution() {
		//ArrayList<PionIndice> indice = new ArrayList<PionIndice>();
		LigneIndice indice = new LigneIndice();
		Boolean[] traiteSolution = new Boolean[p.getNbTrou()];
		Boolean[] traiteProposition = new Boolean[p.getNbTrou()];
			
		//savoir si des pions sont a la bonne place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(p.getLastLigneProposition().getAt(i) == code.getAt(i)) {
				indice.add(PionIndice.ROUGE);
				traiteSolution[i] = true;
				traiteProposition[i] = true;
			}
			else {
				traiteSolution[i] = false;
				traiteProposition[i] = false;
				
			}
		}
		
		//savoir si il y a des pions à la mauvaise place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(!traiteProposition[i]) {
				for(int j=0; j < p.getNbTrou(); j++) {
					if(p.getLastLigneProposition().getAt(i) == code.getAt(j) && !traiteSolution[j]) {
						indice.add(PionIndice.BLANC);
						traiteProposition[i] = true;
						traiteSolution[j] = true;
						break;
					}
				}
			}			
		}
		
		System.out.println(indice.toString()+"\n");
		p.ajouterIndice(indice);
		
	}
	
	
	//choisir le code a trouver
	public void choisirCode(CodeSolution c) {
		
		c.createRandom();
		code = c;
		//p.choisirCodeSecret(code);
		p.choisirCodeSecret(c);
		System.out.println("Code secret choisi aléatoirement : " + code.toString());
		System.out.println("---------------------------------------");
	}
	
}
