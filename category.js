function applyFilters() {
    const searchBar = document.getElementById('searchBar').value;
    const category = document.getElementById('category').value;
    const brand = document.getElementById('brand').value;
    let minPrice = parseFloat(document.getElementById('minPrice').value);
    let maxPrice = parseFloat(document.getElementById('maxPrice').value);
    const sort = document.getElementById('sort').value;

    // Validate minPrice and maxPrice
    if (isNaN(minPrice) || minPrice < 0) {
        minPrice = '';
    }
    if (isNaN(maxPrice) || maxPrice < 0) {
        maxPrice = '';
    }

    // Validate that minPrice is less than or equal to maxPrice
    if (minPrice !== '' && maxPrice !== '' && minPrice > maxPrice) {
        alert('Minimum price cannot be greater than maximum price.');
        return;
    }

    // Construct query parameters
    let queryParams = `?search=${encodeURIComponent(searchBar)}&category=${encodeURIComponent(category)}&brand=${encodeURIComponent(brand)}&minPrice=${encodeURIComponent(minPrice)}&maxPrice=${encodeURIComponent(maxPrice)}&sort=${encodeURIComponent(sort)}`;

    // Make an API request
    fetch(`http://localhost:8899/product/api/v1.0${queryParams}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
}

function displayResults(products) {
    const resultsContainer = document.getElementById('results');
    resultsContainer.innerHTML = ''; // Clear previous results

    if (products.length === 0) {
        resultsContainer.innerHTML = '<p>No products found.</p>';
        return;
    }

    products.forEach(product => {
        const resultItem = document.createElement('div');
        resultItem.classList.add('result-item');

        resultItem.innerHTML = `
            <img src="${product.productImage}" alt="${product.productName}">
            <div class="info">
                <h3>${product.productName}</h3>
                <p><strong>Category:</strong> ${product.category}</p>
                <p><strong>Brand:</strong> ${product.productBrand}</p>
                <p><strong>Price:</strong> $${product.productPrice}</p>
                <p>${product.productDescription}</p>
            </div>
        `;

        resultsContainer.appendChild(resultItem);
    });
}
