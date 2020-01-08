import java.util.ArrayList;
import java.util.List;



public class Entropia {
    
        public static double policzEntropieDlaPrawdopodobieństw(double x1, double x2){
            double entropia;
            if(x1==0||x2==0){
            	entropia = 0;
            }
            else{
            	 entropia= -(x1 * Math.log(x1)/Math.log(2) + x2 * Math.log(x2)/Math.log(2));
            }
           

            return entropia;
        }
        

    	public static double[] policzEntropieKazdejKolumny(String[][] tablica2){
    		
    		
    		
    		String[][] tablica=new String[tablica2.length-1][tablica2[0].length];
    	//	System.out.println(tablica.length);
    	//	System.out.println(tablica[0].length);
    		
    		for(int i=0; i<tablica2.length-1;i++){
    			for(int j=0; j<tablica2[0].length;j++){
    				tablica[i][j]=tablica2[i+1][j];
    				
    			}
    		}
    		
    		String[] nazwy = tablica2[0];
    //System.out.println("nazwy kolumn to:");		
    		/*for(int i=0;i<nazwy.length;i++){
    			System.out.print(nazwy[i]+" ");
    		}*/

    		//System.out.println("");
    		
    		double[] wektorEntropi=new double[tablica[0].length];
    		
    	
    		//glowna petla dla kazdej kolumny
    		//for(int a=0 ; a< wektorEntropi.length;a++){
    			for(int a=0 ; a< wektorEntropi.length;a++){
    			
    		//tworzy tablice wartosci unikalnych
    		List<String> lista = new ArrayList<String>();


    		for (int i = 0; i < tablica.length; ++i)
    	    {
    	        if(!lista.contains(tablica[i][a])){
    	           lista.add(tablica[i][a]);
    	        }
    	    }
    		

    		//tworzy tablice liczebsci
    	List<Double> listaP = new ArrayList<Double>();
    	
    		for(int i=0;i<lista.size();i++){		
    			double licznik=0;
    			
    					for(int j=0;j<tablica.length;j++){
    					if(lista.get(i).equals(tablica[j][a])){
    						licznik++;
    					}		
    				}
    					
    					listaP.add(licznik);
    //System.out.println("kolumna numer: "+ a + " wartosc: " + lista.get(i) +" wystapila: "+ licznik +" razy. "+ " prawdopodobienstwo: "+ licznik/tablica.length+ " listaP: "+ listaP.get(i)/tablica.length);
    			}
    		
    		
    		//liczy entropie z podanych liczebnosci, na zasadzie prawdopodobienstwa wystapienia
    		double entropia=0;
    		for(int i=0;i<listaP.size();i++){
                        entropia = entropia +
    					((listaP.get(i)/tablica.length) *  (Math.log(listaP.get(i)/
    						tablica.length)/Math.log(2)));

    		}
    		entropia = -entropia;
    		//System.out.println("entropia "+ (a+1)+ " kolumny to: " + entropia);
    		wektorEntropi[a]=entropia;
    		}
    		

    		return wektorEntropi;
    		
    	}

         
        
   

	public static double policzEntropieOstatniejKolumny(String[][] tablica){
		
		double[] wektorEntropi=new double[tablica[0].length];
		

			
		//tworzy tablice wartosci unikalnych
		List<String> lista = new ArrayList<String>();


		for (int i = 0; i < tablica.length; ++i)
	    {
	        if(!lista.contains(tablica[i][tablica[0].length-1])){
	           lista.add(tablica[i][tablica[0].length-1]);
	        }
	    }
		

		//tworzy tablice liczebsci
	List<Double> listaP = new ArrayList<Double>();
	
		for(int i=0;i<lista.size();i++){		
			double licznik=0;
			
					for(int j=0;j<tablica.length;j++){
					if(lista.get(i).equals(tablica[j][tablica[0].length-1])){
						licznik++;
					}		
				}
					
					listaP.add(licznik);
//System.out.println("kolumna numer: "+ a + " wartosc: " + lista.get(i) +" wystapila: "+ licznik +" razy. "+ " prawdopodobienstwo: "+ licznik/tablica.length+ " listaP: "+ listaP.get(i)/tablica.length);
			}
		
		
		//liczy entropie z podanych liczebnosci, na zasadzie prawdopodobienstwa wystapienia
		double entropia=0;
		for(int i=0;i<listaP.size();i++){
                    entropia = entropia +
					((listaP.get(i)/tablica.length) *  (Math.log(listaP.get(i)/
						tablica.length)/Math.log(2)));

		}
		entropia = -entropia;
		//System.out.println("entropia "+ (a+1)+ " kolumny to: " + entropia);
		return entropia;
		
	}

    static void sprawdzOstatniaKolumne(double[] entropiaZmienna) {
      if(entropiaZmienna[entropiaZmienna.length-1]==0){
      System.out.println("Wszystkie wartosci decyzyjne sa takie same, koniec programu");
      System.exit(0);
      }
       
                
    }
	
	
		


}