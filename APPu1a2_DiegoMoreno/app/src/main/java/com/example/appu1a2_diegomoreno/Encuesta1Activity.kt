package com.example.appu1a2_diegomoreno

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appu1a2_diegomoreno.ui.theme.APPu1a2_DiegoMorenoTheme

class Encuesta1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APPu1a2_DiegoMorenoTheme {
                Encuesta1Screen { selectedOption ->
                    // Devolver los resultados a la actividad llamante
                    val resultIntent = Intent().apply {
                        putExtra("selected_option", selectedOption)
                        putExtra("source", "Encuesta 1")
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Encuesta1Screen(onSubmit: (String) -> Unit) {
    Scaffold {
        Encuesta1BodyContent(onSubmit)
    }
}

@Composable
fun Encuesta1BodyContent(onSubmit: (String) -> Unit) {
    // Usamos un Box para superponer la imagen y los elementos de UI
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "background",
            alignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Capa semitransparente para mejorar la legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Contenido encima del fondo
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // RadioButton Example
                RadioButtonExample(onSubmit)
            }
        }
    }
}

@Composable
fun RadioButtonExample(onSubmit: (String) -> Unit) {
    // Lista de opciones
    val options = listOf(
        stringResource(R.string.radio1), stringResource(R.string.radio2),
        stringResource(R.string.radio3),
        stringResource(R.string.radio4),
        stringResource(R.string.radio5),
        stringResource(R.string.radio6)
    )

    // Estado para la opción seleccionada
    var selectedOption by rememberSaveable { mutableStateOf(options[0]) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.radioPregunta),
            style = MaterialTheme.typography.titleMedium,
        )
        // Mostrar los RadioButtons
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFFDA9195), // Color cuando está seleccionado
                        unselectedColor = Color(0xFF15212B) // Color cuando no está seleccionado
                    )
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Mostrar la opción seleccionada
        Text(
            text = "${stringResource(R.string.opcionSeleccionada)} $selectedOption",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Botón para enviar la opción seleccionada
        Button(onClick = { onSubmit(selectedOption) },
            modifier = Modifier.fillMaxWidth(),  // Mover el modificador fuera del onClick
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA9195)),  // También mover esto fuera
            shape = RoundedCornerShape(8.dp)) {
            Text(text = stringResource(R.string.enviar))
        }
    }
}
