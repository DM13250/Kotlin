package com.example.appu1a1_diego_moreno

import android.app.Activity
import android.content.Intent
import android.icu.number.NumberFormatter.DecimalSeparatorDisplay
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.appu1a1_diego_moreno.ui.theme.APPu1a1_Diego_MorenoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APPu1a1_Diego_MorenoTheme {
                MinimoComunMultiplo()
            }
        }
    }
}

@Composable
fun MinimoComunMultiplo() {
    Scaffold { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    val mContext = LocalContext.current
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var errorMessage1 by remember { mutableStateOf("") }
    var errorMessage2 by remember { mutableStateOf("") }
    val mensajeError = stringResource(R.string.mensajeError1)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00595F),
                        Color(0xFFCEDC00),
                        Color(0xFF00594F)
                    ),
                    startY = 0f,
                    endY = 1500f // Cambia este valor si necesitas un efecto más amplio
                )
            )
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado con imagen
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Cambia a tu icono
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(50.dp)) // Borde redondo
                .border(2.dp, Color(0xFFCEDC00), RoundedCornerShape(50.dp)) // Añadir el borde
                .background(Color(0xFF00594F)) // Fondo dentro del borde
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.titulo),
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFFFFFFFF),
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = stringResource(R.string.texto1),
            fontSize = 14.sp,
            color = Color(0xFFFFFFFFF),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = numero1,
            onValueChange = {
                numero1 = it
                errorMessage1 = if (it.toIntOrNull() == null && it.isNotBlank()) {
                    mensajeError
                } else {
                    ""
                }
            },
            label = { Text(text = stringResource(R.string.num1), color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp), // Bordes redondeados
            isError = errorMessage1.isNotEmpty(), // Marcar como error si hay mensaje
            textStyle = TextStyle(color = Color.White),
            supportingText = {
                if (errorMessage1.isNotEmpty()) {
                    Text(text = errorMessage1, color = Color.Red)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = numero2,
            onValueChange = {
                numero2 = it
                errorMessage2 = if (it.toIntOrNull() == null && it.isNotBlank()) {
                    mensajeError
                } else {
                    ""
                }
            },
            label = { Text(text = stringResource(R.string.num2), color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp), // Bordes redondeados
            isError = errorMessage2.isNotEmpty(), // Marcar como error si hay mensaje
            supportingText = {
                if (errorMessage2.isNotEmpty()) {
                    Text(text = errorMessage2, color = Color.Red)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val isButtonEnabled = numero1.isNotBlank() && numero2.isNotBlank() &&
                numero1.toIntOrNull() != null && numero2.toIntOrNull() != null

        Button(
            onClick = {
                val num1 = numero1.toIntOrNull()
                val num2 = numero2.toIntOrNull()

                // Validar las entradas
                if (num1 != null && num2 != null) {
                    val resultado = minimoComunMultiplo(num1, num2)
                    val intent = Intent(mContext, ResultadoFinal::class.java).apply {
                        putExtra("numero1", num1)
                        putExtra("numero2", num2)
                        putExtra("resultado", resultado)
                    }
                    mContext.startActivity(intent)
                }
            },
            modifier = Modifier
                .padding(top = 25.dp)
                .clip(RoundedCornerShape(50.dp)) // Clip para ajustar la forma del fondo
                .border(2.dp, Color(0xFFCEDC00), RoundedCornerShape(50.dp)), // Añadir el borde
            enabled = isButtonEnabled,// Habilitar boton solo si hay entradas
            colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF00594F) else Color.Gray // Color de fondo segun el estado
            )
        ) {
            Text(text = stringResource(R.string.calcular), color = Color.White)
        }
    }
    }
}

/**
 * Para calcula el MCM es a*b entre minimo comun divisor de a, b
 */
fun minimoComunMultiplo(numero1: Int, numero2: Int):Int {
    return (numero1 * numero2) / mcd(numero1, numero2)  // Utiliza la fórmula MCM(a, b) = |a * b| / MCD(a, b)
}


fun mcd(numero1: Int, numero2: Int): Int {
    var num1 = numero1
    var num2 = numero2
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinimoComunMultiplo()
}