document.addEventListener('DOMContentLoaded', async function() {
    // For index.html
    if (document.getElementById('categories')) {
        try {
            const response = await fetch('http://localhost:8088/api/categories');
            const categories = await response.json();
            
            const categoriesContainer = document.getElementById('categories');
            categoriesContainer.innerHTML = categories.map(category => `
                <div class="category-item">
                    <a href="categories.html?id=${category.id}">
                        <img src="${category.imageUrl}" alt="${category.name}">
                        <h2>${category.name}</h2>
                    </a>
                </div>
            `).join('');
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
}})