package ud1_ad;
import java.io.*;

public class ejercicio2 {
    public static void main(String[] args) {
        String archivoEntrada = "nombres.txt";
        String archivoSalida = "nombresFiltrados.txt";

        // Verificar si el archivo de entrada existe, y si no, crearlo con nombres de ejemplo
        File fileEntrada = new File(archivoEntrada);
        if (!fileEntrada.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoEntrada))) {
                writer.write("Juan Carlos Pedro Ana Maria Sofia Miguel Lucia");
                System.out.println("Archivo 'nombres.txt' creado con nombres de ejemplo.");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al crear el archivo de entrada: " + e.getMessage());
            }
        }

        try (
            // Usamos BufferedReader para leer y BufferedWriter para escribir
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))
        ) {
            String linea;

            // Leer cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                String[] palabras = linea.split(" "); // Separar nombres y apellidos por espacios

                // Filtrar y escribir solo nombres de 5 letras
                for (String palabra : palabras) {
                    if (palabra.length() == 5) {
                        escritor.write(palabra);
                        escritor.newLine(); // Escribir cada nombre en una nueva línea
                    }
                }
            }

            System.out.println("Nombres con cinco letras escritos en 'nombresFiltrados.txt'.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar los archivos: " + e.getMessage());
        }
    }
}

