document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM fully loaded and parsed');

    // Handle registration
    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', async function(event) {
            event.preventDefault();
            console.log('Register form submitted');

            const email = document.getElementById('register-email').value;
            const password = document.getElementById('register-password').value;

            console.log('Register email:',email);
            console.log('Register password:',password);
            const loginData = {
                email: email,
                password: password
            };

            try {
                const response = await fetch(`http://localhost:8088/api/users/register`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(loginData)
                });

                if (response.ok) {
                    alert('Registration successful');
                    window.location.href = 'login.html';
                } else {
                    const message = await response.text();
                    alert('Registration failed: ' + message);
                }
            } catch (error) {
                console.error('Error:', error);
                alert('An error occurred');
            }
        });
    }

    // Handle login
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', async function(event) {
            event.preventDefault();
            console.log('Login form submitted');

            const email = document.getElementById('login-email').value;
            const password = document.getElementById('login-password').value;

            console.log('Login email:', email);
            console.log('Login password:', password);

            try {
                const response = await fetch('http://localhost:8088/api/users/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ email, password })
                });

                if (response.ok) {
                    // Assuming the user ID is sent in the response body
                    const userId = await response.text(); // or response.json() depending on the API response format
                    localStorage.setItem('userId', userId); // Store user ID in local storage
                    alert('Login successful');
                    window.location.href = 'index.html'; // Redirect to home page or dashboard
                } else {
                    const message = await response.text();
                    alert('Login failed: ' + message);
                }
            } catch (error) {
                console.error('Error:', error);
                alert('An error occurred');
            }
        });
    }
});