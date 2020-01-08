import java.util.ArrayList;
import java.util.List;

class Drzewo3 {

	public static String rysujZnaczki(int ktoryPoziomDrzewa) {
		String znaczek = "";
		for (int i = 0; i < ktoryPoziomDrzewa; i++) {
			znaczek = znaczek + "=";
		}

		return znaczek;
	}

	public static void rysujDrzewo(String[][] tablica, int poziomDrzewa) {
		poziomDrzewa++;
		PoliczZysk tablicaCala = new PoliczZysk(tablica);
		if (tablicaCala.zyskPrawdaFalsz) {
			for (int i = 0; i < tablicaCala.getTablicaUnikalnych().length; i++) {
				System.out.println();
				System.out.print(tablicaCala.nazwy[tablicaCala.index] + " = "
						+ tablicaCala.getTablicaUnikalnych()[i]);

				PoliczZysk tablicaPomocnicza = new PoliczZysk(
						tablicaCala.UtworzTabliceWedlugWartosci(i));

				if (tablicaPomocnicza.zyskPrawdaFalsz) {
					for (int j = 0; j < tablicaPomocnicza
							.getTablicaUnikalnych().length; j++) {

						System.out.println();
						System.out
								.print("|   "
										+ tablicaPomocnicza.nazwy[tablicaPomocnicza.index]
										+ " "
										+ rysujZnaczki(poziomDrzewa)
										+ " "
										+ tablicaPomocnicza
												.getTablicaUnikalnych()[j]);

						rysujDrzewo(
								tablicaPomocnicza
										.UtworzTabliceWedlugWartosci(j),
								poziomDrzewa);
					}

				} else {

					System.out.print(" : "
							+ tablicaPomocnicza.elementDecyzycjny);
				}
			}
		} else {

			System.out.print(" : " + tablicaCala.elementDecyzycjny);
		}

	}

}
