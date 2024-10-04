package ud1_ad;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        try {
            // Verificar si el archivo de entrada existe, y si no, crearlo
            File fileEntrada = new File(archivoEntrada);
            if (!fileEntrada.exists()) {
                FileWriter fw = new FileWriter(archivoEntrada);
                fw.write("Hola Mundo");  // Crear el archivo con contenido predeterminado
                fw.close();
                System.out.println("Archivo 'entrada.txt' creado con contenido predeterminado.");
            }

            // Leer contenido del archivo de entrada
            FileReader fr = new FileReader(archivoEntrada);
            StringBuilder contenido = new StringBuilder();
            int caracter;
            while ((caracter = fr.read()) != -1) {
                contenido.append((char) caracter);
            }
            fr.close();

            // Invertir las palabras
            String resultado = invertirPalabras(contenido.toString());

            // Escribir el resultado en el archivo de salida
            FileWriter fw = new FileWriter(archivoSalida);
            fw.write(resultado);
            fw.close();

            System.out.println("Archivo 'salida.txt' creado con las palabras invertidas.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    public static String invertirPalabras(String linea) {
        String[] palabras = linea.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(new StringBuilder(palabra).reverse().toString()).append(" ");
        }

        return resultado.toString().trim();
    }
}

