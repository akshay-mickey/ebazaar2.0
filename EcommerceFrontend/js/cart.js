
document.addEventListener('DOMContentLoaded', async function() {
    const user = JSON.parse(localStorage.getItem("userId"));
    const userId = user.userId;
    if (!userId) {
        alert('User not logged in');
        return;
    }

    const cartItemsContainer = document.getElementById('cart-items');
    const totalPriceElement = document.getElementById('total-price');

    async function fetchCartItems() {
        try {
            const response = await fetch(`http://localhost:8088/api/cart/${userId}`);
            const cartItems = await response.json();
            displayCartItems(cartItems);
            updateTotalPrice(cartItems);
        } catch (error) {
            console.error('Error fetching cart items:', error);
        }
    }

    function displayCartItems(cartItems) {
        cartItemsContainer.innerHTML = cartItems.map(item => `
            <div class="cart-item" data-id="${item.id}">
                <img src="${item.product.imageUrl}" alt="${item.product.name}">
                <h2>${item.product.name}</h2>
                <p>${item.product.description}</p>
                <p>Price: $<span class="item-price">${item.price.toFixed(2)}</span></p>
                <input type="number" class="item-quantity" min="1" value="${item.quantity}" data-product-id="${item.product.id}">
                <button class="remove-item" data-id="${item.id}">Remove</button>
            </div>
        `).join('');

        document.querySelectorAll('.item-quantity').forEach(input => {
            input.addEventListener('change', updateQuantity);
        });

        document.querySelectorAll('.remove-item').forEach(button => {
            button.addEventListener('click', removeFromCart);
        });
    }

    function updateTotalPrice(cartItems) {
        const totalPrice = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
        totalPriceElement.textContent = totalPrice.toFixed(2);
    }

    async function updateQuantity(event) {
        const input = event.target;
        const cartItemId = input.closest('.cart-item').dataset.id;
        const newQuantity = parseInt(input.value);
        const productId = input.dataset.productId;

        try {
            const response = await fetch(`http://localhost:8088/api/cart/update/${cartItemId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ quantity: newQuantity,
                    userId: userId,
                    productId: productId
     })
            });

            if (response.ok) {
                const cartItems = await response.json();
                displayCartItems(cartItems);
                updateTotalPrice(cartItems);
            } else {
                const message = await response.text();
                alert('Failed to update quantity: ' + message);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred');
        }
    }

    async function removeFromCart(event) {
        const button = event.target;
        const cartItemId = button.dataset.id;

        try {
            const response = await fetch(`http://localhost:8088/api/cart/remove/${cartItemId}/${userId}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                // const cartItems = await response.json();
                // displayCartItems(cartItems);
                // updateTotalPrice(cartItems);
                window.location.reload()
            } else {
                const message = await response.text();
                alert('Failed to remove item: ' + message);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred');
        }
    }

    fetchCartItems();
});