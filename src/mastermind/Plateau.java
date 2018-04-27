package mastermind;

import java.util.ArrayList;

public class Plateau {

	private int nbTrou;
	private int nbCouleur;
	private ArrayList<LigneProposition> essais;
	private ArrayList<LigneIndice> indices;
	private CodeSolution codeSecret;
	
	//Création du plateau de jeu
	public Plateau(int nbTrou, int nbCouleur) {
		this.nbTrou = nbTrou;
		this.nbCouleur = nbCouleur;
		//this.codeSecret = new int[nbTrou];
		essais = (new ArrayList<LigneProposition>());
		indices = (new ArrayList<LigneIndice>());
	}
	
	public int getNbTrou() {
		return nbTrou;
	}

	public int getNbCouleur() {
		return nbCouleur;
	}
	
	public ArrayList<LigneProposition> getEssais() {
		return essais;
	}

	public ArrayList<LigneIndice> getIndices() {
		return indices;
	}

	//Choisir le code secret
	public void choisirCodeSecret(CodeSolution code) {
		
		codeSecret = code;
	}
	
	//ajouter un essai
	public void ajouterEssai(LigneProposition essai) {
		this.essais.add(essai);
	}
	
	//ajouter un indice
	public void ajouterIndice(LigneIndice indice) {
		this.indices.add(indice);
	}
	

	public int getNbRouge() {
		return indices.get(indices.size() -1).nbIndiceRouge();
	}
	
	public LigneProposition getLastLigneProposition() {
		
		return getEssais().get(this.essais.size()-1);
	}
	
}
