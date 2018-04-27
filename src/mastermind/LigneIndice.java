package mastermind;

import java.util.ArrayList;
import java.util.Iterator;

public class LigneIndice {

	private ArrayList<PionIndice> listePions;
	
	public LigneIndice() {
		
		listePions = new ArrayList<PionIndice>();
	}
	
	public void add(PionIndice pion) {
		
		listePions.add(pion);
	}

	public int nbIndiceRouge() {
		int nbRouge = 0;
		for (PionIndice i : listePions) {
			if(i == PionIndice.ROUGE) {
				nbRouge++;
			}
		}
		return nbRouge;
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
	
}
