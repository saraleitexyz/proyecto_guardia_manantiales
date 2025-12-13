package com.example.pordios

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.AbsoluteAlignment

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.font.Font
import com.example.pordios.UserSession.email
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale






//  fecha de hoy
@RequiresApi(Build.VERSION_CODES.O)
val fechaHoy = LocalDate.now()

// español
val espanol = Locale("es", "ES")


@RequiresApi(Build.VERSION_CODES.O)
val diaSemana = fechaHoy.format(DateTimeFormatter.ofPattern("EEEE", espanol))
    .replaceFirstChar { it.uppercase() }
@RequiresApi(Build.VERSION_CODES.O)
val nombreMes = fechaHoy.format(DateTimeFormatter.ofPattern("MMMM", espanol))
    .replaceFirstChar { it.uppercase() }
@RequiresApi(Build.VERSION_CODES.O)
val anio = fechaHoy.year.toString()
@RequiresApi(Build.VERSION_CODES.O)
val diaNumero = fechaHoy.dayOfMonth.toString()





class ScheduleActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = SoftMint
            ) {
                ScheduleScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun ScheduleScreen() {

   // Fuente
    val PoppinsFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_bold, FontWeight.Bold)

    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SoftMint), // Fondo


            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Saludo al usuario y fecha del dia

            Card(
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                shape = RoundedCornerShape(bottomEnd = 2.dp, bottomStart = 32.dp), // Bordes muy redondeados
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight() // Se ajusta al contenido
            ) {
                Text(
                    text = "Hola ${email}", // Cambiar por variable nombre completo del mysql
                    color = DarkText,
                    fontSize = 21.sp,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .padding(bottom = 22.dp, top = 10.dp)
                )
                Text(
                    text = "Hoy es ${diaSemana}, ${diaNumero} de ${nombreMes} de ${anio}",
                    color = DarkText,
                    fontSize = 19.sp,
                    fontFamily = PoppinsFamily,
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .padding(bottom = 32.dp, top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))


            // Función del calendario escrita en CalendarWidget.kt
            CalendarWidget()
            Spacer(modifier = Modifier.height(20.dp))
            // Guardias
            Text(
                text = "Guardias",
                color = DarkText,
                fontSize = 25.sp,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(bottom = 2.dp, top = 8.dp)
                    .padding(horizontal = 15.dp)
                    .align(AbsoluteAlignment.Left)
            )
            //Llamada a Funcion de guardias
            GuardiaItem("ASIR 1 DIA LUNES", "13:00-14:00 AULA 1")
            // SE PUEDE REEMPLAZAR  CON CONSULTAS DE MYSQL
            GuardiaItem("ESO 3B DIA MARTES", "12:00-15:00 AULA 4F")
            // Llamada al menu del footer
            CustomBottomBarhome()
        }
    }
}

@Composable
fun GuardiaItem(titulo:String,subtitulo:String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SoftPeach),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. La Imagen del profesor (Círculo)
            // Si no tienes fotos, usa un icono por mientras:
            Surface(
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.size(48.dp),
                color = Color.Gray.copy(alpha = 0.3f)
            ) {
                // AQUÍ iría tu Image(painter = painterResource(id = ...))
                // Por ahora ponemos un icono temporal
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 2. Textos (Título y Subtítulo)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = DarkText
                )
                Text(
                    text = subtitulo,
                    fontSize = 12.sp,
                    color = DarkText.copy(alpha = 0.8f)
                )
            }

            // 3. Icono de 3 puntos
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Opciones",
                tint = DarkText
            )
        }
    }
}
@Composable
// funcion footer
fun CustomBottomBarhome() {
    val context = LocalContext.current
    Surface(
        color = PureWhite,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        shadowElevation = 10.dp, // Sombra para que flote
        modifier = Modifier.fillMaxWidth().height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Botón 1:  ---
            Surface(
                color = NavGrey,
                shape = RoundedCornerShape(50),
                modifier = Modifier.height(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { val intent = Intent(context, ScheduleActivity::class.java)


                        context.startActivity(intent)
                    }){
                        Icon(Icons.Default.Home, contentDescription = null, tint = DarkText , modifier = Modifier.size(58.dp))

                    }
                    Spacer(modifier = Modifier.width(28.dp))
                    Text("Calendario" ,fontWeight = FontWeight.Bold, color = DarkText, fontSize = 18.sp)
                }
            }

            // --- Botón 2: Perfil  ---
            IconButton(onClick = { val intent = Intent(context, DataUserActivity::class.java)


                                    context.startActivity(intent)
                 }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    tint = DarkText,
                    modifier = Modifier.size(58.dp)
                )
            }

            // --- Botón 3: Chat ---
            IconButton(onClick = { val intent = Intent(context, NotificationsActivity::class.java)

                // 3. ¡Importante! Inicia la actividad
                context.startActivity(intent)
            }) {

                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Chat",
                    tint = DarkText,
                    modifier = Modifier.size(58.dp)
                )
            }
        }
    }
}




