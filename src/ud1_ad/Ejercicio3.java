package ud1_ad;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ejercicio3 {
    public static void main(String[] args) {
        String archivoPDF = "archivoEntrada/archivo.pdf"; // Ruta del archivo PDF

        try (InputStream input = new FileInputStream(archivoPDF)) {
            // Leer los primeros 4 bytes del archivo
            byte[] cabecera = new byte[4];
            int bytesLeidos = input.read(cabecera);

            // Verificar si se leyeron al menos 4 bytes
            if (bytesLeidos == 4) {
                // Secuencia de cabecera estándar de un archivo PDF: {37, 80, 68, 70} que corresponde a %PDF
                byte[] cabeceraPDF = {37, 80, 68, 70};

                // Comparar los bytes leídos con la cabecera esperada
                boolean esPDF = true;
                for (int i = 0; i < 4; i++) {
                    if (cabecera[i] != cabeceraPDF[i]) {
                        esPDF = false;
                        break;
                    }
                }

                if (esPDF) {
                    System.out.println("El archivo es un PDF válido.");
                } else {
                    System.out.println("El archivo no es un PDF válido.");
                }
            } else {
                System.out.println("No se pudieron leer los primeros 4 bytes del archivo.");
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}

