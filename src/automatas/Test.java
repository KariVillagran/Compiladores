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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Thompson th = new Thompson("A*");
        String [][] a = {{"","a","b"},{"1","1,2","1"},{"2","1","phi"}};
        String [][] b = {{"","a","b"},{"2","1","phi"}};
        
        //String [][] result = th.union(a, b);
        //String [][] x = th.closing(a);
        
        AFND af = new AFND(a);       
        
        af.setListStates(af.getStates());
        af.setDiccionario(af.getDictionary());
        String [][] result = af.execMethod(af.getTransitionTable());
        af.getAFDphi(result);
        af.finishStates();
        result = af.getTransitionTable();
        
        for (int i = 0; i < result.length; i++)
        {
            System.out.println("----------------------");
            for (int j = 0; j < result[0].length; j++)
            {   
                System.out.println("Indice i:" + i + " j:" + j + " valor: " + result[i][j]);
            }
        }
        
        System.out.println("----------------------");
        System.out.println("Contenido de estados");
        for (int j = 0; j < af.getListStates().size(); j++)
        {   
            System.out.println("valor: " + af.getListStates().get(j));
        }
        System.out.println("----------------------");
        System.out.println("Contenido de diccionario");
        for (int j = 0; j < af.getDiccionario().size(); j++)
        {   
            System.out.println("valor: " + af.getDiccionario().get(j));
        }
        
        /*if(getResult(result, "bbbb", "2,3"))
        {
            System.out.println("La cadena pertenece al lengaueje");
        }
        else
        {
            System.out.println("La cadena NO pertenece al lengaueje");
        }*/
        
        
        /*Thompson th = new Thompson("A*");
        String [][] a = {{"","a","b"},{"q1","q1","phi"}};
        String [][] b = {{"","a","b"},{"q2","q1","phi"}};
        //String [][] result = th.union(a, b);
        String [][] result = th.closing(a);
        
        for (int i = 0; i < result.length; i++)
        {
            System.out.println("----------------------");
            for (int j = 0; j < result[0].length; j++)
            {   
                System.out.println("Indice i:" + i + " j:" + j + " valor: " + result[i][j]);
            }
        }*/
        
    }
    
}
