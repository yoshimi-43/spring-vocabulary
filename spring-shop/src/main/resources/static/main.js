addEventListener("DOMContentLoaded", () => {
	fetch('/product')
	.then(response => {
		if(!response.ok){
			return response.json()
			.then(errorBody => {
				throw new Error(errorBody.detail);
			});
		}
		return response.json()
	})
	.then(body => {
		const productList = document.getElementById('product-list');
		body.forEach(product => {
			const row = document.createElement('tr')
			row.innerHTML = `
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td><button onclick=addToCart(${product.id})>追加</button></td>
			`;
			productList.appendChild(row);
		})
	})
	.catch(error => {
		window.location.href = '/error.html';
		console.log(error);
		
	})
		
	getAllCart();
});

function addToCart(productId){
	fetch(`/cart/${productId}`, {
		method: 'POST'
	})
	.then(response => {
		if(!response.ok){
			return response.json()
			.then(errorBody => {
				throw new Error(errorBody.detail);
			});
		}
	})
	.then(() => {
		getAllCart();
	})
	.catch(error => {
		console.log(error.message);
		window.location.href = '/error.html';
	})

}

function updateCart(cartId, quantity){
	fetch(`/cart`, {
		method: 'PUT',
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({ id: cartId, quantity: quantity})
	})
	.then(response => {
		if(!response.ok){
			return response.json()
			.then(errorBody => {
				throw new Error(errorBody.detail);
			});
		}
	})
	.then(() => {
		getAllCart();
	})
	.catch(error => {
		window.location.href = '/error.html';
		console.log(error);
	})
}

function getAllCart(){
	fetch('/cart')
	.then(response => {
		if(!response.ok){
			return response.json()
			.then(errorBody => {
				throw new Error(errorBody.detail);
			});
		}
		return response.json()
	})
	.then(body => {
		const productList = document.getElementById('cart-list');
		productList.innerHTML = '';
		body.forEach(cart => {
			const row = document.createElement('tr')
			row.innerHTML = `
				<td>${cart.product.name}</td>
				<td>
					<input type="number" min="1"
					value="${cart.quantity}"
					onchange="updateCart(${cart.id}, ${cart.quantity})"
					/>
				</td>
				<td>${cart.product.price * cart.quantity}</td>
			`;
			productList.appendChild(row);
		})
	})
	.catch(error => {
		window.location.href = '/error.html';
		console.log(error);
	})
}