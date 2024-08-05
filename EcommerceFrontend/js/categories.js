document.addEventListener('DOMContentLoaded', async function() {
    console.log('DOM fully loaded and parsed');

    const productsContainer = document.getElementById('products');
    if (productsContainer) {
        const urlParams = new URLSearchParams(window.location.search);
        const categoryId = urlParams.get('id');
        
        if (!categoryId) {
            console.error('No category ID provided');
            return;
        }
        
        try {
            const response = await fetch(`http://localhost:8088/api/categories/${categoryId}/products`);
            const products = await response.json();
            
            productsContainer.innerHTML = products.map(product => `
                <div class="product">
                    <img src="${product.imageUrl}" alt="${product.name}">
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <p>Price: $${product.price.toFixed(2)}</p>
                    <input type="number" id="quantity-${product.id}" min="1" value="1">
                    <button onclick="addToCart(${product.id})">Add to Cart</button>
                    <button onclick="addToWishlist(${product.id})">Add to Wishlist</button>
                </div>
            `).join('');
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    }
});

async function addToCart(productId) {
    const quantity = document.getElementById(`quantity-${productId}`).value;
    const user = JSON.parse(localStorage.getItem("userId"));
    const userId = user.userId;
    console.log(userId);


    if (!userId) {
        alert('User not logged in');
        return;
    }

    try {
        const productResponse = await fetch(`http://localhost:8088/api/products/${productId}`);
        if (!productResponse.ok) {
            throw new Error('Product not found');
        }

        const product = await productResponse.json();
        const price = product.price; 
        const response = await fetch(`http://localhost:8088/api/cart/add/${userId}/${productId}/${quantity}/${price}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        
        if (response.ok) {
            alert('Product added to cart');
        } else {
            const message = await response.text();
            alert('Failed to add to cart: ' + message);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred');
    }
}

