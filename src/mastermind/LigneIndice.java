package mastermind;

import java.util.ArrayList;
import java.util.Iterator;

public class LigneIndice {

	private ArrayList<PionIndice> listePions;
	
	public LigneIndice() {
		
		listePions = new ArrayList<PionIndice>();
	}
	
	/**
	 * Ajout un pion à la liste des indices
	 * @param pion
	 */
	public void add(PionIndice pion) {
		
		listePions.add(pion);
	}

	/**
	 * @return le nombre de pion rouge dans la ligne d'indice
	 */
	public int nbIndiceRouge() {
		int nbRouge = 0;
		for (PionIndice i : listePions) {
			if(i == PionIndice.ROUGE) {
				nbRouge++;
			}
		}
		return nbRouge;
	}
	
	/**
	 * 
	 * @return le nombre de pion blanc dans la ligne d'indice
	 */
	public int nbIndiceBlanc() {
		int nbBlanc = 0;
		for (PionIndice i : listePions) {
			if(i == PionIndice.BLANC) {
				nbBlanc++;
			}
		}
		return nbBlanc;
	}
	
	public String toString() {
		
		String s = "";
		s = s + "[ ";
		for (PionIndice pionIndice : listePions) {
			s = s + pionIndice +" ";
		}
		s = s + "]";
		return s;
	}
	
	/**
	 * Compare 2 lignes d'indices
	 * @param li1
	 * @param li2
	 * @return True si les lignes d'indices sont identique, sinon False
	 */
	public boolean isEqual(LigneIndice li1, LigneIndice li2) {
		
		return (li1.nbIndiceRouge() == li2.nbIndiceRouge() && li1.nbIndiceBlanc() == li2.nbIndiceBlanc());
		
	}
	
}



