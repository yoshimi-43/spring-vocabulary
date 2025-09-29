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
					<td><button>追加</button></td>
				`;
				productList.appendChild(row);
			})
		})
		.catch(error => {
			console.log(error.message);
			window.location.href = '/error.html';
		})
});