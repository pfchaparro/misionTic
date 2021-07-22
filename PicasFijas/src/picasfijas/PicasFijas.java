// Programa Java del juego Picas y Fijas
 
/**
* <h1>Picas y Fijas</h1>
*
* @author  Pablo Chaparro
* @version 1.0
* @since   2021-07-22
*/
package picasfijas;
import java.util.Scanner;


public class PicasFijas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PicasFijas.iniciarJuego();
    }
    
    public static void iniciarJuego() {
        // generacion del codigo, por ejemplo (1, 2, 3, 4)
        Scanner in;
        in = new Scanner(System.in);
        
        String numero = Juego.generateNumber();
        System.out.println("Numero generado aleatorio: " + numero);
        
        Integer intentos = 0, aciertos = 0, coincidencias = 0;
        System.out.println("Que codigo propones?: ");
        String propuesta = in.nextLine();
        
        while (true)
        {
            if ((""+propuesta).length() != 4){
                System.out.println("El numero debe tener 4 digitos");
                System.out.println("Que codigo propones?: ");
                propuesta = in.nextLine();
                continue;
            }

            intentos++;
            String[] picasFijas = Juego.picasFijas(numero, propuesta);
            aciertos = picasFijas[0].length();
            coincidencias = picasFijas[1].length();
            
            System.out.println("Tu propuesta (" + propuesta + ") tiene: " + aciertos + " fijas y " + coincidencias + " picas.");
            
            if (aciertos == 4) {
                break;
            }

            System.out.println("Que codigo propones?: ");
            propuesta = in.nextLine();
        }

        System.out.println("Ganaste, tu numero de intentos fue: " + intentos);
    }
    
}
