window.addEventListener("DOMContentLoaded", async function() {
	const respuesta = await fetch('http://localhost:8080/servidorrest/contactos/');
	const contactos = await respuesta.json();
	
	const ul = document.querySelector('ul');
	
	contactos.forEach(contacto => {
		const li = document.createElement('li');
		li.innerHTML = `${contacto.id}, ${contacto.nombre}, ${contacto.apellidos}, ${contacto.fecha_nacimiento}`;
		ul.appendChild(li);
	})
});