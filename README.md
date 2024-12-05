# Pruebas-Automatizacion - Taller de Automatización con Selenium WebDriver

## Descripción del Proyecto
Este proyecto automatiza los flujos de **inicio de sesión** y **compra de productos** en el sitio web **OpenCart** utilizando **Selenium WebDriver** con **Java**. Los datos de usuario y productos se leen desde un archivo **Excel**. Se utilizan técnicas como **esperas implícitas y explícitas** y **aserciones** para garantizar la correcta ejecución de los flujos.

## Particularidades
1. **Cuenta proporcionada no válida**: La cuenta de prueba proporcionada por el profesor (test@example.com y password123) no funcionó, por lo que fue necesario crear una nueva cuenta para completar los casos de prueba.

## Objetivos del Taller
1. Automatizar el flujo de **inicio de sesión** y **compra de productos** usando **Selenium WebDriver** con **Java**.
2. Aplicar:
   - **Esperas implícitas** y **explícitas**.
   - **Aserciones** para validar los pasos clave.
   - **Lectura de datos desde un archivo Excel**.
3. Generar reportes de ejecución tanto en consola como en un archivo Excel.

## Actividades Implementadas
### 1. **Configuración del Proyecto**
- El proyecto está estructurado como un **Java Maven** utilizando el patrón **POM (Page Object Model)**:
    - **src/main/java**: Contiene las clases para manejar las páginas.
    - **src/test/java**: Contiene las clases de prueba. (Aunque anexamos una en el src/main/java, ya que, al enviar el archivo tenia problemas con el IntelliJ IDEA
    - **src/test/resources**: Contiene el archivo `testdata.xlsx` para los datos de prueba.
- Dependencias utilizadas:
    - **Selenium WebDriver**.
    - **Apache POI** para la manipulación de archivos Excel.
    - **TestNG** para organizar los casos de prueba.

### 2. **Automatización del Flujo de Login**
- Las credenciales de usuario se leen desde el archivo `testdata.xlsx`.
- Flujo implementado:
    - Acceder a "My Account" > "Login".
    - Ingresar las credenciales de usuario y contraseña.
    - Verificar que el login se realice correctamente.
- Técnicas aplicadas:
    - **Esperas implícitas** para esperar la carga de los elementos.
    - **Aserciones** para validar la redirección a la página de cuenta.

### 3. **Automatización del Flujo de Compra**
- Los productos y cantidades se leen desde el archivo `testdata.xlsx`.
- Flujo implementado:
    1. Buscar y agregar productos al carrito:
        - Validar que los productos estén disponibles.
        - Establecer la cantidad según los datos del archivo Excel.
    2. Completar el proceso de compra:
        - Ir al carrito y proceder con el checkout.
        - Completar la información de envío y pago.
        - Confirmar la orden de compra.
- Técnicas aplicadas:
    - **Esperas explícitas** para los elementos dinámicos.
    - **Aserciones** para confirmar que el producto se agregó al carrito y que la compra se completó.

### 4. **Generación de Reportes**
- **Reporte en consola**:
    - Muestra el resultado de la ejecución con los productos procesados correctamente y los productos no encontrados.
- **Reporte en Excel**:
    - Se genera un archivo `output_report.xlsx` que guarda los resultados de la prueba, implementamos el codigo pero no obtuvimos el reporte final

## Resultados de las Pruebas
### Login
- **Login exitoso**:
    - Se realizó correctamente con las credenciales válidas.
    - La redirección a la página de cuenta fue verificada.


### Compra
- Productos procesados:
    - **MacBook**: Cantidad: 1.
    - **iPhone**: Cantidad: 2.
- El flujo de compra se completó sin errores.

## Estructura del Proyecto
- **Clases POM**:
    - `LoginPage`: Manejo de la página de login.
    - `ProductPage`: Selección de productos y cantidad.
    - `CheckoutPage`: Flujo final de la compra.
    - `CheckoutPage`:  Elementos y métodos para manejar la página principal.
- **Clases de Prueba**:
    - `LoginTest`: Casos de prueba para el login.
    - `PurchaseTest`: Casos de prueba para el proceso de compra.
- **Utilidades**:
    - `ExcelUtils`: Funcionalidades para leer y escribir datos en archivos Excel.

## Instrucciones para Ejecutar
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/AgustdSrOscuro7/Pruebas-Automatizacion.git
    ```
2. Importar el proyecto en **Eclipse** o **IntelliJ IDEA**.
3. Ejecutar las clases de prueba:
    - `LoginTest.java`
    - `PurchaseTest.java`
    - **Nota**: Asegurarse de tener **Java** y **Maven** instalados, a su vez, de cambiar la direccion de donde se encuentra el google driver en su maquina.
4. Ajustar el directorio del google driver, ya que, para poder realizar los test debimos poner exactamente la direccion de donde se encontraba el archivo .exe, entonces si no se cambia dicho path en el LoginTest y el PurchaseTest pues puede que salga un error, pero es debido a ese aspecto


## Autor
- Trabajo realizado por **Daniel Felipe Garcia Acevedo**, **Valeria Rudas Ruiz**, **Juan David Serna Quilindo**, **David Santigo Davila Ruiz** y **Daniel Torres Valencia**.

## Evidencias 
- Por si no se logra encontrar el directorio del google driver ponemos nuestras evidencias de los tests
  ![image](https://github.com/user-attachments/assets/b8daa495-42af-4300-a289-bacb010dea70)
  ![image](https://github.com/user-attachments/assets/483c7393-584c-4832-a820-21497359c092)
  https://github.com/user-attachments/assets/b7e4b8d7-f741-423b-aed5-47c3b90c14a8



https://github.com/user-attachments/assets/27da0e5e-ea8a-49a9-8339-df7428fd3afa


https://github.com/user-attachments/assets/ec5881c1-9f91-4532-9bd1-a9b5a8c3fa9a




