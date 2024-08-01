document.addEventListener('DOMContentLoaded', function() {
    const fetchButton = document.getElementById('fetchButton');
    const addToInventoryButton = document.getElementById('addToInventoryButton');
    const productIdInput = document.getElementById('productIdInput');
    const productDetailsContainer = document.getElementById('productDetails');
    const loadingIndicator = document.getElementById('loadingIndicator');
    const errorMessage = document.getElementById('errorMessage');

    fetchButton.addEventListener('click', function() {
        const productId = productIdInput.value.trim();

        if (productId === '') {
            displayErrorMessage('Please enter a valid Product ID');
            return;
        }

        showLoadingIndicator();

        fetch(`http://localhost:8899/product/api/v1.0/details/${productId}`, {
            method: 'GET',
            mode: 'cors'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            displayProductDetails(data);
            hideLoadingIndicator();
            clearErrorMessage();
        })
        .catch(error => {
            console.error('Error fetching product details:', error);
            displayErrorMessage('Error fetching details. Please try again.');
            hideLoadingIndicator();
        });
    });

    addToInventoryButton.addEventListener('click', function() {
        const productId = productIdInput.value.trim();
        if (productId === '') {
            displayErrorMessage('Please enter a valid Product ID before navigating.');
            return;
        }

        window.location.href = `addToInventory.html?productId=${productId}`;
    });

    function displayProductDetails(product) {
        productDetailsContainer.innerHTML = `
            <p><strong>Product Name:</strong> ${product.productName}</p>
            <p><strong>Description:</strong> ${product.productDescription}</p>
            <p><strong>Price:</strong> ${product.productPrice}</p>
            <p><strong>Quantity:</strong> ${product.productQuantity}</p>
            <p><strong>Category:</strong> ${product.category}</p>
            <p><strong>Brand:</strong> ${product.productBrand}</p>
            <p><strong>Image URL:</strong> ${product.productImage}</p>
            <p><strong>Specification:</strong> ${product.productSpecification}</p>
        `;

        productDetailsContainer.style.display = 'block';

        // Check for low stock quantity
        if (product.productQuantity < 100) { // Alert if quantity is less than 100
            alert('Your stock is low, please add more.');
        }
    }

    function displayErrorMessage(message) {
        errorMessage.textContent = message;
        errorMessage.style.display = 'block';
    }

    function clearErrorMessage() {
        errorMessage.textContent = '';
        errorMessage.style.display = 'none';
    }

    function showLoadingIndicator() {
        loadingIndicator.style.display = 'block';
    }

    function hideLoadingIndicator() {
        loadingIndicator.style.display = 'none';
    }
});
