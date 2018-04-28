package mastermind;

public class LigneProposition {
	
	private int[] listeCouleurs;
	private int size;
	
	public LigneProposition(int size) {
		
		 listeCouleurs = new int[size];
		 this.size = size;
	}
	

	public LigneProposition(int a, int b, int c, int d) {
		
		 listeCouleurs = new int[4];
		 listeCouleurs[0] = a;
		 listeCouleurs[1] = b;
		 listeCouleurs[2] = c;
		 listeCouleurs[3] = d;
	}
	
	public void setAt(int i, int color) {
		
		listeCouleurs[i] = color;
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





