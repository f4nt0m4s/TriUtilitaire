import java.lang.Math;
import java.util.Arrays;

/**
	* Classe TriUtilitaire 
	* @author 	: -
	* @version 	: 1.0
*/

public class TriUtilitaire
{	
	/**
		* @return Création d'un tableau et retourne le tableau.
		* @param nbElt : le nombre d'élement du tableau.
		* @param borneInf : la borne inférieur.
		* @param borneSup : la borne supérieur.
	*/
	public static int[] creerTableau(int nbElt, int borneInf, int borneSup)
	{
		int[] tab = new int[nbElt];
		
		for(int cpt=0; cpt < nbElt; cpt++)
		{
			tab[cpt] = (int) (Math.random() * ( borneSup - borneInf ));
		}

		return tab;
	}
	
	/**
		* Le trie à bulle.
		* @param tab 	: le tableau à trier avec le tri  à bulle.
		* @param n 	: la taille du tableau.
	*/
	public static void trierBulle(int[] tab, int n)
	{
		for(int cpt=0; cpt < n-1; cpt++)
		{
			if( tab[cpt] > tab[cpt+1] )
			{
				permuter(tab, cpt, cpt+1);
			}
		}
	}

	/**
		* Le trie par sélection.
		* @param tab : le tableau à trier avec le tri par sélection.
	*/
	public static void trierSelection(int[] tab)
	{
		for(int cpt=0; cpt < tab.length; cpt++) 
		{
			int min = cpt;
			for (int cpt2=cpt+1; cpt2 < tab.length; cpt2++) 
			{
				if( tab[cpt2] < tab[min] ) 
				{
					min = cpt2;
				}
			}
			
			if ( min != cpt )
			{
				permuter(tab, cpt, min);
			}
		}
	}
	
	/**
		* Le trie par insertion.
		* @param tab : le tableau à trier avec le tri par insertion.
	*/
	public static void trierInsertion(int []tab)
	{
		int n = tab.length;  
		for(int j=1; j < n; j++) 
		{
			int temp = tab[j];  
			int i = j-1;  
			while ( (i > -1) && ( tab[i] > temp ) ) 
			{  
				tab[i+1] = tab[i];  
				i--;  
			}  
			tab[i+1] = temp;  
		}
	}
	
	/**
		* Le tri rapide avec récursivité.
		* @param tab : le tableau à trier avec le tri rapide.
		* @param debut : indice de début.
		* @param fin : indice de fin.
	*/
	public static void trierRapide(int[] tab, int debut, int fin)
	{
		int pivot = 0;

		if ( debut < fin )
		{
			pivot = TriUtilitaire.partionner(tab, debut, fin);
			TriUtilitaire.trierRapide(tab, debut, pivot-1);
			TriUtilitaire.trierRapide(tab, pivot+1, tab.length-1);
		}
	}

	/**
		* @return Partionne un tableau et le tri de manière itérative et retourne l'indice du pivot.
		* @param tab : le tableau à trier.
		* @param debut : indice de début.
		* @param fin : indice de fin.
	*/
	private static int partionner(int tab[], int debut, int fin)
	{
		int indPivot;
		indPivot = debut++;
		
		while( fin >= debut )
		{
			if ( tab[debut] <= tab[indPivot] )
			{
				TriUtilitaire.permuter(tab, debut++, indPivot++);
			}
			else
			{
				TriUtilitaire.permuter(tab, debut, fin--);
			}
		}
		return indPivot;
	}
	
	/**
		* Le tri fusion avec récursivité. (tri interclassement)
	 	* @param tab : le tableau à trier avec le tri fusion.
		* @param debut : indice de début.
		* @param fin : indice de fin.
	*/
	public static void trierFusion(int[] tab, int debut, int fin)
	{
		int milieu = 0;
		if ( debut != fin ) // debut != fin )
		{
			milieu = ( (fin+debut) / 2 );
			TriUtilitaire.trierFusion(tab, debut, milieu);
			TriUtilitaire.trierFusion(tab, milieu+1, fin);
			
			TriUtilitaire.interclasser(tab, debut, fin, milieu);
		}
	}

	/**
		* Interclassement d'un tableau
		* @param tab : le tableau à interclasser avec le tri fusion.
		* @param debut : indice de début.
		* @param fin : indice de fin.
		* @param milieu : indice du milieu.
	*/
	private static void interclasser(int[] tab, int debut, int fin, int milieu)
	{
		int[] tabTmp = TriUtilitaire.copierTableau(tab, debut, milieu);

		int indOrig1 	= debut; 	// tabTMP[0]
		int indOrig2 	= milieu+1; // tab[milieu+1]
		int indDest 	= fin; 		// tab[0]

		for(int cpt=indOrig1; cpt <= indDest; cpt++)
		{
			if ( indOrig1 == indOrig2 )
			{
				break;
			}
			else if ( indOrig2 == (indDest+1) ) // tabTmp[indOrig2] < tabTmp[indOrig2]
			{
				tab[cpt] = tab[indOrig1-debut];
				indOrig1++;
			}
			else if ( tab[indOrig1-debut] < tab[indOrig2] )
			{
				tab[cpt] = tab[indOrig1-debut];
				indOrig1++;
			}
			else
			{
				tab[cpt] = tab[indOrig2];
				indOrig2++;
			}
		}
	}

	/**
		* @return Copier les élements d'un tableau dans un nouveau tableau et retourne ce tableau.
		* @param tab : le tableau à copier.
		* @param indiceDebut : l'indice du début.
		* @param indiceFin : l'indice de fin.
	*/
	private static int[] copierTableau(int[] tab, int indiceDebut, int indiceFin)
	{
		int[] tabCopier = new int[(indiceFin+1)-indiceDebut];

		int indTabCopier = 0;
		for(int cpt=indiceDebut; cpt <= indiceFin; cpt++)
		{
			tabCopier[indTabCopier++] = tab[cpt];
		}

		return tabCopier;
	}

	/**
		* @return Retourne l'indice maximum de l'élément maximum dans le tableau.
		* @param tab 		: le tableau où il faut cherche l'indice maximum.
		* @param indDebut 	: l'indice de début.
		* @param indFin 	: l'indice de fin.
	*/
	private static int indMax(int[] tab, int indDebut, int indFin)
	{
		int max=0;
		for (int i=0; i<tab.length; i++)
		{
			if(tab[i]>max)
			{
				max=tab[i];
			}
		}
		return max;
	}

	/**
		* Permute les élements d'indice ind1 et ind 2 du tableau
		* @param tab 	: le tableau où les deux indices sont à permuter.
		* @param ind1 	: l'indice 1.
		* @param ind2 	: l'indice 2.
	*/
	private static void permuter(int []tab, int ind1, int ind2)
	{
		int tmp 	=	tab[ind1];
		tab[ind1]	=	tab[ind2];
		tab[ind2]	= 	tmp;
	}
	
	/**
		* Affichage du tableau
		* @param tab : le tableau à afficher.
	*/
	public static void afficherTableau(int[] tab)
	{
		for(int cpt=0; cpt<tab.length; cpt++)
		{
			System.out.print(tab[cpt] + " ");
		}
		System.out.println();
	}
	
	/**
		* Méthode pour indiquer d'un tableau est trié.
		* @return Retourne vrai si le tableau est trié. False si le tableau n'est pas trié.
		* @param tab : le tableau pour savoir si il est trié.
	*/
	public boolean estTrie(int[] tab)
	{
		for(int cpt=0; cpt<tab.length-1; cpt++)
		{
			if ( tab[cpt] > tab[cpt+1] )
			{
				return false;
			}
		}
		return true;
	}
	
	/**
		Méthode main
		* @param args : les arguments du programme.
	*/
	public static void main(String[] args)
	{
		long tpsDebutProgramme = System.currentTimeMillis();

		long tps1, tps2 	= 0;
		double tpsSeconds 	= 0;
		int taille = 100;
		int[] tabBulle 		= new int[taille];
		int[] tabSelection 	= new int[taille];
		int[] tabInsertion 	= new int[taille];
		int[] tabRapide 	= new int[taille];
		int[] tabFusion 	= new int[taille];
		
		// Remplissage du tableau
		for(int cpt=0; cpt<tabBulle.length; cpt++)
		{
			tabBulle[cpt] = tabSelection[cpt] = tabInsertion[cpt] = tabRapide[cpt] = tabFusion[cpt] = (int) (Math.random () * 100);
		}
		
		// Trie à bulle
		tps1 = System.nanoTime();
		TriUtilitaire.trierBulle(tabBulle, tabBulle.length);
		tps2 = System.nanoTime();
		tpsSeconds = (tps2-tps1) / 1e9;
		System.out.println("Tri à bulle : " + (tps2-tps1) + " nano secs soit " + tpsSeconds + " secs");
		
		tps1 		= 0;
		tps2 		= 0;
		tpsSeconds 	= 0;
		
		// Trie par sélection
		tps1 = System.nanoTime();
		TriUtilitaire.trierSelection(tabSelection);
		tps2 = System.nanoTime();
		tpsSeconds = (tps2-tps1) / 1e9;
		System.out.println("Tri par sélection : " + (tps2-tps1) + " nano secs soit " + tpsSeconds + " secs");
			
		tps1 = 0;
		tps2 = 0;
		tpsSeconds 	= 0;
		
		// Trie par insertion
		tps1 = System.nanoTime();
		TriUtilitaire.trierInsertion(tabInsertion);
		tps2 = System.nanoTime();
		tpsSeconds = (tps2-tps1) / 1e9;
		System.out.println("Tri par insertion : " + (tps2-tps1) + " nano secs soit " + tpsSeconds + " secs");
		
		tps1 = 0;
		tps2 = 0;
		tpsSeconds 	= 0;

		// Trie rapide
		tps1 = System.nanoTime();
		TriUtilitaire.trierRapide(tabRapide, 0, tabRapide.length-1);
		tps2 = System.nanoTime();
		tpsSeconds = (tps2-tps1) / 1e9;
		System.out.println("Tri rapide : " + (tps2-tps1) + " nano secs soit " + tpsSeconds + " secs");

		// Trie fusion (interclassement)
		tps1 = System.nanoTime();
		TriUtilitaire.trierFusion(tabRapide, 0, tabRapide.length-1);
		tps2 = System.nanoTime();
		tpsSeconds = (tps2-tps1) / 1e9;
		System.out.println("Tri fusion : " + (tps2-tps1) + " nano secs soit " + tpsSeconds + " secs");

		long tpsFinProgramme = System.currentTimeMillis();
		float tpsProgramme = ((tpsFinProgramme-tpsDebutProgramme) / 1000F); 
		System.out.println("Temps d'éxécution du programme " + tpsProgramme + " secs");
	}
}
