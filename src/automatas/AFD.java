/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author kari
 */
public class AFD {
    
    
    
    
    // automata finito deterministico
    public static boolean getResult(String[][] matriz,String word, String EstadoFinal)
    {
        boolean salida=false;
        int EstadoTransitorio=1;
        for (int i = 1; i <= word.length(); i++)
        {
            int j=i-1;
            for (int x = 1; x < matriz.length; x++)
            {
                if(word.substring(j,i).equals(matriz[0][x]))
                {
                    EstadoTransitorio=Integer.parseInt(matriz[EstadoTransitorio][x]); 
                    break;
                }
            }
        }        
        if(EstadoFinal.equals(matriz[EstadoTransitorio][0]))
        {
            salida = true;
        }
         return salida;
    }
    
}
