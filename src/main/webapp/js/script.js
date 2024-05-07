// Obtener los elementos del DOM
const btnRegistrar = document.getElementById('btnRegistrar');
const btnArqueo = document.getElementById('btnArqueo');
const btnAtras = document.getElementById('btnAtras');
const contenidoNormal = document.getElementById('contenidoNormal');
const formularioVenta = document.getElementById('formularioVenta');

// Agregar eventos a los botones
btnRegistrar.addEventListener('click', mostrarFormularioVenta);
btnArqueo.addEventListener('click', mostrarFormularioArqueo);
btnAtras.addEventListener('click', mostrarContenidoNormal);

// Funciones para mostrar y ocultar elementos
function mostrarFormularioVenta() {
  contenidoNormal.style.display = 'none';
  formularioVenta.style.display = 'block';
}

function mostrarFormularioArqueo() {
  // Aquí puedes implementar la lógica para mostrar el formulario de arqueo
}

function mostrarContenidoNormal() {
  contenidoNormal.style.display = 'flex';
  formularioVenta.style.display = 'none';
}
