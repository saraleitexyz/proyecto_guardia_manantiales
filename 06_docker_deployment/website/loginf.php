<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="loginf.css">
    <link rel="icon" href="img\Linguardia_app_icon.png">
    <title>Linguardia</title>
</head>
<body>



    <section>

        <div class="titulo">
            <p>Bienvenido de nuevo</p>
        </div>

        <form method="POST" action="Jefatura.php">

            <div id="login">
                <p>Email:</p>
            </div>
            <div class="preglogin">
                <input class="input" type="email" name="email" required>
            </div>

            <div id="login">
                <p>Contraseña:</p>
            </div>
            <div class="preglogin">
                <input class="input" type="password" name="password" required>
            </div>

            <div id="condiciones">
                <p class="olividar" style="color:#78bcc7">¿Olvidaste tu contraseña? </p>  
            </div>
            <div id="condiciones">
                <p class="condi"><input  class="condibot" type="checkbox"> No cerrar sesión </p>  
            </div>
            
            <button type="submit">Iniciar sesión</button>
        </form>

        <div>
            <div class="linea"></div>
        </div>

        <div>
            <p class="yatiene">¿No tienes cuenta? <a href="sign_up.php" style="color:#78bcc7">Regístrate</a> </p>
        </div>













    </section>
    
    


</body>
</html>