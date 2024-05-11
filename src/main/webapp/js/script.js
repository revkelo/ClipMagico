// Obtener los elementos del DOM
const btnRegistrar = document.getElementById('btnRegistrar');
const btnArqueo = document.getElementById('btnArqueo');
const btnCrearCliente = document.getElementById('btnCrearCliente');
const btnAtrasVenta = document.getElementById('btnAtrasVenta');
const btnAtrasCliente = document.getElementById('btnAtrasCliente');
const contenidoNormal = document.getElementById('contenidoNormal');
const formularioVenta = document.getElementById('formularioVenta');
const formularioCliente = document.getElementById('formularioCliente');
//const btnMostrar = document.getElementById('mostrar');

// Agregar eventos a los botones
btnRegistrar.addEventListener('click', mostrarFormularioVenta);
btnArqueo.addEventListener('click', mostrarFormularioArqueo);
btnCrearCliente.addEventListener('click', mostrarFormularioCliente);
btnAtrasVenta.addEventListener('click', mostrarContenidoNormal);
btnAtrasCliente.addEventListener('click', mostrarContenidoNormal);
//btnMostrar.addEventListener('click', mostrarFormularioCliente); // Nuevo evento agregado

// Funciones para mostrar y ocultar elementos
function mostrarFormularioVenta() {
  contenidoNormal.style.display = 'none';
  formularioVenta.style.display = 'block';
}

function mostrarFormularioArqueo() {
  // Implementa la lógica para mostrar el formulario de arqueo
}

function mostrarFormularioCliente() {
  contenidoNormal.style.display = 'none';
  formularioCliente.style.display = 'block';
}

//function mostrarContenidoNormal() {
////  contenidoNormal.style.display = 'flex';
 // formularioVenta.style.display = 'none';
 // formularioCliente.style.display = 'none';
//}
