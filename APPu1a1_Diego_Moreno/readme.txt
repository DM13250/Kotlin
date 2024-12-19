Nombre de la aplicación: APPu1a1_Diego_Moreno
Función principal: Calcular el mínimo común múltiplo (MCM) de dos números enteros.

Funcionalidades
La aplicación permite ingresar dos números enteros a través de dos campos de entrada (inputs).
Está diseñado para evitar entradas inválidas, tales como caracteres no numéricos, letras, campos vacíos, números negativos o decimales. Si se introduce un valor incorrecto, se mostrará un mensaje de error en el campo correspondiente.
El botón "Calcular" se mantendrá deshabilitado hasta que ambos campos sean completados correctamente con números enteros.
Pantallas
MainActivity : Es la pantalla principal. Contiene:

Un logotipo en la parte superior.
Un texto explicativo que guía al usuario sobre cómo usar la aplicación.
Dos campos de entrada donde el usuario debe ingresar los números.
Validación en tiempo real para asegurar que solo se ingresen números enteros. Si hay algún error en los campos, el botón "Calcular" se desactiva hasta que la información sea válida.
Un botón "Calcular" que, al ser presionado, redirige a la siguiente pantalla si los campos han sido llenados correctamente.
ResultadoActivity : Una vez que el usuario haya ingresado dos números válidos y presionado "Calcular", será redirigido a esta pantalla. Aquí se mostrará el cálculo del Mínimo Común Múltiplo (MCM) de los dos números ingresados ​​junto con un mensaje que indica qué operación se ha realizado.

Ejemplo de uso
El usuario ingresa los números 5 y 2 en los campos.
Presiona el botón "Calcular".
La aplicación lo redirige a la pantalla de resultado, donde se muestra que el Mínimo Común Múltiplo de 5 y 2 es 10 .