package ud1_ad;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio4a {

    public static void main(String[] args) {
        // Datos de los personajes proporcionados
        int[] ids = {1, 2, 3, 4, 5, 6, 7}; // IDs de los personajes
        String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"}; // DNI (9 caracteres)
        String[] nombres = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"}; // Nombres (10 caracteres)
        String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"}; // Identidad secreta (20 caracteres)
        String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"}; // Tipo: héroe o villano (10 caracteres)
        int[] pesos = {76, 84, 66, 136, 78, 102, 70}; // Peso en kg
        int[] alturas = {178, 183, 156, 152, 177, 182, 188}; // Altura en cm

        // Nombre del archivo aleatorio donde se guardarán los datos
        String archivo = "archivoEntrada/Marvel.dat"; // Guardado en la carpeta 'archivoEntrada'

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            // Escribir los datos en el archivo
            for (int i = 0; i < ids.length; i++) {
                raf.writeInt(ids[i]); // Escribir el ID del personaje (entero)

                // Usar formato para asegurarse de que las longitudes sean correctas (rellenar con espacios si es necesario)
                raf.writeBytes(String.format("%-9s", dnis[i])); // DNI (9 caracteres)
                raf.writeBytes(String.format("%-10s", nombres[i])); // Nombre (10 caracteres)
                raf.writeBytes(String.format("%-20s", identidades[i])); // Identidad secreta (20 caracteres)
                raf.writeBytes(String.format("%-10s", tipos[i])); // Tipo (10 caracteres)
                
                raf.writeInt(pesos[i]); // Escribir el peso (entero)
                raf.writeInt(alturas[i]); // Escribir la altura (entero)
            }
            // Informar que la carga de datos fue exitosa
            System.out.println("La carga de los personajes ha terminado correctamente.");
        } catch (IOException e) {
            // Informar si ocurre un error durante la escritura del archivo
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}

