<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="jefaturacss.css">
    <link rel="icon" href="img/Linguardia_app_icon.png">
    <title>Linguardia</title>
</head>
<body>

    <nav>
        <div class="navega">
            <p>Bienvenido a gestion de usuarios</p> 
            <img src="img\login-sesion.png" alt="Icono de sesión">
        </div>
    </nav>

    <div class="contenido">

        <article>
            <table>
            <td> 
                <tr>
                    <button class="boton" id="btn-add" type="button" onclick="cargarContenido('add')">Añadir usuarios</button>
                </tr>
                <tr>
                    <button class="boton" id="btn-delete" type="button" onclick="cargarContenido('delete')">Eliminar usuarios</button>
                </tr>
                <tr>
                    <button class="boton" id="btn-modify" type="button" onclick="cargarContenido('modify')">Modificar usuarios</button>
                </tr>
                <tr>
                    <button class="botonfin" id="btn-list" type="button" onclick="cargarContenido('list')">Ver lista de usuarios</button>
                </tr>
            </td>
        </table>
        </article>

        <section id="area-contenido">
            <div>
                <h3>Bienvenido a gestion de usuarios de Linguardia</h3>
                <p>Selecciona una opción del menú de la izquierda para comenzar.</p>
            </div>
        </section>

    </div>

    
    
    <script>
        function cargarContenido(idContenido) {
            const areaContenido = document.getElementById('area-contenido');
            
            // 1. LIMPIAR: Borrar el contenido anterior para que solo quede uno.
            areaContenido.innerHTML = '';
            
            // 2. Crear el nuevo div contenedor y definir variables
            const nuevoDiv = document.createElement('div');
            let contenidoHTML = '';
            
            // 3. Determinar el contenido según el ID
            switch (idContenido) {
                case 'add':
                    // Contenido para Añadir usuarios (Formulario)
                    nuevoDiv.className = 'contenido-form'; // Clase CSS para el formulario
                    contenidoHTML = `
                        <h2>AÑADIR USUARIOS</h2>
                        <form id="form-add-user">
                            <p>Complete los datos para agregar un nuevo usuario:</p>
                            <label for="user_name">Nombre:</label><br>
                            <input type="text" id="user_name" name="user_name" required style="width: 80%; padding: 8px; margin-bottom: 10px;"><br>
                            
                            <label for="user_email">Apellidos:</label><br>
                            <input type="text" id="user_apellido" name="user_apellido" required style="width: 80%; padding: 8px; margin-bottom: 10px;"><br>
                            
                            

                            <button type="submit" class="boton" style="width: 50%;">Registrar Usuario</button>
                        </form>
                    `;
                    break;

                case 'delete':
                    // Contenido para Eliminar usuarios
                    nuevoDiv.className = 'contenido-info'; 
                    contenidoHTML = `
                        <h2>ELIMINAR USUARIOS</h2>
                        <p>Herramienta para la baja de cuentas.</p>
                        <input type="text" placeholder="Nombre del usuario a eliminar" style="width: 80%; padding: 8px; margin-bottom: 10px;"><br>
                        <button class="boton" style="width: 50%;">Buscar y Eliminar</button>
                    `;
                    break;
                    
                case 'modify':
                    // Contenido para Modificar usuarios
                    nuevoDiv.className = 'contenido-info'; 
                    contenidoHTML = `
                        <h2>MODIFICAR USUARIOS</h2>
                        <p>Busque el usuario para actualizar sus datos o permisos.</p>
                        <input type="text" placeholder="Nombre del usuario a modificar" style="width: 80%; padding: 8px; margin-bottom: 10px;"><br>
                        <button class="boton" style="width: 50%;">Buscar Usuario</button>
                    `;
                    break;
                    
                case 'list':
                    // Contenido para Ver lista de usuarios
                    nuevoDiv.className = 'contenido-info'; 
                    contenidoHTML = `
                        <h2>LISTA COMPLETA DE USUARIOS</h2>
                        <p>Mostrando los primeros 100 usuarios activos:</p>
                        <table border="1" style="width: 100%; border-collapse: collapse;">
                            <thead>
                                <tr><th>ID</th><th>Nombre</th><th>Apellido</th></tr>
                            </thead>
                            <tbody>
                                <tr><td>p001</td><td>Jorge</td><td>Camacho Vela</td></tr>
                                <tr><td>p002</td><td>Francisco</td><td>Villalba Sánchez</td></tr>
                            </tbody>
                        </table>
                    `;
                    break;
                
                default:
                    nuevoDiv.className = 'contenido-info';
                    contenidoHTML = '<h2>Error de Carga</h2><p>Contenido no encontrado.</p>';
            }
            
            // 4. Asignar el contenido y añadir el nuevo div
            nuevoDiv.innerHTML = contenidoHTML;
            areaContenido.appendChild(nuevoDiv);
        }
    </script>

</body>
</html>