// Mensajes iniciales del bot
        const initialMessages = [
            {
                text: "¡Hola! Soy el bot de Linguardia 🤖 ¿En qué puedo ayudarte hoy?",
                sender: "bot",
                time: getCurrentTime()
            }
        ];

        // Respuestas del bot
        const botResponses = {
            "hola": ["¡Hola! ¿Cómo estás?", "¡Hola! Encantado de verte", "¡Hola! ¿En qué puedo ayudarte?"],
            "ayuda": ["Dime en que necesitas ayuda", "Si tu respuesta no la encuentras escribe contacto.."],
            "guardias": ["Las guardias se organizan segun usuario , los directivos cargan cada semana y dia las guardias en cada profe"],
            "bajas": ["Si necesitas comunicar una baja en el menú de tu app hay una opcion de COMUNICAR BAJA", "Si la app no funciona llama a tu centro en caso de necesitar baja"],
            "inscripción": ["Para inscribirte puedes venir a nuestra sede o contactarnos por teléfono. 777646569"],
            "contacto": ["Nuestro teléfono: 777646569 , Nuestro email avenidasiempreviva@gmail.com"],
            "gracias": ["¡De nada! 😊 ¿Hay algo más en lo que pueda ayudarte?", "¡Un placer! Si tienes más dudas, aquí estoy.", "¡Gracias a ti! ¿Necesitas algo más?"],
            "default": ["Interesante. ¿Podrías contarme más sobre lo que necesitas?", "No estoy seguro de entender. ¿Podrías reformular tu pregunta?"]
        };

        // Inicializar chat
        document.addEventListener('DOMContentLoaded', function() {
            initialMessages.forEach(message => {
                addMessage(message.text, message.sender, message.time);
            });
        });

        // Función para enviar mensaje
        function sendMessage() {
            const input = document.getElementById('messageInput');
            const message = input.value.trim();
            
            if (message === '') return;
            
            // Agregar mensaje del usuario
            addMessage(message, 'user', getCurrentTime());
            input.value = '';
            
            // Mostrar indicador de escritura
            showTypingIndicator();
            
            // Respuesta del bot después de un delay
            setTimeout(() => {
                hideTypingIndicator();
                const botResponse = getBotResponse(message);
                addMessage(botResponse, 'bot', getCurrentTime());
            }, 1500);
        }

        // Función para agregar mensaje al chat
        function addMessage(text, sender, time) {
            const chatMessages = document.getElementById('chatMessages');
            const messageDiv = document.createElement('div');
            messageDiv.className = `message ${sender}-message`;
            
            messageDiv.innerHTML = `
                <div>${text}</div>
                <div class="message-time">${time}</div>
            `;
            
            chatMessages.appendChild(messageDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }

        // Función para obtener respuesta del bot
        function getBotResponse(message) {
            const lowerMessage = message.toLowerCase();
            
            for (const [key, responses] of Object.entries(botResponses)) {
                if (lowerMessage.includes(key)) {
                    return responses[Math.floor(Math.random() * responses.length)];
                }
            }
            
            return botResponses.default[Math.floor(Math.random() * botResponses.default.length)];
        }

        // Función para opciones rápidas
        function selectOption(element) {
            const text = element.textContent;
            document.getElementById('messageInput').value = text;
            sendMessage();
        }

        // Función para manejar Enter
        function handleKeyPress(event) {
            if (event.key === 'Enter') {
                sendMessage();
            }
        }

        // Función para mostrar indicador de escritura
        function showTypingIndicator() {
            document.getElementById('typingIndicator').style.display = 'block';
            document.getElementById('chatMessages').scrollTop = document.getElementById('chatMessages').scrollHeight;
        }

        // Función para ocultar indicador de escritura
        function hideTypingIndicator() {
            document.getElementById('typingIndicator').style.display = 'none';
        }

        // Función para obtener hora actual
        function getCurrentTime() {
            const now = new Date();
            return `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`;
        }

        // Función para volver atrás
        function goBack() {
            window.history.back();
        }