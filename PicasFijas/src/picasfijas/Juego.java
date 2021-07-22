/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picasfijas;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author pablo
 */
public class Juego {
    
    /**
        * Este método se encarga de generar un número aleatorio de 4 digitos, 
        * donde no se repitan los digitos
        * @author Pablo Chaparro
        * @return numero de 4 digitos
    **/
    public static String generateNumber()
    {
        int iterator = 0;
        Random rnd = new Random();
        ArrayList<Integer> numberGenerate = new ArrayList<>();
        while(iterator < 4){
            int num = rnd.nextInt(10);
            if (!numberGenerate.contains(num))
            {
                numberGenerate.add(num);
                iterator++;
            }
        }
        
        String result = numberGenerate.toString();

        result = "" + result.charAt(1) + result.charAt(4) + result.charAt(7) + result.charAt(10);
        return result;
    }
    
    /**
        * Dado un código y una propuesta, determinar mediante el juego de picas y fijas, 
        *Si hay coincidencias o aciertos.
        * - Un acierto es cuando un digito de la propuesta se encuentra en la misma posición.
        * - Una coincidencia es cuando un digito se encuentra en el código, pero NO en la misma posición
        * @param numero numero generado aleatoriamente.
        * @param propuesta propuesta que da el usuario.
        * @author Pablo Chaparro
        * @return fijas,picas
    **/
    public static String[] picasFijas(String numero, String propuesta){
        
        String fijas = "";
        String picas = "";
        
        char[] cod = numero.toCharArray();
        char[] pro = propuesta.toCharArray();
        for (int i = 0; i < cod.length; i++)
        {
            if (pro[i] == cod[i])  
            {
                fijas += pro[i];
            }
            else if (numero.contains(String.valueOf(pro[i])) && !fijas.contains(String.valueOf(pro[i])))
            {
                picas += pro[i];
            }
        }
            
        String[] result = {fijas,picas};
        return result;
    }
    

    
    
}
