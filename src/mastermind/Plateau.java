package mastermind;

import java.util.ArrayList;

public class Plateau {

	private int nbTrou;
	private int nbCouleur;
	private ArrayList<int[]> essais;
	private ArrayList<ArrayList<Indice>> indices;
	private int[] codeSecret;
	
	//Création du plateau de jeu
	public Plateau(int nbTrou, int nbCouleur) {
		this.nbTrou = nbTrou;
		this.nbCouleur = nbCouleur;
		//this.codeSecret = new int[nbTrou];
		essais = (new ArrayList<int[]>());
		indices = (new ArrayList<ArrayList<Indice>>());
	}
	
	public int getNbTrou() {
		return nbTrou;
	}

	public int getNbCouleur() {
		return nbCouleur;
	}
	
	public ArrayList<int[]> getEssais() {
		return essais;
	}

	public ArrayList<ArrayList<Indice>> getIndices() {
		return indices;
	}

	//Choisir le code secret
	public void choisirCodeSecret(int[] code) {
		
		codeSecret = code;
	}
	
	//ajouter un essai
	public void ajouterEssai(int[] essai) {
		this.essais.add(essai);
	}
	
	//ajouter un indice
	public void ajouterIndice(ArrayList<Indice> indice) {
		this.indices.add(indice);
	}
	
	public int nbIndiceRouge() {
		int nbRouge = 0;
		for (Indice i : indices.get(indices.size()-1)) {
			if(i == Indice.ROUGE) {
				nbRouge++;
			}
		}
		return nbRouge;
	}
	
}
