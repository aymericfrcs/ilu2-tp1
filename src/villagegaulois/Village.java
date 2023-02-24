package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(nbEtalsMax);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder str = new StringBuilder();
		int numEtalLibre = marche.trouverEtalLibre();
		
		str.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + produit + "\n");
		
	}
	
	private static class Marche {
		private Etal[] etals;
		
		
		public Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			
		}
		
		public void utliserEtal(int indiceEtal, Gaulois vendeur,
					String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			String str = vendeur.getNom() + " s'installe à l'étal n°"
					+ indiceEtal + " et vend " + nbProduit + produit;
			System.out.println(str);
		}
		
		public int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			Etal[] etalsTrouve = new Etal[etals.length];
			int y = 0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsTrouve[y] = etals[i];
					y++;
				}
			}
			return etalsTrouve;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			int nbEtalVide = 0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				}
				else {
					nbEtalVide++;
				}
			}
			String str = "Il reste " + nbEtalVide 
						+ " étals non utilisés dans le marché\n";
			return str;
		}
	}
}