import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//klasa wczytujaca plik
public class WczytaniePliku2 {

	public static String[][] wczytajPlik2(String sciezka) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(sciezka));
		String line = br.readLine();
		List<String> list = new ArrayList<String>();
		List<String[]> listOfLists = new ArrayList<String[]>();
		String[] token2;
		String token1 = "";
		while (line != null) {
			token1 = line;
			list.add(token1);
			token2 = token1.split(",");
			listOfLists.add(token2);
			line = br.readLine();
		}

		br.close();

		String[][] tempsArray2 = listOfLists.toArray(new String[0][0]);

		return tempsArray2;
	}

	public static void wyswietlTabliceString(String[][] tablica) {
		for (int i = 0; i < tablica.length; i++) {
			for (int j = 0; j < tablica[i].length; j++) {

				System.out.print(tablica[i][j] + " ");

			}
			System.out.println();
		}
	}

	public static int[][] zmienNaInt(String[][] tablica1) {

		int[][] tablica2 = new int[tablica1.length][tablica1[1].length];
		for (int i = 0; i < tablica1.length; i++) {

			for (int j = 1; j < tablica1[1].length; j++)
				tablica2[i][j] = Integer.parseInt(tablica1[i][j]);
		}
		return tablica2;
	}

	public static int[][] zmienNaInt2(String[][] tablica1) {

		int[][] tablica2 = new int[tablica1.length][tablica1[1].length];
		for (int i = 0; i < tablica1.length; i++) {
			for (int j = 1; j < tablica1[1].length; j++)
				tablica2[i][j] = Integer.parseInt(tablica1[i][j]);
		}
		return tablica2;
	}
	
	public static String[] pobierzNazwy(String[][] tablica1) {

		String[] tablica2 = tablica1[0];
		return tablica2;
	}
	
	

}