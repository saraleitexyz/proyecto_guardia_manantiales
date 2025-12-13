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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.draw.clip

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.font.Font
import com.example.pordios.UserSession.email
import java.time.LocalDate
import java.time.format.DateTimeFormatter // Opcional, por si quieres formatear texto
import java.util.Locale



// Colores
val SoftMint = Color(0xFFA3E4E0)
val SoftPink = Color(0xFFFADCEF)
val SoftPeach = Color(0xFFFFE6D5)
val NavGrey = Color(0xFFD9D9D9)
val DarkText = Color(0xFF1A1A1A)
val PureWhite = Color(0xFFFFFFFF)

val LightBlueBg = Color(0xFFAEE2E6)
val LightGrayButton = Color(0xFFEEEEEE)
val NavPillColor = Color(0xFFD9D9D9)

val GrayText = Color(0xFF757575)
val TealIcon = Color(0xFF4DB6AC)

class DataUserActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = SoftMint
            ) {
                DataUserScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun DataUserScreen() {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp),
        bottomBar = { (CustomBottomBarUser()) }

    ) { paddingValues ->
        // Column principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection() // LLama a la funcion  donde esta lo azul y la foto de usuario

            Spacer(modifier = Modifier.height(30.dp))

            // de Botones del Menú
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuButton(
                    text = "Perfil",
                    icon = Icons.Default.AccountBox,
                    iconTint = TealIcon,
                    onClick = { /* Acción */ } // No llevan a nada
                )
                MenuButton(
                    text = "Configuracion",
                    icon = Icons.Default.Settings,
                    iconTint = TealIcon,
                    onClick = { /* Acción */ } // No llevan a nada
                )
                MenuButton(
                    text = "Cerrar sesion",
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    iconTint = TealIcon,
                    // Lleva al MainActivity.kt
                    onClick = { val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent) }
                )
            }
        }
    }
}

@Composable
fun HeaderSection() {

    val alturaFondoAzul = 320.dp
    val tamanoImagen = 200.dp
    val radioCurva = 120.dp

    // CALCULO MAGICO: Para que la imagen quede mitad dentro y mitad fuera
    // Restamos la mitad de la imagen a la altura del fondo
    val desplazamientoSuperior = alturaFondoAzul - (tamanoImagen / 2)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        // --- CAPA 1: El Fondo Azul ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(alturaFondoAzul)
                .background(
                    color = Color(0xFFAEE2E6),
                    shape = RoundedCornerShape(
                        bottomStart = radioCurva,
                        bottomEnd = radioCurva
                    )
                )
        )

        // --- CAPA 2: Contenido (Imagen + Textos) ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = desplazamientoSuperior) // AQUI ESTÁ EL TRUCO
        ) {
            // Imagen de perfil
            Image(
                painter = painterResource(id = R.drawable.fondo), // Cambiar imagen por la del
                // usuario , agregare imagenes segun registros mysql y hare if-elseif
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(tamanoImagen)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Textos
            Text(
                text = "DULCESOLEI765" ,// Cambiar por nombre completo del select,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Text(
                text = email, // Uilizo el emial ingresado en el formulario
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}




//  Botón de Menú
@Composable
fun MenuButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color = Color.Black,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = LightGrayButton,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().height(76.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )
        }
    }
}

// Footer




@Composable
fun CustomBottomBarUser() {
    val context = LocalContext.current
    Surface(
        color = PureWhite,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp), // Redondeado arriba
        shadowElevation = 10.dp, // Sombra para que flote
        modifier = Modifier.fillMaxWidth().height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Espacio entre elementos
            verticalAlignment = Alignment.CenterVertically
        ) {



            // --- Botón 1 Home ---
            IconButton(onClick = {
                val intent = Intent(context, ScheduleActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = DarkText,
                    modifier = Modifier.size(58.dp)
                )
            }
            // Boton perfil
            Surface(
                color = NavGrey,
                shape = RoundedCornerShape(50),
                modifier = Modifier.height(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val intent = Intent(context, DataUserActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Perfil",
                            tint = DarkText,
                            modifier = Modifier.size(58.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(28.dp))
                    Text(
                        text = "Perfil",
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        fontSize = 18.sp
                    )
                }
            }
            // Botón 3: Chat
            IconButton(onClick = { val intent = Intent(context, NotificationsActivity::class.java)
                context.startActivity(intent) }) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Chat",
                    tint = DarkText,
                    modifier = Modifier.size(58.dp)
                )
            }
        }}}
























































































































