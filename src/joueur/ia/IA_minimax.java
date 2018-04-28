package joueur.ia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mastermind.LigneIndice;
import mastermind.LigneProposition;
import mastermind.PionIndice;
import mastermind.Plateau;

public class IA_minimax extends IA{

	private List<LigneProposition> setS = new LinkedList<LigneProposition>();
	private List<LigneProposition> setU;
	private List<LigneIndice> setIndice = new LinkedList<LigneIndice>();
	private boolean premierTour;
	LigneProposition l = null;
	LigneIndice lastIndice;
	
	public IA_minimax(Plateau p) {
		super(p);
		premierTour = true;
		
//		setS.add(new LigneProposition(1,1));
//		setS.add(new LigneProposition(1,2));
//		setS.add(new LigneProposition(1,3));
//		setS.add(new LigneProposition(2,1));
//		setS.add(new LigneProposition(2,2));
//		setS.add(new LigneProposition(2,3));
//		setS.add(new LigneProposition(3,1));
//		setS.add(new LigneProposition(3,2));
//		setS.add(new LigneProposition(3,3));
		
//		LigneIndice li1 = new LigneIndice();
//		LigneIndice li2 = new LigneIndice();
//		LigneIndice li3 = new LigneIndice();
//		LigneIndice li4 = new LigneIndice();
//		LigneIndice li5 = new LigneIndice();
//		li2.add(PionIndice.BLANC);
//		li3.add(PionIndice.BLANC); li2.add(PionIndice.BLANC);
//		li4.add(PionIndice.ROUGE);
//		li5.add(PionIndice.BLANC); li5.add(PionIndice.BLANC);
//		setIndice.add(li1);
//		setIndice.add(li2);
//		setIndice.add(li3);
//		setIndice.add(li4);
//		setIndice.add(li5);
//		
		setS = createSet();
		setIndice = createSetIndice();
		
		setU = new LinkedList<LigneProposition>(setS);


	}
	
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
	
	
public LinkedList<LigneIndice> createSetIndice() {
		
		LinkedList<LigneIndice> l =  new LinkedList<>();
		LigneIndice lp, lplast;
		
		int nbSeta = (int) Math.pow(3, p.getNbTrou());
		
		lp = new LigneIndice();

		int k =0;
		while(lp.nbIndiceBlanc() != p.getNbTrou()) {
			
			for(int i=k; i<=p.getNbTrou(); i++) {
				lp = new LigneIndice();
				for(int m = 0; m<k; m++) {
					lp.add(PionIndice.BLANC);
				}
				
				for(int j = k; j<i; j++) {
					lp.add(PionIndice.ROUGE);
				}

				//if(lp.nbIndiceBlanc() != 1 || lp.nbIndiceRouge() != p.getNbTrou()-1) {
					l.add(lp);
				//}
				
			}
			
			k++;
		}
			
		return l;
		
	}
	
	public void resolution() {
		
		int index = (int)(Math.random() * setS.size());
		System.out.println("Size: " + setS.size());
		System.out.println("Index " + index);

		
		if(premierTour) {
			l = setS.get(index);
			p.ajouterEssai(l);
			System.out.println("Proposition : " + l.toString());
			setS.remove(l);
			premierTour = false;
		} 
		else {
			
			lastIndice = p.getIndices();
			setU.remove(l);
			
			//testReponse(setS.get(0), l, lastIndice);
			int size = setS.size();
			for (int i = 0; i < size; i++) {
				if(!testReponse(setS.get(i), l, lastIndice)) {
					setS.remove(setS.get(i));
					i--;
					size--;
				}
			}
			
//			System.out.println("Etat de la liste setS : " );
//			for (LigneProposition ligneProposition : setS) {
//				System.out.println(ligneProposition.toString());
//			}
			
			//l = setS.get((int)(Math.random() * setS.size()));
			//p.ajouterEssai(l);
			//System.out.println("Proposition : " + l.toString());
			//setS.remove(l);
			
			
			int scoreUMax = 0;
			LigneProposition lpMax = null;
			for (LigneProposition lpu : setU) {
				
				int compteurMin = Integer.MAX_VALUE;
				for (LigneIndice ligneI : setIndice) {
					
					int compteur = 0;
					for (LigneProposition lps : setS) {
						if(!testReponse(lps, lpu, ligneI)) {
							compteur++;
						}
					}
					if(compteur < compteurMin) {
						compteurMin = compteur;
					}
				}
				if(compteurMin >= scoreUMax ) {
					scoreUMax = compteurMin;
					lpMax = lpu;
				}
			}
			System.out.println("OKKKKKKKKKKK : " + scoreUMax );
			System.out.println("VIRGILLLLE  !!!" + lpMax.toString());
			
			
			l = setS.get((int)(Math.random() * setS.size()));
			p.ajouterEssai(l);
			System.out.println("Proposition : " + l.toString());
			setS.remove(l);
			
		}
	

		
		
	}
	
	public boolean testReponse(LigneProposition eachS, LigneProposition solutionTemp, LigneIndice reponse) {
		//ArrayList<PionIndice> indice = new ArrayList<PionIndice>();
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
		
//		System.out.print("EACHS : " + eachS.toString());
//		System.out.println("INDICE RENVOYE PAR LA FONCTION : " + indice.toString()+"\n");
//		System.out.println("INDICE RECHERCHER : " + reponse.toString());
//		
//		System.out.println("BOOLEAN =  " + indice.isEqual(indice, reponse));
		return indice.isEqual(indice, reponse);
		
	}

}
