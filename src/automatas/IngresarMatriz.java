package automatas;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class IngresarMatriz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        llenarMatrix();
	}
	
	public static String [][] llenarMatrix()
	{
		
		Scanner scanner=new Scanner(System.in);		
		System.out.println("Ingrese elementos de la matriz separados por /:");
        String elementos=scanner.next();
    	System.out.println("Ingrese valores de la matriz separados por / :");
        String valores=scanner.next();
        String[] elementosArray = elementos.split("/");
        String[] valoresArray = valores.split("/");
        
        if(elementosArray.length>0 && valoresArray.length>0)
        {
        	String[][] matrizA=new String[(elementosArray.length)+1][(valoresArray.length)+1];
        	
        	for (int i = 0; i < (elementosArray.length)+1; i++) 
        	{
        		for (int j = 0; j < (valoresArray.length)+1; j++) 
        		{
    			
        			if(i==0 && j==0)
        			{
        				matrizA[i][j]="";
        			}
        			if(i==0 && j>0)
        			{
        				matrizA[i][j]=valoresArray[j-1];
        			}
        			if(i>0 && j==0)
        			{
        				matrizA[i][j]=elementosArray[i-1];
        			}
        			if(i!=0 && j!=0)
        			{
        		    	System.out.println("Ingrese valores para :"+matrizA[i][0]);
        				matrizA[i][j]=scanner.next();
        			}	
        			
        		}
        	}
        	System.out.println("matriz de transcicios ingresada:");
        	System.out.println(Arrays.deepToString(matrizA));
        	
        	return matrizA;
        }
        else
        {
        	System.out.println("LA CANTIDAD DE ELEMENTOS INGRESADO NO ES EQUIVALENTE A LOS VALORES. FIN");
        	
        	
        	System.exit(0);
        }
		return null;	
		
		
	}

}
