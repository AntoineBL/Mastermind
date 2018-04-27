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
		Boolean[] traite = new Boolean[p.getNbTrou()];
			
		//savoir si des pions sont a la bonne place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(p.getEssais().get(p.getEssais().size()-1)[i] == code[i]) {
				indice.add(Indice.ROUGE);
				traite[i] = true;
			}
			else {
				traite[i] = false;
			}
		}
		
		
		//savoir si il y a des pions � la mauvaise place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(!traite[i]) {
				for(int j=0; j < p.getNbTrou(); j++) {
					if(p.getEssais().get(p.getEssais().size()-1)[i] == code[j] && !traite[j]) {
						indice.add(Indice.BLANC);
						traite[i] = true;
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
			code[i] = 1 + (int)(Math.random() * ((p.getNbCouleur()) + 1));
		}
		
		p.choisirCodeSecret(code);
		System.out.println("Code secret choisi al�atoirement : " + code[0]+code[1]+code[2]+code[3]);
		System.out.println("---------------------------------------");
	}
	
	public void choisirCodeManuel() {
	    
	}
}
