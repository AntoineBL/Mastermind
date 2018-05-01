package joueur.ia;

import java.util.LinkedList;
import java.util.List;

import mastermind.LigneIndice;
import mastermind.LigneProposition;
import mastermind.PionIndice;
import mastermind.Plateau;

public class IA_NonMinimax extends IA{

	private List<LigneProposition> setS = new LinkedList<LigneProposition>();
	private List<LigneProposition> setU;
	private boolean premierTour;
	LigneProposition l = null;
	LigneIndice lastIndice;
	
	public IA_NonMinimax(Plateau p) {
		super(p);
		premierTour = true;
		
		setS = createSet();
		
		setU = new LinkedList<LigneProposition>(setS);


	}
	
	
	/**
	 * Créer toutes les solutions possibles en fonction du nombre de couleur et de la taille du code a trouver
	 * @return la liste de toutes les solutions
	 */
	public LinkedList<LigneProposition> createSet() {
		LinkedList<LigneProposition> l =  new LinkedList<>();
		LigneProposition lp, lplast;
		
		int nbSeta = (int) Math.pow(p.getNbCouleur(), p.getNbTrou());
		
		lp = new LigneProposition(p.getNbTrou());
		for(int j =0; j<p.getNbTrou(); j++) {
			lp.setAt(j, 1);
		}
		l.add(lp);

		for(int i = 1; i<nbSeta; i++) {
			lplast = lp;
			lp = new LigneProposition(p.getNbTrou());
			for(int j =0; j<p.getNbTrou(); j++) {
				lp.setAt(j, lplast.getAt(j));
			}
			
			lp.setAt(p.getNbTrou()-1, lplast.getAt(p.getNbTrou()-1)+1);
			for(int j = p.getNbTrou()-1; j>=0; j--) {
				if(lp.getAt(j) == p.getNbCouleur()+1) {
					lp.setAt(j, 1);
					if(j >= 0) {
						lp.setAt(j-1, lp.getAt(j-1)+1);
					}
				}
			}		
			l.add(lp);
		}
			
		return l;
	}
	
	/**
	 * 
	 */
	public void resolution() {
		
		int index = (int)(Math.random() * setS.size());
		
		if(premierTour) {
			l = setS.get(index);
			p.ajouterEssai(l);
			setS.remove(l);
			premierTour = false;
		} 
		else {
			
			lastIndice = p.getIndices();
			setU.remove(l);
			
			for (int i = 0; i < setS.size()-1; i++) {
				if(!testReponse(setS.get(i), l, lastIndice)) {
					setS.remove(setS.get(i));
					i--;
				}
			}

			l = setS.get((int)(Math.random() * setS.size()));
			p.ajouterEssai(l);
			setS.remove(l);
			
		}
	

		
		
	}
	
	/**
	 * 
	 * @param eachS
	 * @param solutionTemp
	 * @param reponse
	 * @return
	 */
	public boolean testReponse(LigneProposition eachS, LigneProposition solutionTemp, LigneIndice reponse) {

		LigneIndice indice = new LigneIndice();
		Boolean[] traiteSolution = new Boolean[p.getNbTrou()];
		Boolean[] traiteProposition = new Boolean[p.getNbTrou()];
			
		//savoir si des pions sont a la bonne place
		for(int i=0; i < p.getNbTrou(); i++) {
			if(eachS.getAt(i) == solutionTemp.getAt(i)) {
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
					if(eachS.getAt(i) == solutionTemp.getAt(j) && !traiteSolution[j]) {
						indice.add(PionIndice.BLANC);
						traiteProposition[i] = true;
						traiteSolution[j] = true;
						break;
					}
				}
			}			
		}
		
		return indice.isEqual(indice, reponse);
		
	}

}
