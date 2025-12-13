package com.example.pordios

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent
import com.example.pordios.ui.theme.PordiosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PordiosTheme {
                FullScreenImageScreen()
            }
        }
    }
}

@Composable
fun FullScreenImageScreen() {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // IMAGEN DE FONDO
            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = "Imagen de fondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // TEXTO 1: "¡Bienvenido!"
            Text(
                color = Color.White ,
                text = "¡Bienvenido!" ,
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    // PADDING para separar del texto de abajo
                    .padding(bottom = 60.dp)
            )

            // TEXTO 2: "Introduce tus datos"
            Text(
                color = Color.White ,
                text = "Introduce tus datos para poder acceder a la seccion de tus guardias" ,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 60.dp, start = 32.dp, end = 32.dp)
            )





            // CONTENEDOR PARA LOS DOS BOTONES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // BOTÓN "Iniciar sesión"
                Button(
                    onClick = {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text("Iniciar sesión", color = Color(0xFF1E8A9E)) // color turquesa del logo
                }


                // ESPACIADOR
                Spacer(modifier = Modifier.weight(0.1f))

                // BOTÓN "Registrarse" (BLANCO)
                Button(
                    onClick = {

                            val intent = Intent(context, SignupActivity::class.java)
                            context.startActivity(intent)

                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E8A9E))
                ) {
                    Text("Registrarse", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FullScreenImageScreenPreview() {
    PordiosTheme {
        FullScreenImageScreen()
    }
}