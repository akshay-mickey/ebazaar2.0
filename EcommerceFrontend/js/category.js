document.addEventListener('DOMContentLoaded', () => {
    const categoryList = document.getElementById('category-list');
    const createCategoryForm = document.getElementById('create-category-form');
    const updateCategoryDiv = document.getElementById('update-category');
    const updateCategoryForm = document.getElementById('update-category-form');

    // Load categories
    async function loadCategories() {
        try {
            const response = await fetch('http://localhost:8088/api/categories');
            const categories = await response.json();
            categoryList.innerHTML = categories.map(cat => `
                <li>
                    <span>${cat.name}</span>
                    <button onclick="editCategory(${cat.id}, '${cat.name}', '${cat.imageUrl}')">Edit</button>
                    <button onclick="deleteCategory(${cat.id})">Delete</button>
                </li>
            `).join('');
        } catch (error) {
            console.error('Error loading categories:', error);
        }
    }

    // Create category
    createCategoryForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const name = document.getElementById('category-name').value;
        const imageUrl = document.getElementById('category-image-url').value;

        try {
            const response = await fetch(`http://localhost:8088/api/categories/${name}/${imageUrl}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, imageUrl })
            });

            if (response.ok) {
                alert('Category created successfully');
                loadCategories();
            } else {
                alert('Failed to create category');
            }
        } catch (error) {
            console.error('Error creating category:', error);
        }
    });

    // Update category
    updateCategoryForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const id = document.getElementById('update-category-id').value;
        const name = document.getElementById('update-category-name').value;
        const imageUrl = document.getElementById('update-category-image-url').value;

        try {
            const response = await fetch(`http://localhost:8088/api/categories/${id}/${name}/${imageUrl}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, imageUrl })
            });

            if (response.ok) {
                alert('Category updated successfully');
                updateCategoryDiv.style.display = 'none';
                loadCategories();
            } else {
                alert('Failed to update category');
            }
        } catch (error) {
            console.error('Error updating category:', error);
        }
    });

    // Edit category
    window.editCategory = (id, name, imageUrl) => {
        document.getElementById('update-category-id').value = id;
        document.getElementById('update-category-name').value = name;
        document.getElementById('update-category-image-url').value = imageUrl;
        updateCategoryDiv.style.display = 'block';
    }

    // Delete category
    window.deleteCategory = async (id) => {
        try {
            const response = await fetch(`http://localhost:8088/api/categories/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Category deleted successfully');
                loadCategories();
            } else {
                alert('Failed to delete category');
            }
        } catch (error) {
            console.error('Error deleting category:', error);
        }
    }

    loadCategories();
});