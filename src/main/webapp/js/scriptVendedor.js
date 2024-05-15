$(document).ready(function() {
	// Mostrar el formulario de venta cuando se hace clic en el botón Registrar
	$("#btnRegistrar").click(function() {
		$("#formularioVenta").show();
		$("#formularioEliminarCliente").hide();
		$("#formularioAgregarCliente").hide();
	});

	// Mostrar el formulario de eliminar cliente cuando se hace clic en el botón Eliminar Cliente
	$("#btnEliminarCliente").click(function() {
		$("#formularioVenta").hide();
		$("#formularioEliminarCliente").show();
		$("#formularioAgregarCliente").hide();
	});

	// Mostrar el formulario de agregar cliente cuando se hace clic en el botón Crear Cliente
	$("#btnCrearCliente").click(function() {
		$("#formularioVenta").hide();
		$("#formularioEliminarCliente").hide();
		$("#formularioAgregarCliente").show();
	});

	// Regresar al contenido normal cuando se hace clic en el botón Atrás en el formulario de venta
	$("#btnAtrasVenta").click(function() {
		$("#formularioVenta").hide();
	});

	// Regresar al contenido normal cuando se hace clic en el botón Atrás en el formulario de eliminar cliente
	$("#btnAtrasEliminarCliente").click(function() {
		$("#formularioEliminarCliente").hide();
	});

	// Regresar al contenido normal cuando se hace clic en el botón Atrás en el formulario de agregar cliente
	$("#btnAtrasAgregarCliente").click(function() {
		$("#formularioAgregarCliente").hide();
	});
});


$('#getPriceBtn').click(function(event) {
	event.preventDefault(); // Prevent default form submission
	var productId = $('#productId').val();
	var quantity = $('#quantity').val();

	// Send GET request to servlet
	$.get('http://localhost:8080/ClipMagico/prod?id=' + productId, function(data) {
		// Show the price in an alert
		alert('El precio del producto es: ' + data.precio);

		// Log the object to console
		console.log(data);

		// Calculate total
		var total = data.precio * quantity;

		// Add row to the table with search result
		addRowToTable(data, quantity, total);

		// Update total sum
		updateTotalSum();

	});
});

function addRowToTable(producto, quantity, total) {
	var newRow = '<tr>';
	newRow += '<td>' + producto.idProducto + '</td>';
	newRow += '<td>' + producto.nombre + '</td>';
	newRow += '<td>' + producto.descripcion + '</td>';
	newRow += '<td>' + producto.idProveedor + '</td>';
	newRow += '<td>' + producto.precio + '</td>';
	newRow += '<td>' + quantity + '</td>';
	newRow += '<td>' + total + '</td>';
	newRow += '<td><button class="btn btn-danger btn-sm btnEliminar">Eliminar</button></td>'; // Botón eliminar
	newRow += '</tr>';

	$('#productTable tbody').append(newRow);
}

$(document).on('click', '.btnEliminar', function() {
	$(this).closest('tr').remove(); // Eliminar la fila actual
	updateTotalSum(); // Actualizar la suma total
});

function updateTotalSum() {
	var totalSum = 0;
	$('#productTable tbody tr').each(function() {
		var totalCell = $(this).find('td:nth-last-child(2)').text(); // Seleccionar la penúltima celda que contiene el total
		var totalValue = parseFloat(totalCell);
		if (!isNaN(totalValue)) { // Verificar si es un número válido
			totalSum += totalValue;
		}
	});
	$('#totalSum').text('Suma Total: $' + totalSum.toFixed(2));
	$('#totalVenta').val(totalSum.toFixed(2)); // Actualizar el campo de entrada oculto para el monto total
}

function eliminaCliente() {
	var cedula = document.getElementById("cedula").value;
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "/ClipMagico/cliente?cedula=" + cedula, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// Manejar la respuesta si es necesaria
			console.log("Cliente eliminado correctamente");
		}
	};
	xhr.send();
}

// Manejador de eventos para el botón de Arqueo
$('#btnArqueo').click(function() {
    alert('Función no disponible por el momento.');
});
