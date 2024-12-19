Nombre de la aplicación: APPu1a2_Diego_Moreno
Función principal:  Obtener resultados de encuestas y realizar la multiplicación de matrices de un tamaño definido por el usuario.
Funcionalidades
La aplicación permite al usuario participar en dos tipos de encuestas. Una de ellas utiliza checkboxes (casillas de verificación) y la otra radiobuttons (botones de opción).
Al enviar la respuesta seleccionada, el usuario será redirigido automáticamente a la pantalla principal, donde podrá visualizar las últimas respuestas mediante un switch que alterna entre las encuestas.

En la pantalla principal, el usuario puede introducir un número en un campo de entrada, correspondiente al tamaño de la matriz, y presionar el botón Calcular para generar la matriz.

Pantallas 
MainActivity : Es la pantalla principal. Contiene:

Un logotipo en la parte superior.
Una imagen de fondo.
Un texto explicativo que guía al usuario sobre cómo usar la aplicación.
Dos botones para mandarte a las pantallas de sus respectivas encuestas.
Un text mostrando por pantalla la última respuesta de las encuentas.
Un switch para mostrar la última respuesta de cada encuesta, dependiendo de que lado este activado se mostrará una u otra.
Una barra divisoria.
Un campo tipo número para envia el tamaño de la matriz.
Un botón "Calcular" que, al ser presionado, redirige a la siguiente pantalla si ha sido rellenado correctamene.

Encuesta1Activity : Permite al usuario responder a una pregunta seleccionando una opción mediante un radiobutton. Al presionar Enviar, la respuesta será guardada.

Encuesta2Activity : Permite al usuario responder a una pregunta seleccionando varias opciones mediante checkboxes. Al presionar Enviar, las respuestas serán guardadas.

Matriz : Muestra un texto indicando que la matriz se está generando. Una vez completada la operación de multiplicación, el resultado se muestra en pantalla.