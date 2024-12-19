package com.example.appu1a1_diego_moreno

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appu1a1_diego_moreno.ui.theme.APPu1a1_Diego_MorenoTheme

class ResultadoFinal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val numero1 = intent.getIntExtra("numero1", 0);
        val numero2 = intent.getIntExtra("numero2", 0);
        val resultado = intent.getIntExtra("resultado",0);
        setContent {
            APPu1a1_Diego_MorenoTheme {
                    Resultado(numero1, numero2, resultado)
            }
        }
    }
}

@Composable
fun Resultado(numero1: Int, numero2: Int, resultado: Int) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF0F4F8)
    ){ innerPadding ->
        BodyResultadoContent(numero1, numero2, resultado, Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyResultadoContent(numero1: Int, numero2: Int, resultado: Int, modifier: Modifier = Modifier) {
    val mContext = LocalContext.current
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
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encabezado con imagen
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp)) // Borde redondo
                    .border(2.dp, Color(0xFFCEDC00), RoundedCornerShape(50.dp)) // Añadir el borde
                    .background(Color(0xFF00594F)) // Fondo dentro del borde
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "${stringResource(R.string.mcm1)} $numero1 ${stringResource(R.string.mcm2)} $numero2 ${stringResource(R.string.mcm3)}",
                fontSize = 25.sp,
                color = Color(0xFFFFFFFFF),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "$resultado",
                fontSize = 42.sp,
                color = Color(0xFFFFFFFFF),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), // Cambia el estilo a negrita
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultadoPreview() {
    Resultado(3, 5, 15)
}