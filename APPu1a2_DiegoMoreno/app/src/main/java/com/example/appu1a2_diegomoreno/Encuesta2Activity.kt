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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appu1a2_diegomoreno.ui.theme.APPu1a2_DiegoMorenoTheme

class Encuesta2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APPu1a2_DiegoMorenoTheme {
                Encuesta2Screen { selectedOptions ->
                    // Devolver los resultados a la actividad llamante
                    val resultIntent = Intent().apply {
                        putExtra("selected_option", selectedOptions.joinToString())
                        putExtra("source", "Encuesta 2")
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish() // Finaliza la actividad
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Encuesta2Screen(onSubmit: (Set<String>) -> Unit) {
    Scaffold {
        Encuesta2BodyContent(onSubmit)
    }
}

@Composable
fun Encuesta2BodyContent(onSubmit: (Set<String>) -> Unit) {
    // Imagen de fondo
    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = "background",
        alignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenido encima del fondo
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Checkboxes
            Checkboxes(onSubmit)
        }
    }
}

@Composable
fun Checkboxes(onSubmit: (Set<String>) -> Unit) {
    // Lista de opciones
    val options = listOf(
        stringResource(R.string.check1), stringResource(R.string.check2),
        stringResource(R.string.check3), stringResource(R.string.check4),
        stringResource(R.string.check5))
    // Estado para las opciones seleccionadas
    var selectedOptions by rememberSaveable { mutableStateOf(setOf<String>()) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.checkPregunta),
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        // Mostrar los Checkboxes
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                val isSelected = option in selectedOptions
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = {
                        selectedOptions = if (isSelected) {
                            selectedOptions - option
                        } else {
                            selectedOptions + option
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFDA9195),    // Color cuando está seleccionado
                        uncheckedColor = Color(0xFF15212B),    // Color del borde cuando no está seleccionado
                        checkmarkColor = Color.White   // Color de la marca de verificación
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

        // Mostrar las opciones seleccionadas
        Text(
            text = "${stringResource(R.string.checkSeleccionado)} ${if (selectedOptions.isEmpty()) stringResource(R.string.ninguno) else selectedOptions.joinToString()}",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Botón para enviar las opciones seleccionadas
        Button(onClick = { onSubmit(selectedOptions) },
            modifier = Modifier.fillMaxWidth(),  // Mover el modificador fuera del onClick
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA9195)),  // También mover esto fuera
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(R.string.enviar))
        }
    }
}
