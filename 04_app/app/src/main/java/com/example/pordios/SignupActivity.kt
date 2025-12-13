package com.example.pordios

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontWeight

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SignupScreen()
            }
        }
    }
}

@Composable
fun SignupScreen() {
    // Variables que se guardan en el formulario
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    // Variables color y context
    val context = LocalContext.current
    val TealColor = Color(0xFF1E8A9E)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Botón de volver
        TextButton(onClick = { val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)  }) {
            Text(
                text = "> Volver",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 28.dp)
            )
        }
        // Contenedor principal del formulario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.74f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            // Formulario ---

            Text(
                text = "Crea una cuenta",
                color = Color.Black,
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp, top = 46.dp)
            )
            Text(
                text = "Nombre Completo",
                color = TealColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp)
            )
            // Input de nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().height(56.dp)
                    .padding(bottom = 6.dp, top = 1.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = TealColor, unfocusedIndicatorColor = Color(0xFFABABAB),
                    cursorColor = TealColor
                ),
                shape = RoundedCornerShape(9.dp)
            )
            // Campo de Email
            Text(
                text = "Email",
                color = TealColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp,top = 18.dp)
            )
            // Input de mail
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,

                modifier = Modifier.fillMaxWidth().height(56.dp)
                    .padding(bottom = 8.dp, top = 2.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = TealColor, unfocusedIndicatorColor = Color(0xFFABABAB),
                    cursorColor = TealColor
                ),
                shape = RoundedCornerShape(9.dp)
            )

            // Campo de Contraseña
            Text(
                text = "Contraseña",
                color = TealColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 18.dp)
            )
            // Input de contraseña
            OutlinedTextField(
                value = password, // Usamos el nuevo estado 'password'
                onValueChange = { password = it },
                singleLine = true,

                // **Añadir seguridad visual para la contraseña**
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(top = 2.dp)
                    .padding(bottom = 6.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = TealColor, unfocusedIndicatorColor = Color(0xFFABABAB),
                    cursorColor = TealColor
                ),
                shape = RoundedCornerShape(9.dp)
            )

            // --- Sección terminos y condiciones" ---

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(checkedColor = TealColor)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Estoy de acuerdo con los terminos y condiciones de uso",
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp,top = 18.dp)


                    )
            }

            // Espacio antes del botón
            Spacer(modifier = Modifier.height(10.dp))

            // Botón Registrarse
            Button(
                onClick = {  }, // Hacer un insert , yo Jazmin pongo un mensaje alerta
                                // que debe confirmar su cuenta e iniciar sesión
                colors = ButtonDefaults.buttonColors(containerColor = TealColor),
                modifier = Modifier.fillMaxWidth().height(72.dp)
            ) {
                Text(
                    "Registrarse",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }


            // Línea divisoria
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth().padding(top = 44.dp)
            )

            // Enlace "¿Ya tienes cuenta"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Ya tienes cuenta?",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 28.dp)

                )
                Spacer(modifier = Modifier.width(2.dp))
                // Boton que lleva a la pagina de iniciar sesión
                TextButton(onClick = {  val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent) }) {
                    Text(
                        text = "Inicia sesion",
                        color = TealColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 28.dp)
                    )
                }
            }
        }
    }
}









