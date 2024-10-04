package ud1_ad;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4b {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el DNI y el peso actual
        System.out.println("Introduzca el DNI (con letra) del personaje para control de peso: ");
        String dniBuscado = scanner.nextLine().trim();  // Usar trim() para eliminar posibles espacios extra

        System.out.println("Introduzca su peso actual: ");
        int pesoUltimoMes;

        // Intentar convertir la entrada del peso a entero
        try {
            pesoUltimoMes = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El peso debe ser un número entero.");
            return;
        }

        // Nombre del archivo
        String archivo = "archivoEntrada/Marvel.dat";
        File file = new File(archivo);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("Error: El archivo no existe o la ruta es incorrecta.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            boolean personajeEncontrado = false;

            // Leer los datos hasta el final del archivo
            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt(); // Leer el ID
                String dni = readFixedLengthString(raf, 9); // Leer DNI
                String nombre = readFixedLengthString(raf, 10); // Leer Nombre
                String identidad = readFixedLengthString(raf, 20); // Leer Identidad secreta
                String tipo = readFixedLengthString(raf, 10); // Leer Tipo
                int pesoAnterior = raf.readInt(); // Leer Peso
                int altura = raf.readInt(); // Leer Altura

                // Comprobar si el DNI coincide (trim para eliminar espacios sobrantes)
                if (dni.trim().equalsIgnoreCase(dniBuscado)) {  // Comparar ignorando mayúsculas/minúsculas
                    personajeEncontrado = true;

                    // Calcular la variación de peso
                    int diferenciaPeso = pesoUltimoMes - pesoAnterior;

                    // Actualizar el peso en el archivo
                    raf.seek(raf.getFilePointer() - 8); // Regresar al inicio del peso (antes de los 4 bytes de altura)
                    raf.writeInt(pesoUltimoMes); // Escribir el nuevo peso

                    // Mostrar resultados según la variación de peso
                    if (diferenciaPeso == 0) {
                        System.out.printf("%s se mantiene en su peso anterior.%n", nombre);
                    } else if (diferenciaPeso > 0) {
                        System.out.printf("%s ha engordado %d kg.%n", nombre, diferenciaPeso);
                    } else {
                        System.out.printf("%s ha adelgazado %d kg.%n", nombre, -diferenciaPeso);
                    }
                    break;
                }
            }
            if (!personajeEncontrado) {
                System.out.println("Error: No se encontró un personaje con el DNI proporcionado.");
            }
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Método para leer una cadena de longitud fija
    private static String readFixedLengthString(RandomAccessFile raf, int length) throws IOException {
        byte[] buffer = new byte[length];
        raf.readFully(buffer); // Leer una cantidad fija de bytes
        return new String(buffer).trim(); // Convertir a String y eliminar espacios en blanco
    }
}
