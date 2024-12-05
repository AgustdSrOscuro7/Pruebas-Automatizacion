package com.automatizacion.grupoDos;
import org.testng.TestNG;

public class MainTestRunner {

    /**
     * Método principal para ejecutar las pruebas utilizando TestNG.
     *
     * Este runner permite ejecutar múltiples clases de prueba desde un único punto.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Crear una instancia de TestNG
        TestNG testng = new TestNG();

        // Configurar las clases de prueba a ejecutar
        testng.setTestClasses(new Class[]{
                tests.LoginTest.class,   // Clase para pruebas de login
                tests.PurchaseTest.class // Clase para pruebas de compra
        });

        // Ejecutar las pruebas
        System.out.println("=== Inicio de la ejecución de pruebas ===");
        testng.run();
        System.out.println("=== Todas las pruebas han finalizado ===");
    }
}
