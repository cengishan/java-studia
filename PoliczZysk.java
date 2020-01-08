import java.util.ArrayList;
import java.util.List;

//tworzy klase wszystkich potrzebnych elementów do podziału tablicy na mniejsze częsci, potrzebna do rekurencji

public class PoliczZysk {

	double[] tablicaInfo;
	double entropia;
	String[][] tablicaZnazwami;
	int index;
	String[] tablicaUnikalnych;
	int[] tablicaLiczebnosci;
	boolean zyskPrawdaFalsz;
	String elementDecyzycjny;
	String[] nazwy;

	
	PoliczZysk(String[][] tablica2) {
		
		String[][] tablica = new String[tablica2.length - 1][tablica2[0].length];
		for (int i = 0; i < tablica2.length - 1; i++) {
			for (int j = 0; j < tablica2[0].length; j++) {
				tablica[i][j] = tablica2[i + 1][j];

			}
		}
		this.nazwy = tablica2[0];

		this.tablicaZnazwami = tablica2;
		double entropia = 0;
		List<String> wartosciWkolumnieDecyzyjnej = new ArrayList<String>();

		for (int i = 0; i < tablica.length; ++i) {
			if (!wartosciWkolumnieDecyzyjnej
					.contains(tablica[i][tablica[0].length - 1])) {
				wartosciWkolumnieDecyzyjnej
						.add(tablica[i][tablica[0].length - 1]);
			}
		}
		double[] tablicaInfo = new double[tablica[0].length - 1];

		// dla kazdej kolumny musi porownac z kolumna ostatnia(decyzyjna)

		for (int ktoraKolumna = 0; ktoraKolumna < tablica[0].length - 1; ktoraKolumna++) {
			List<String> listaWartosci = new ArrayList<String>();

			for (int i = 0; i < tablica.length; ++i) {
				if (!listaWartosci.contains(tablica[i][ktoraKolumna])) {
					listaWartosci.add(tablica[i][ktoraKolumna]);
				}
			}
			// lista zliaczająca poszczególne wystąpienia
			List<Double> listaLiczebnosciWartosci = new ArrayList<Double>();

			for (int i = 0; i < listaWartosci.size(); i++) {
				double licznik = 0;

				for (int j = 0; j < tablica.length; j++) {
					if (listaWartosci.get(i).equals(tablica[j][ktoraKolumna])) {
						licznik++;
					}
				}
				listaLiczebnosciWartosci.add(licznik);
			}

			double p1 = 0;
			double p2 = 0;
			double licznik;
			double info = 0;
			for (int i = 0; i < listaWartosci.size(); i++) {

				licznik = 0;

				for (int j = 0; j < tablica.length; j++) {
					if (listaWartosci.get(i).equals(tablica[j][ktoraKolumna])
							&& tablica[j][tablica[1].length - 1]
									.equals(wartosciWkolumnieDecyzyjnej.get(0)))
						licznik++;
				}
				double dlugoscTablicy = tablica.length;
				p1 = listaLiczebnosciWartosci.get(i) / dlugoscTablicy;
				p2 = licznik / listaLiczebnosciWartosci.get(i);
				double info2 = 0;
				info2 = p1
						* Entropia.policzEntropieDlaPrawdopodobieństw(p2,
								(1 - p2));
				info = info + info2;

			}
			entropia = Entropia.policzEntropieOstatniejKolumny(tablica);
			tablicaInfo[ktoraKolumna] = entropia - info;

		}
		this.tablicaInfo = tablicaInfo;
		this.entropia = entropia;
		this.index = sprawdzNajwiekszaWartosc(tablicaInfo);
		this.tablicaUnikalnych = wskazWartosciUnikalne(wybierzKolumneZtablicy(
				tablica, index));
		this.tablicaLiczebnosci = policzWystapienia(wybierzKolumneZtablicy(
				tablica, index));
		this.zyskPrawdaFalsz = czyZyskRownyZero(tablicaInfo);
		this.elementDecyzycjny = tablica[0][tablica[0].length - 1];
	}

	public double[] getTablicaZysk() {
		return tablicaInfo;
	}

	public double getEntropia() {
		return entropia;
	}

	public String[] getTablicaUnikalnych() {
		return tablicaUnikalnych;
	}

	public int[] getTablicaLiczebnosci() {
		return tablicaLiczebnosci;

	}

	public int getIndex() {
		return index;

	}

	public boolean getCzyZyskRownyZero() {
		return zyskPrawdaFalsz;

	}
	
	
	public String[][] UtworzTabliceWedlugWartosci(int numerUnikalnej) {

		String[][] tablica2 = this.tablicaZnazwami;
		int index = this.index;
		String[] tablicaUnikalnych = this.tablicaUnikalnych;
		String wartoscUnikalna = tablicaUnikalnych[numerUnikalnej];
		int[] tablicaLiczebnosci = this.tablicaLiczebnosci;
		int liczebnosc = tablicaLiczebnosci[numerUnikalnej];
		String[][] tablica = new String[liczebnosc+1][tablica2[0].length - 1];
		int licznikKolumn1 = 0;
		for (int i=0;i<this.nazwy.length;i++){
			if (index != i){
			tablica[0][licznikKolumn1]=nazwy[i];
			licznikKolumn1++;
			}
		}

		int licznikWierszy = 0;
		for (int i = 1; i < tablica2.length; i++) {
			int licznikKolumn = 0;
			if (tablica2[i][index].equals(wartoscUnikalna)) {
				for (int j = 0; j < tablica2[0].length; j++) {
					if (index != j) {
						tablica[licznikWierszy+1][licznikKolumn] = tablica2[i][j];
						licznikKolumn++;
					}

				}
				licznikWierszy++;
			}

		}

		return tablica;
	}

	static boolean czyZyskRownyZero(double[] tablica) {
		boolean prawdaFalsz = false;
		for (int i = 0; i < tablica.length; i++) {
			if (tablica[i] > 0) {
				prawdaFalsz = true;
			}
		}
		return prawdaFalsz;

	}

	public static int[] policzWystapienia(String[] tablica) {

		List<String> wartosciUnikalne = new ArrayList<String>();

		for (int i = 0; i < tablica.length; ++i) {
			if (!wartosciUnikalne.contains(tablica[i])) {
				wartosciUnikalne.add(tablica[i]);
			}
		}

		List<Integer> listaLiczebnosciWartosci = new ArrayList<Integer>();

		for (int i = 0; i < wartosciUnikalne.size(); i++) {
			int licznik = 0;

			for (int j = 0; j < tablica.length; j++) {
				if (wartosciUnikalne.get(i).equals(tablica[j])) {
					licznik++;
				}
			}
			listaLiczebnosciWartosci.add(licznik);
		}

		int tablica2[] = new int[listaLiczebnosciWartosci.size()];
		for (int i = 0; i < listaLiczebnosciWartosci.size(); i++) {
			tablica2[i] = listaLiczebnosciWartosci.get(i);
		}

		// Double[] tablica2 = listaLiczebnosciWartosci.toArray(new
		// Double[listaLiczebnosciWartosci.size()]);

		return tablica2;
	}

	public static int sprawdzNajwiekszaWartosc(double[] tablica) {
		int index = 0;
		double najwiekszaWartosc = tablica[0];
		for (int i = 0; i < tablica.length; i++) {
			if (najwiekszaWartosc < tablica[i]) {
				index = i;
			}
		}

		return index;
	}

	public static String[] wskazWartosciUnikalne(String[] tablica) {
		List<String> wartosciUnikalne = new ArrayList<String>();

		for (int i = 0; i < tablica.length; ++i) {
			if (!wartosciUnikalne.contains(tablica[i])) {
				wartosciUnikalne.add(tablica[i]);
			}
		}

		String[] tablica2 = wartosciUnikalne
				.toArray(new String[wartosciUnikalne.size()]);
		return tablica2;
	}

	public static String[] wybierzKolumneZtablicy(String[][] tablica, int index) {

		String tablica2[] = new String[tablica.length];

		for (int i = 0; i < tablica.length; ++i) {
			tablica2[i] = tablica[i][index];
		}
		return tablica2;
	}

	
}