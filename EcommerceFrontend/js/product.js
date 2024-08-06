document.addEventListener("DOMContentLoaded", () => {
  const productList = document.getElementById("product-list");
  const createProductForm = document.getElementById("create-product-form");
  const updateProductDiv = document.getElementById("update-product");
  const updateProductForm = document.getElementById("update-product-form");

  // Load products
  async function loadProducts() {
    try {
      const response = await fetch("http://localhost:8088/api/products/");
      const products = await response.json();
      console.log(products)
      productList.innerHTML = products
        .map(
          (prod) => `
                <li>
                    <span>${prod.name} - $${prod.price.toFixed(2)} - ${
            prod.quantity
          } in stock - ${prod.brand}</span>
                    <button onclick="editProduct(${prod.id}, '${prod.name}', ${
            prod.price
          }, '${prod.description}', '${prod.imageUrl}', ${prod.quantity}, '${
            prod.brand
          }', ${prod.category})">Edit</button>
                    <button onclick="deleteProduct(${prod.id})">Delete</button>
                </li>
            `
        )
        .join("");
    } catch (error) {
      console.error("Error loading products:", error);
    }
  }

  // Create product
  createProductForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const name = document.getElementById("product-name").value;
    const price = parseFloat(document.getElementById("product-price").value);
    const description = document.getElementById("product-description").value;
    const imageUrl = document.getElementById("product-image-url").value;
    const quantity = parseInt(
      document.getElementById("product-quantity").value
    );
    const brand = document.getElementById("product-brand").value;
    const categoryId = parseInt(
      document.getElementById("productCategory").value
    );

    try {
      const response = await fetch(
        `http://localhost:8088/api/products/${categoryId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name,
            price,
            description,
            imageUrl,
            quantity,
            brand,
          }),
        }
      );

      if (response.ok) {
        alert("Product created successfully");
        loadProducts();
      } else {
        alert("Failed to create product");
      }
    } catch (error) {
      console.error("Error creating product:", error);
    }
  });

  // Delete product
  window.deleteProduct = async (id) => {
    try {
      const response = await fetch(`http://localhost:8088/api/products/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("Product deleted successfully");
        loadProducts();
      } else {
        alert("Failed to delete product");
      }
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  async function loadCategories() {
    try {
      const response = await fetch("http://localhost:8088/api/categories"); // Adjust URL if necessary
      const categories = await response.json();

      productCategory.innerHTML = categories
        .map((cat) => `<option value="${cat.id}">${cat.name}</option>`)
        .join("");

        updateProductCategory.innerHTML = categories
        .map((cat) => `<option value="${cat.id}">${cat.name}</option>`)
        .join("");
    //   createProductCategory.innerHTML = options;
    //   updateProductCategory.innerHTML = options;
    } catch (error) {
      console.error("Error loading categories:", error);
    }
  }


  
  // Edit product
  
  window.editProduct = (
    id,
    name,
    price,
    description,
    imageUrl,
    quantity,
    brand,
    category // Assuming this is a Category object
  ) => {
    document.getElementById('update-product-id').value = id;
    document.getElementById('update-product-name').value = name;
    document.getElementById('update-product-price').value = price;
    document.getElementById('update-product-description').value = description;
    document.getElementById('update-product-image-url').value = imageUrl;
    document.getElementById('update-product-quantity').value = quantity;
    document.getElementById('update-product-brand').value = brand;
    console.log(id)
    // Assuming 'category' is an object with 'id' and 'name' properties
    const categoryId = category.id;

    // Populate the category dropdown/select element
    const categorySelect = document.getElementById('update-product-category');

    // Clear previous options
    categorySelect.innerHTML = '';

    // Fetch categories or use a pre-existing list
    fetch(`http://localhost:8088/api/categories`)
      .then(response => response.json())
      .then(data => {
        // Loop through categories and add them as options
        data.forEach(cat => {
          const option = document.createElement('option');
          option.value = cat.id;
          option.textContent = cat.name;
          if (cat.id === categoryId) {
            option.selected = true;
          }
          categorySelect.appendChild(option);
        });
      })
      .catch(error => {
        console.error('Error fetching categories:', error);
      });

    // Show the update product form
    updateProductDiv.style.display = 'block';
  };

  // Update product
  updateProductForm.addEventListener('submit', async event => {
    event.preventDefault();
    const id = document.getElementById('update-product-id').value;
    const name = document.getElementById('update-product-name').value;
    const price = parseFloat(
      document.getElementById('update-product-price').value
    );
    const description = document.getElementById(
      'update-product-description'
    ).value;
    const imageUrl = document.getElementById('update-product-image-url').value;
    const quantity = parseInt(
      document.getElementById('update-product-quantity').value
    );
    const brand = document.getElementById('update-product-brand').value;
    const categoryId = parseInt(
      document.getElementById('update-product-category').value
    ); // Assuming categoryId is now an ID, not an object

    try {
      const response = await fetch(`http://localhost:8088/api/products/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name,
          price,
          description,
          imageUrl,
          quantity,
          brand,
          categoryId // Ensure categoryId is correctly sent in the body
        }),
      });

      if (response.ok) {
        alert('Product updated successfully');
        updateProductDiv.style.display = 'none';
        loadProducts(); // You might define this function to reload your product list
      } else {
        alert('Failed to update product');
      }
    } catch (error) {
      console.error('Error updating product:', error);
    }
  });
  loadCategories();
  loadProducts();


});



