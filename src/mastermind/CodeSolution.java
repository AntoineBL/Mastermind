package mastermind;

public class CodeSolution {

	private int[] listeCouleurs;
	private int size;
	private int nbColor;
	
	public CodeSolution(int size, int nbColor) {
		
		 listeCouleurs = new int[size];
		 this.size = size;
		 this.nbColor = nbColor;
	}

	public int[] getListeCouleurs() {
		return listeCouleurs;
	}
	
	
	/**
	 * Créer le code secret aléatoirment
	 */
	public void createRandom() {
			
		for(int i = 0; i < size; i++) {
			listeCouleurs[i] = 1 + (int)(Math.random() * ((nbColor -1)));
		}
		
	}
	
	public int getAt(int i) {
		
		return listeCouleurs[i];
	}
	
	public String toString() {
		
		String s = "";
		for (int i : listeCouleurs) {
			s = s+i;
		}
		return s;
	}
	
}
