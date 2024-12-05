package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * Obtiene los datos de inicio de sesión desde un archivo Excel.
     *
     * @param filePath Ruta del archivo Excel.
     * @return Un arreglo de strings con las credenciales [usuario, contraseña].
     */
    public static String[] getLoginData(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Acceder a la hoja "Login" y leer la primera fila con datos
            Sheet sheet = workbook.getSheet("Login");
            Row row = sheet.getRow(1); // Primera fila de datos (índice 1)

            // Devolver los datos de usuario y contraseña
            return new String[]{
                    row.getCell(0).getStringCellValue(), // Usuario
                    row.getCell(1).getStringCellValue()  // Contraseña
            };

        } catch (IOException e) {
            System.err.println("Error al leer el archivo Excel: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Datos faltantes en la hoja 'Login': " + e.getMessage());
        }

        return null; // Retorna null si ocurre algún error
    }

    /**
     * Obtiene una lista de productos desde un archivo Excel.
     *
     * @param filePath Ruta del archivo Excel.
     * @return Una lista de arreglos de strings, donde cada arreglo contiene [nombreProducto, cantidad].
     */
    public static List<String[]> getProducts(String filePath) {
        List<String[]> products = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Acceder a la hoja "Productos"
            Sheet sheet = workbook.getSheet("Productos");
            int rows = sheet.getPhysicalNumberOfRows(); // Número de filas con datos

            // Iterar desde la segunda fila para omitir los encabezados
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);

                // Leer el nombre del producto y la cantidad
                String productName = row.getCell(0).getStringCellValue(); // Nombre del producto
                int quantity = (int) row.getCell(1).getNumericCellValue(); // Cantidad como entero

                // Agregar los datos a la lista
                products.add(new String[]{productName, String.valueOf(quantity)});
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo Excel: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Datos faltantes en la hoja 'Productos': " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error al leer un tipo de celda inesperado: " + e.getMessage());
        }

        return products; // Retorna la lista, vacía si ocurre algún error
    }
}
