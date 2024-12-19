package com.example.appu1a2_diegomoreno

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appu1a2_diegomoreno.ui.theme.APPu1a2_DiegoMorenoTheme

class Matriz: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Obtener el número de la matriz desde el Intent
        val numeroMatriz = intent.getIntExtra("numeroMatriz", 0)

        setContent {
            APPu1a2_DiegoMorenoTheme {
                MatrizScreen(numeroMatriz)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MatrizScreen(numeroMatriz: Int) {
    // Scaffold para estructura de pantalla con barra de estado
    Scaffold() {
        MatrizBodyContent(numeroMatriz)
    }
}

@Composable
fun MatrizBodyContent(numeroMatriz: Int) {
    val esperandoMatriz = stringResource(R.string.esperandoMatriz)
    val matrizGenerada1 = stringResource(R.string.matrizGenerada1)
    val matrizGenerada2 = stringResource(R.string.matrizGenerada2)
    var mensaje by remember { mutableStateOf(esperandoMatriz) }
    var matriz: Array<IntArray>? by remember { mutableStateOf(null) }

    // Efecto lanzado para generar la matriz
    LaunchedEffect(numeroMatriz) {
        matriz = generarMatriz(numeroMatriz)
        mensaje = "$matrizGenerada1 $numeroMatriz x $numeroMatriz $matrizGenerada2"
        generarMatriz(numeroMatriz);
    }

    // Imagen de fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido principal centrado en la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = mensaje,
                fontSize = 25.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

fun generarMatriz(size: Int): Array<IntArray> {
    // Crear una matriz de size x size con valores inicializados a 0
    return Array(size) { IntArray(size) { 0 } }
}

@Preview(showBackground = true)
@Composable
fun MatrizScreenPreview() {
    APPu1a2_DiegoMorenoTheme {
        MatrizScreen(4) // Preview con una matriz de 4x4
    }
}
