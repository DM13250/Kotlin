package com.example.appu1a2_diegomoreno

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appu1a2_diegomoreno.ui.theme.APPu1a2_DiegoMorenoTheme

class MainActivity : ComponentActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var selectedOption: String? = null
    private var source: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar el ActivityResultLauncher
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    selectedOption = data?.getStringExtra("selected_option")
                    source = data?.getStringExtra("source")
                    setContent {
                        APPu1a2_DiegoMorenoTheme {
                            FirstScreen(selectedOption, source)
                        }
                    }
                }
            }

        // Obtener el Intent y la opción seleccionada
        selectedOption = intent.getStringExtra("selected_option")
        source = intent.getStringExtra("source")
        setContent {
            APPu1a2_DiegoMorenoTheme {
                FirstScreen(selectedOption, source)
            }
        }

    }

    @Composable
    fun FirstScreen(selectedOption: String?, source: String?) {
        Scaffold { innerPadding ->
            BodyContent(selectedOption, source, Modifier.padding(innerPadding))
        }
    }

    @Composable
    fun BodyContent(selectedOption: String?, source: String?, modifier: Modifier = Modifier) {
        val mContext = LocalContext.current
        val switchState = remember { mutableStateOf(false) }
        val numeroMatriz = remember { mutableStateOf("") }
        val error = stringResource(R.string.error);
        val encuesta1 = stringResource(R.string.encuesta1);
        val encuesta2 = stringResource(R.string.encuesta2);
        // Declarar valor1 y valor2 como estados mutables que se pueden actualizar
        val valor1 = remember { mutableStateOf(error) }
        val valor2 = remember { mutableStateOf(error) }

        // Actualizar valor1 o valor2 dependiendo de la fuente
        if (source == encuesta1) {
            valor1.value = selectedOption ?: error
        } else if (source == encuesta2) {
            valor2.value = selectedOption ?: error
        }

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "background",
            alignment = Alignment.BottomCenter,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(R.string.introduccion),
                fontSize = 15.sp,
                color = Color(0xFF15212B),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(mContext, Encuesta1Activity::class.java)
                        activityResultLauncher.launch(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA9195)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = encuesta1)
                }
                Button(
                    onClick = {
                        val intent = Intent(mContext, Encuesta2Activity::class.java)
                        activityResultLauncher.launch(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA9195)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = encuesta2)
                }
            }

            val encuestaText = when {
                source == encuesta1 && switchState.value -> "${encuesta2}: ${valor2.value}"
                source == encuesta1 -> "${encuesta1}: ${valor1.value}"
                source == encuesta2 && switchState.value -> "${encuesta2}: ${valor2.value}"
                source == encuesta2 -> "${encuesta1}: ${valor1.value}"
                else -> error
            }

            Text(
                text = encuestaText,
                color = Color(0xFF15212B),
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 14.sp,
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Switch(
                    checked = switchState.value,
                    onCheckedChange = { switchState.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFFDA9195),
                        uncheckedThumbColor = Color(0xFF494454),
                        checkedTrackColor = Color(0xFF6D5058),
                        uncheckedTrackColor = Color(0xFF7C7979)
                    )
                )
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp
            )

            OutlinedTextField(
                value = numeroMatriz.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                        numeroMatriz.value = newValue
                    }
                },
                label = {
                    Text(
                        text = stringResource(R.string.tamannoMatriz),
                        color = Color(0xFF15212B)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.placeholder),
                        color = Color.Black
                    )
                },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
            )

            Button(
                onClick = {
                    // Validar las entradas
                    val numMatriz = numeroMatriz.value.toIntOrNull()
                    if (numMatriz != null) {
                        val intent = Intent(mContext, Matriz::class.java).apply {
                            putExtra("numeroMatriz", numMatriz)
                        }
                        mContext.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth(),  // Mover el modificador fuera del onClick
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA9195)),  // También mover esto fuera
                shape = RoundedCornerShape(8.dp)  // Y esto también
            ) {
                Text(text = stringResource(R.string.calcular), color = Color.White)
            }
        }
    }
}
