// Función para mostrar un formulario y ocultar los demás
function mostrarFormulario(idFormulario) {
	// Ocultar todos los formularios
	document.querySelectorAll('.formulario').forEach(formulario => {
		formulario.style.display = 'none';
	});
	// Mostrar el formulario seleccionado
	document.getElementById(idFormulario).style.display = 'block';
}

// Función para ocultar el formulario de venta
function ocultarFormularioVenta() {
	// Ocultar el formulario de venta
	var formularioVenta = document.getElementById('formularioVenta');
	if (formularioVenta) {
		formularioVenta.style.display = 'none';
	}
}

// Evento de clic para el botón "Atrás" del formulario de venta
document.getElementById('btnAtrasVenta').addEventListener('click', function() {
	ocultarFormularioVenta();
});

// Función para eliminar un producto
function eliminarProducto() {
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

// Función para eliminar un cliente
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

// Función para eliminar un proveedor
function eliminaProveedor() {
	var idProveedor = document.getElementById("idProveedor").value;
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "/ClipMagico/prov?idProveedor=" + idProveedor, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// Manejar la respuesta si es necesaria
			console.log("Proveedor eliminado correctamente");
		}
	};
	xhr.send();
}


$('#getPriceBtn').click(function(event) {
    event.preventDefault(); // Evitar envío del formulario por defecto
    var productId = $('#productId').val();
    var quantity = $('#quantity').val();

    // Comprobar si los campos están vacíos
    if (!productId || !quantity) {
        alert('Por favor, complete todos los campos.');
        return; // Salir de la función si falta información
    }

    // Enviar solicitud GET al servlet
    $.get('http://localhost:8080/ClipMagico/prod?id=' + productId, function(data) {
        // Verificar si se recibió una respuesta válida
        if (data && data.hasOwnProperty('precio')) {
            // Mostrar el precio en un mensaje de alerta
            alert('El precio del producto es: ' + data.precio);

            // Calcular el total
            var total = parseFloat(data.precio) * parseInt(quantity);

            // Agregar una fila a la tabla con los resultados de la búsqueda
            addRowToTable(data, quantity, total);

            // Actualizar la suma total
            updateTotalSum();
        } else {
            alert('No se pudo obtener el precio del producto.');
        }

        // Limpiar los campos del formulario después de la operación
        $('#productId').val('');
        $('#quantity').val('');
    }).fail(function() {
        alert('Error al obtener el precio del producto. Por favor, inténtelo de nuevo más tarde.');
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
    newRow += '<td>' + total.toFixed(2) + '</td>'; // Asegurarse de que el total tenga dos decimales
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

