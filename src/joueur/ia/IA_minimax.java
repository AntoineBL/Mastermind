package joueur.ia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mastermind.LigneProposition;
import mastermind.Plateau;

public class IA_minimax extends IA{

	private List<LigneProposition> setS = new LinkedList<LigneProposition>();
	private List<LigneProposition> setU;
	private boolean premierTour;
	LigneProposition l = null;
	
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
		
		setS = createSet();
		
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
	
	
	public void resolution() {
		
		int index = (int)(Math.random() * setS.size());
		System.out.println("Size: " + setS.size());
		System.out.println("Index " + index);

		
		if(premierTour) {
			l = setS.get(index);
			p.ajouterEssai(l);
			premierTour = false;
		} 
		else {
			
//			index = (int)(Math.random() * setS.size());
//			System.out.println("Size: " + setS.size());
//			System.out.println("Index " + index);
//			l = setS.get(index);
//			p.ajouterEssai(l);
//			setS.remove(l);
			l = setS.get(index);
			System.out.println("l : "+ l);
			setS.remove(l);
			System.out.println("FFFFFFFF");
			for (LigneProposition ligneProposition : setU) {
				System.out.println(ligneProposition);
			}
		}
	

		
		
	}

}
