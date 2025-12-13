package com.example.pordios

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class NotificationsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = LightBlueBg // Fondo azul claro general
            ) {
                NotificationsScreen()
            }
        }
    }
}

// Modelo de datos para las notificaciones
data class NotificationItem(
    val title: String,
    val body: String,
    val date: String = "",
    val isPinned: Boolean = false,
    val hasImage: Boolean = false,
    val backgroundColor: Color = Color.White
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationsScreen() {
    val context = LocalContext.current

    // Notificaciones
    val notifications = listOf(
        NotificationItem(
            title = "Guia de uso basico de la aplicacion",
            body = "Este mensaje tiene como finalidad orientar a aquellos maestros que la utilicen por...",
            isPinned = true,
            backgroundColor = SoftPink // El color rosa definido en tu otro archivo
        ),
        NotificationItem(
            title = "Recordatorio del minimo de horas",
            body = "Recordamos que todos los profesores han de cubrir un minimo de x horas a la ...",
            date = "2 dic.",
            backgroundColor = Color.White
        ),
        NotificationItem(
            title = "Alberto Leite Branco se ha reincorporado",
            body = "Alberto de ASIR 1 volvera a clase a partir del 3 de diciembre...",
            date = "1 dic.",
            hasImage = true, // Simularemos la foto
            backgroundColor = Color.White
        )
    )

    Scaffold(
        bottomBar = { CustomBottomBarNotifications() },
        containerColor = LightBlueBg // Asegura fondo azul
    ) { paddingValues ->
        Row (modifier = Modifier
            .background(color = PureWhite, shape = RoundedCornerShape(bottomEnd = 2.dp, bottomStart = 32.dp))
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 15.dp)
            ){
            TopTabsSection()
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(vertical = 58.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(notifications) { item ->
                    NotificationCard(item)
                }
            }
        }
    }
}

@Composable
// Recibidos y enviados
fun TopTabsSection() {
    Surface(
        color = Color(0xFFEFEFEF).copy(alpha = 0.5f),//
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(50.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Pestaña Izquierda: Recibidos (Seleccionada - Gris Oscuro)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFFD0D0D0))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Recibidos",
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
            }

            // Pestaña Derecha: Enviados (No seleccionada)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Transparent)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Enviados",
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = item.backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Cabecera: Título y (Pin o Fecha)
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DarkText,
                    modifier = Modifier.weight(1f)
                )

                if (item.isPinned) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Fijado",
                        tint = DarkText,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cuerpo: Texto e Imagen opcional
            Row(verticalAlignment = Alignment.CenterVertically) {

                if (item.hasImage) {
                    // Imagen pequeña redonda (Simulada)
                    Image(
                        painter = painterResource(id = R.drawable.fondo), // Usamos tu imagen de recurso existente
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Text(
                    text = item.body,
                    fontSize = 14.sp,
                    color = DarkText,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Fecha (si no está fijado y tiene fecha)
            if (!item.isPinned && item.date.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.date,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

// --- Barra Inferior Específica para Notificaciones ---
@Composable
fun CustomBottomBarNotifications() {
    val context = LocalContext.current
    Surface(
        color = PureWhite,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        shadowElevation = 10.dp,
        modifier = Modifier.fillMaxWidth().height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Botón 1: Calendario / Home ---
            IconButton(onClick = {
                val intent = Intent(context, ScheduleActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = DarkText,
                    modifier = Modifier.size(58.dp) // Icono un poco más pequeño para balancear
                )
            }

            // --- Botón 2: Perfil ---
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

            // --- Botón 3: Notificaciones (SELECCIONADO) ---
            Surface(
                color = NavGrey, // Fondo gris
                shape = RoundedCornerShape(50), // Pastilla
                modifier = Modifier.height(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Notificaciones",
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Email, // Icono estilo chat
                        contentDescription = "Notificaciones",
                        tint = DarkText,
                        modifier = Modifier.size(58.dp)
                    )
                }
            }
        }
    }
}


