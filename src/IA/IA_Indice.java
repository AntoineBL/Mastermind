package IA;

import java.util.ArrayList;
import java.util.Scanner;

import mastermind.Indice;
import mastermind.Plateau;

public class IA_Indice extends IA{
	
    private static final Scanner SCANNER = new Scanner(System.in);
	int[] code;
	public IA_Indice(Plateau p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	
	public int[] getCode() {
		return code;
	}


	//ajout les indices pour le dernier essai
	public void resolution() {
		ArrayList<Indice> indice = new ArrayList<Indice>();
		Boolean[] traiteSolution = new Boolean[p.getNbTrou()];
		Boolean[] traiteProposition = new Boolean[p.getNbTrou()];
			
		//savoir si des pions sont a la bonne place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(p.getEssais().get(p.getEssais().size()-1)[i] == code[i]) {
				indice.add(Indice.ROUGE);
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
					if(p.getEssais().get(p.getEssais().size()-1)[i] == code[j] && !traiteSolution[j]) {
						indice.add(Indice.BLANC);
						traiteProposition[i] = true;
						traiteSolution[j] = true;
						break;
					}
				}
			}			
		}
		
		System.out.println(indice);
		p.ajouterIndice(indice);
		
	}
	
	
	//choisir le code a trouver
	public void choisirCode() {
		code = new int[p.getNbTrou()];
		
		for(int i = 0; i < p.getNbTrou(); i++) {
			code[i] = 1 + (int)(Math.random() * ((p.getNbCouleur() -1)));
		}
		
		
		//p.choisirCodeSecret(code);
		p.choisirCodeSecret(code);
		System.out.println("Code secret choisi aléatoirement : " + code[0]+code[1]+code[2]+code[3]);
		System.out.println("---------------------------------------");
	}
	
	public void choisirCodeManuel() {
	    
	}
}
