import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		//projekt napisany na studiach
		String[][] tablica = WczytaniePliku2.wczytajPlik2("/home/cengupan/systemy_uczace_sie/gielda");
		System.out
				.println("Program zakłada, ze kolumna ostatnia jest kolumną decyzyjną");
		System.out
		.println("Program zakłada, ze pierwszy wiersz to nazwy kolumn");
		System.out.println("ile wierszy: " + tablica.length);
		System.out.println("ile kolumn: " + tablica[1].length);
		//liczy entropie ostatniej kolumny
		double[] entropiaZmienna = Entropia
				.policzEntropieKazdejKolumny(tablica);
		double entropia=entropiaZmienna[entropiaZmienna.length-1];
		System.out.println("entropia ostatniej kolumny: " + entropia );
		//sprawdza entropie ostatniej kolumny, jezeli zero to kończy
		Entropia.sprawdzOstatniaKolumne(entropiaZmienna);
		// rysuje drzewo, metoda jest rekurencyjna, parametr zero oznacza, ze jest to cala macierz (ze jest to interacja 0)
		Drzewo3.rysujDrzewo(tablica,0);

	}
}