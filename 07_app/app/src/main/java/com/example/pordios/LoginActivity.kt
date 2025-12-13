package com.example.pordios

import android.content.Intent
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.pordios.UserSession.email
import com.example.pordios.UserSession.password
import com.example.pordios.UserSession.rememberMe

object UserSession { // Lo que se ponga en el formulario se guardan en estas variables que sirven en
                    // todos los archivos
    var email by mutableStateOf("")
    var password by  mutableStateOf("")
    var rememberMe by mutableStateOf(false)

}

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    // Variables que se guardan en el formulario

    // Variables color y context
    val TealColor = Color(0xFF1E8A9E)
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
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
        // Formulario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.64f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 2.dp)
                )
                .align(Alignment.BottomCenter)

                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {


            Text(
                text = "Bienvenido de nuevo",
                color = Color.Black,
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp, top = 46.dp)
            )

            // Campo de Email
            Text(text = "Email", color = TealColor,fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth().padding(start = 5.dp))
            // Input de mail
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(bottom = 8.dp, top = 1.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,     // <<-- ASEGÚRATE DE QUE EL TEXTO SEA NEGRO
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = TealColor, unfocusedIndicatorColor = Color(0xFFABABAB),
                    cursorColor = TealColor
                ),
                shape = RoundedCornerShape(9.dp)
            )

            // Campo de Contraseña
            Text(text = "Contraseña", color = TealColor, fontWeight = FontWeight.Bold,fontSize = 20.sp, modifier = Modifier.fillMaxWidth().padding(top = 22.dp))

            // Input contraseña
            OutlinedTextField(
                value = password, // Usamos el nuevo estado 'password'
                onValueChange = { password = it },
                singleLine = true,

                // Seguridad visual para la contraseña
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(top = 6.dp).padding(bottom = 6.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = TealColor, unfocusedIndicatorColor = Color(0xFFABABAB),
                    cursorColor = TealColor
                ),
                shape = RoundedCornerShape(9.dp)
            )

            //  Sección de Recuperación de Contraseña

            // Enlace "¿Olvidaste tu contraseña?" LO DEBO TRANSFORMAR PARA QUE PONGA UN
            // MENSAJE PREDETERMINADO DE QUE SE ENVIO UN ENLACE AL MAIL PARA RECUPERAR LA CUENTA
            TextButton(onClick = { val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)  }) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = TealColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End).padding(top = 8.dp)
                )
            }


            // Casilla "No cerrar sesión"
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
                    text = "No cerrar sesión",
                    color = Color.Black,
                    fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,

                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            // Botón "Iniciar Sesión"
            Button(
                // EL ONCLICK DENTRO DEBE TENER UN IF DONDE VOY A DEJAR EL EJEMPLO
                // , DEBE TENER CONTRASEÑA CORRECTA O USUARIO Y SI ES CORRECTO
                // CON LA BASE DE DATOS QUE ENTRE A ScheduleActivity , lo dejare
                // por defecto puesto para que entre
                // onClick = {
                //                val intent = Intent(context,
                //
                //                 if (consulta_correcta){
                //                      DataUserActivity::class.java
                //                  }
                //                 else { mensaje de error }
                //
                //
                //                )

                //                context.startActivity(intent)
                //            }
                onClick = { val intent = Intent(context, ScheduleActivity::class.java)
                            context.startActivity(intent)
                          },
                colors = ButtonDefaults.buttonColors(containerColor = TealColor),
                modifier = Modifier.fillMaxWidth().height(72.dp)
            ) {
                Text("Iniciar sesión", color = Color.White ,fontWeight = FontWeight.Bold,fontSize = 22.sp)
            }



            // Línea divisoria
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth().padding(top = 44.dp)
            )

            // Enlace "¿No tienes cuenta? Regístrate"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿No tienes cuenta?",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 28.dp)

                )
                Spacer(modifier = Modifier.width(2.dp))
                TextButton(onClick = { val intent = Intent(context, SignupActivity::class.java)
                    context.startActivity(intent)  }) {
                    Text(
                        text = "Regístrate",
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

