 // Efecto de desplazamiento suave para los enlaces
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                document.querySelector(this.getAttribute('href')).scrollIntoView({
                    behavior: 'smooth'
                });
            });
        });
        
        // Efecto de aparición al hacer scroll
        const observerOptions = {
            threshold: 0.1,
            rootMargin: '0px 0px -50px 0px'
        };
        
        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.style.opacity = '1';
                    entry.target.style.transform = 'translateY(0)';
                }
            });
        }, observerOptions);
        
        // Aplicar efecto a las secciones
        document.querySelectorAll('.section').forEach(section => {
            section.style.opacity = '0';
            section.style.transform = 'translateY(20px)';
            section.style.transition = 'opacity 0.5s, transform 0.5s';
            observer.observe(section);
        });
        // Mostrar mensaje al cargar la página
        document.addEventListener('DOMContentLoaded', function() {
            setTimeout(function() {
                showMessage();
            }, 1000); // Espera 1 segundo después de cargar la página
        });

        function showMessage() {
            const message = document.getElementById('chatbotMessage');
            message.classList.add('show');
            
            // Ocultar automáticamente después de 8 segundos
            setTimeout(function() {
                if (message.classList.contains('show')) {
                    hideMessage();
                }
            }, 8000);
        }

        function hideMessage() {
            const message = document.getElementById('chatbotMessage');
            message.classList.remove('show');
        }

        function toggleChat() {
            const message = document.getElementById('chatbotMessage');
            
            if (message.classList.contains('show')) {
                hideMessage();
            } else {
                showMessage();
            }
        }

        // Ocultar mensaje al hacer clic fuera de él
        document.addEventListener('click', function(event) {
            const message = document.getElementById('chatbotMessage');
            const chatbotBtn = document.querySelector('.chatbot-btn');
            const closeBtn = document.querySelector('.close-message');
            
            if (!message.contains(event.target) && 
                !chatbotBtn.contains(event.target) && 
                !closeBtn.contains(event.target) &&
                message.classList.contains('show')) {
                hideMessage();
            }
        });
        function irABot() {
            window.location.href = 'bot.html';
        };