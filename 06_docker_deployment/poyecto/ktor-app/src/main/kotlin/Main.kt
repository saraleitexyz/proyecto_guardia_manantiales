// Archivo: src/main/kotlin/com/tuempresa/tuproyecto/Application.kt (o Main.kt)

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.System.getenv
import io.ktor.server.engine.*

// ----------------------------------------------------------------------
// 1. FUNCIÓN DE CONEXIÓN A LA BASE DE DATOS
// ----------------------------------------------------------------------
fun Application.configureDatabase() {
    // Lectura de las variables inyectadas por Docker Compose
    val host = getenv("DB_HOST") ?: "localhost"
    val port = getenv("DB_PORT") ?: "3306"
    val name = getenv("DB_NAME") ?: "linguardia"
    val user = getenv("DB_USER") ?: "sara"
    val password = getenv("DB_PASSWORD") ?: "sarapass"

    val jdbcUrl = "jdbc:mysql://$host:$port/$name?serverTimezone=UTC"

    Database.connect(
        url = jdbcUrl,
        driver = "com.mysql.cj.jdbc.Driver", // Driver de MySQL
        user = user,
        password = password
    )

    transaction {
        // Aquí debes crear las tablas (ejemplo: SchemaUtils.create(TuTabla))
        // Esto solo se ejecuta la primera vez si la base de datos está vacía.
    }
}

// ----------------------------------------------------------------------
// 2. FUNCIÓN DE MÓDULO (PUNTO DE CONFIGURACIÓN DE LA APP)
// ----------------------------------------------------------------------
fun Application.module() {
    // ¡Aquí va la llamada! Se ejecuta primero al iniciar la app.
    configureDatabase()

    // Aquí irían el resto de tus configuraciones, como JSON, Routing, etc.
    // configureRouting()
    // configureSerialization()
}

// ----------------------------------------------------------------------
// 3. FUNCIÓN MAIN (PUNTO DE INICIO)
// ----------------------------------------------------------------------
fun main() {
    // Asegúrate de que esta línea llama correctamente a tu módulo.
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}