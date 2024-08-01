document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('quantityForm');
    const responseMessage = document.getElementById('responseMessage');
    const backButton = document.getElementById('backButton'); // New Button

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const productId = document.getElementById('productId').value;
        const quantity = document.getElementById('quantity').value;

        try {
            const response = await fetch(`http://localhost:8899/product/api/v1.0/add/${productId}/${quantity}`, {
                method: 'PUT',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.ok) {
                responseMessage.textContent = 'Quantity added successfully!';
                responseMessage.className = 'response-message success';
            } else {
                responseMessage.textContent = 'Failed to add quantity. Please check the product ID.';
                responseMessage.className = 'response-message error';
            }
        } catch (error) {
            responseMessage.textContent = 'An error occurred. Please try again.';
            responseMessage.className = 'response-message error';
        }

        // Clear the response message after 5 seconds
        setTimeout(() => {
            responseMessage.textContent = '';
        }, 5000);
    });

    // Event listener for the Back button
    backButton.addEventListener('click', () => {
        window.location.href = 'inventory.html'; // Redirect to inventory page
    });
});
