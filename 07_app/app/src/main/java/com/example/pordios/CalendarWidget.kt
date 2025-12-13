package com.example.pordios



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarWidget() {
    // ESTADO: Mes actual
    val SoftMint = Color(0xFFA3E4E0)
    val SoftPink = Color(0xFFFADCEF)
    val SoftPeach = Color(0xFFFFE6D5)
    val NavGrey = Color(0xFFD9D9D9)
    val DarkText = Color(0xFF1A1A1A)
    val PureWhite = Color(0xFFFFFFFF)


    // Fechas
    var currentYearMonth by remember { mutableStateOf(YearMonth.now()) }
    val monthName = currentYearMonth.month
        .getDisplayName(TextStyle.FULL, Locale("es", "ES"))
        .replaceFirstChar { it.uppercase() }
    val year = currentYearMonth.year
    val daysInMonth = currentYearMonth.lengthOfMonth()
    val firstDayOfMonth = currentYearMonth.atDay(1)
    val startOffset = firstDayOfMonth.dayOfWeek.value - 1
    val totalCells = startOffset + daysInMonth

    // TARJETA DEL CALENDARIO
    Card(
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // CABECERA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { currentYearMonth = currentYearMonth.minusMonths(1) }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Anterior", tint = Color.Gray)
                }

                Text(
                    text = "$monthName $year",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText // Usa tu variable DarkText
                )

                IconButton(onClick = { currentYearMonth = currentYearMonth.plusMonths(1) }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, "Siguiente", tint = DarkText)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // DÍAS DE LA SEMANA
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                val daysOfWeek = listOf("L", "M", "X", "J", "V", "S", "D")
                daysOfWeek.forEach { day ->
                    Text(text = day, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // GRILLA
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.height(280.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(totalCells) { index ->
                    if (index < startOffset) {
                        Box(modifier = Modifier.size(40.dp))
                    } else {
                        val dayNumber = index - startOffset + 1
                        val isToday = (dayNumber == LocalDate.now().dayOfMonth &&
                                currentYearMonth == YearMonth.now())

                        RealDayItem(day = dayNumber, isToday = isToday)
                    }
                }
            }
        }
    }
}

// Componente pequeño para el día (Privado o público, como prefieras)
@Composable
fun RealDayItem(day: Int, isToday: Boolean) {
    val bgColor = if (isToday) SoftPeach else Color.Transparent
    val txtColor = DarkText

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .aspectRatio(1f)
            .background(bgColor, shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            text = day.toString(),
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
            color = txtColor
        )
    }
}