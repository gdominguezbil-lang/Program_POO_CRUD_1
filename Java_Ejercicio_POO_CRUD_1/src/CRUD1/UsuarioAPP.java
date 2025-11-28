package CRUD1;

import java.io.*;
import java.util.*;

public class UsuarioAPP {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String linea = "";
		String[] cargas;
		int cantidadUsuarios = 0;

		ArrayList<Usuario> usuarioList = new ArrayList<Usuario>();
		int numeritos;

		File file = new File("./src/usuarios.txt");

		try {
			Scanner scReader = new Scanner(file);

			while (scReader.hasNextLine()) {
				linea = scReader.nextLine();
				Usuario usuario = new Usuario();
				cargas = linea.split(":");
				usuario.setId(Integer.parseInt(cargas[0]));
				usuario.setNombre(cargas[1]);
				usuario.setApellido(cargas[2]);
				usuario.setContras(cargas[3]);
				if (cargas.length == 5) {
					usuario.setNombreUsuario(cargas[1]);
				} else if (cargas.length == 6) {
					usuario.setNombreUsuario(cargas[5]);
				}
				if (Integer.parseInt(cargas[4]) == 1) {
					usuario.setActivo(true);
				} else if (Integer.parseInt(cargas[4]) == 0) {
					usuario.setActivo(false);
				}
				usuarioList.add(usuario);
				cantidadUsuarios++;
				System.out.println(usuario.toString());
			}
			System.out.println("Se han cargado " + cantidadUsuarios + " usuarios...");
			scReader.close();

			do {
				System.out.println(
						"====MENÚ====\n[1] = Mostrar tu usuario\n[2] = Mostrar todos los usuarios\n[3] = Borrar usuario\n[4] = Crear usuario nuevo\n[5] = "
								+ "Crear un usuario con contraseña y activo por defecto\n[6] = Editar el nombreUsuario y contraseña\n[7] = Guardar\n[8] = Salir");
				numeritos = Integer.parseInt(sc.nextLine());

				switch (numeritos) {

				case 1:
					mostrarUsuario(usuarioList, sc);
					break;

				case 2:
					mostrarTodos(usuarioList, sc);
					break;

				case 3:
					borrarUsuario(usuarioList, sc);
					break;

				case 4:
					nuevoUsuario(usuarioList, sc);
					break;

				case 5:
					nuevoUsuarioDefecto(usuarioList, sc);
					break;

				case 6:
					editarContrayUsuario(usuarioList, sc);
					break;

				case 7:
					guardarCambios(usuarioList, sc, file);
					break;

				case 8:
					System.out.println("Fin del programa...");
					break;

				default:
					System.out.println("[!] ERROR: Introduzca una opción correcta");
				}
			} while (numeritos != 8);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("[!] ERROR: El archivo no se encuentra");
		}
	}

	public static void mostrarUsuario(ArrayList<Usuario> usuarioList, Scanner sc) {
		String introd = "";
		boolean valid = false;
		System.out.println("Introduzca su nombre: ");
		introd = sc.nextLine();
		for (int i = 0; i < usuarioList.size(); i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				valid = true;
				if (usuarioList.get(i).isActivo() == true) {
					do {
						System.out.println("Introduce la contraseña: ");
						introd = sc.nextLine();
						if (introd.equals(usuarioList.get(i).getContras())) {
							break;
						}
						System.out.println("[!] ERROR: Contrasña incorrecta\n");
					} while (!introd.equals(usuarioList.get(i).getContras()));

					System.out.println("Nombre de usuario: " + usuarioList.get(i).getNombreUsuario());
					if (usuarioList.get(i).isActivo() == true) {
						System.out
								.println("¡Bienvenido! " + usuarioList.get(i).getNombre() + ", estos son tus datos:\n");
						System.out.println("ID: " + usuarioList.get(i).getId());
						System.out.println("Nombre y Apellidos: " + usuarioList.get(i).getNombre() + " "
								+ usuarioList.get(i).getApellido());
					} else if (usuarioList.get(i).isActivo() == false) {
						System.out.println("[?] AVISO: Esta cuenta esá desactivada...");
					}
				} else if (usuarioList.get(i).isActivo() == false) {
					System.out.println("[?] AVISO: Esta cuenta esá desactivada...");
				}
			}
		}
		if (valid == false) {
			System.out.println("[!] ERROR: No existe ese usuario...");
		}
	}

	public static void mostrarTodos(ArrayList<Usuario> usuarioList, Scanner sc) {
		for (int i = 0; i < usuarioList.size(); i++) {
			System.out.println(usuarioList.get(i).toString());
		}
	}

	public static void borrarUsuario(ArrayList<Usuario> usuarioList, Scanner sc) {
		String introd = "";
		boolean valid = false;
		char sOn;
		System.out.println("Introduzca el nombre de usuario que quiera eliminar: ");
		introd = sc.nextLine();

		for (int i = 0; i < usuarioList.size(); i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				valid = true;
				System.out.println("Seguro que quiere eliminar el usuario? (s/n)");
				sOn = sc.nextLine().charAt(0);
				if (sOn == 's' || sOn == 'n' || sOn == 'S' || sOn == 'N') {
					if (sOn == 's' || sOn == 'S') {
						usuarioList.remove(i);
						System.out.println("[?] AVISO: Eliminando la cuenta...");
						break;
					} else {
						System.out.println("[?] AVISO: Abortando el borrado de usuario...");
						break;
					}
				}
			}
		}
		if (valid == false) {
			System.out.println("[!] ERROR: Usuario no encontrado.");
		}
	}

	public static void nuevoUsuario(ArrayList<Usuario> usuarioList, Scanner sc) {
		String introd = "";
		int introdint;
		boolean valid = false;
		System.out.println("¡Bienvenido al gestor de usuarios!");
		Usuario nuevoUsuario = new Usuario();
		System.out.println("Introduce tu ID: ");
		introdint = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < usuarioList.size(); i++) {
			if (introdint == usuarioList.get(i).getId()) {
				valid = true;
			}
		}
		System.out.println("Introduce tu nombre: ");
		introd = sc.nextLine();
		for (int i = 0; i < usuarioList.size(); i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				valid = true;
			}
		}
		if (valid == true) {
			System.out.println("[!] ERROR : No pueden haber dos nombres o dos IDs idénticas...");
		} else {
			nuevoUsuario.setId(introdint);
			nuevoUsuario.setNombre(introd);

			System.out.println("Introduce tu primer apellido: ");
			nuevoUsuario.setApellido(sc.nextLine());
			nuevoUsuario.setNombre(sc.nextLine());
			System.out.println("Introduce tu nombre de usuario");
			nuevoUsuario.setNombreUsuario(sc.nextLine());
			System.out.println("Introduce tu contraseña: ");
			nuevoUsuario.setContras(sc.nextLine());
			nuevoUsuario.setActivo(true);

			usuarioList.add(nuevoUsuario);
			System.out.println("[?] AVISO: Nuevo usuario creado: " + nuevoUsuario.getNombre());
			System.out.println(nuevoUsuario.toString());
		}
	}

	public static void nuevoUsuarioDefecto(ArrayList<Usuario> usuarioList, Scanner sc) {
		String nombre = "";
		String apellido = "";
		boolean valid = true;
		int id = 0;
		System.out.println("¡Crear cuenta rápidamente!");
		System.out.println("Introduzca su nombre: ");
		nombre = sc.nextLine();
		for (int i = 0; i < usuarioList.size(); i++) {
			if (nombre.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				valid = false;
				break;
			} else {
				System.out.println("Introduzca su apellido: ");
				apellido = sc.nextLine();
				System.out.println("¿Cuál desea que sea su id de usuario?");
				id = Integer.parseInt(sc.nextLine());
				for (int j = 0; j < usuarioList.size(); j++) {
					if (id == usuarioList.get(j).getId()) {
						valid = false;
						break;
					}
				}
				if (valid == false) {
					break;
				}
			}
		}
		if (valid == true) {
			Usuario nuevoUsuario = new Usuario(id, nombre, apellido);

//			System.out.println(nuevoUsuario.toString());
			System.out.println("[?] AVISO: Nuevo usuario creado: " + nuevoUsuario.getNombre());
			usuarioList.add(nuevoUsuario);
		}
		if (valid == false) {
			System.out.println("[!] ERROR: No pueden haber dos nombres o dos IDs idénticos...");
		}
	}

	public static void editarContrayUsuario(ArrayList<Usuario> usuarioList, Scanner sc) {
		String introd = "";
		boolean valid = false;
		System.out.println("¿Que usuario debería cambiar?");
		introd = sc.nextLine();

		for (int i = 0; i < usuarioList.size(); i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				valid = true;
				System.out.println("Introduce tu nuevo nombre de usuario (Anterior: "
						+ usuarioList.get(i).getNombreUsuario() + ")");
				usuarioList.get(i).setNombreUsuario(sc.nextLine());
				System.out.println("Introduce tu nueva contraseña (Anterior: " + usuarioList.get(i).getContras() + ")");
				usuarioList.get(i).setContras(sc.nextLine());
				System.out.println("Nuevas características: " + usuarioList.get(i).toString());
				System.out.println("[?] AVISO: Edición hecha correctamente (recuerda guardar los cambios)");
			}
		}
		if (valid == false) {
			System.out.println("[!] ERROR: No se ha encontrado ningún usuario con ese nombre...");
		}
	}

	public static void guardarCambios(ArrayList<Usuario> usuarioList, Scanner sc, File file)
			throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		int numerocuestion = 1;

		for (int i = 0; i < usuarioList.size(); i++) {
			if (usuarioList.get(i).isActivo() == true) {
				numerocuestion = 1;
			} else if (usuarioList.get(i).isActivo() == false) {
				numerocuestion = 0;
			}
			pw.println(usuarioList.get(i).getId() + ":" + usuarioList.get(i).getNombre() + ":"
					+ usuarioList.get(i).getApellido() + ":" + usuarioList.get(i).getContras() + ":" + numerocuestion
					+ ":" + usuarioList.get(i).getNombreUsuario());
		}
		pw.close();
		System.out.println("[?] AVISO: Datos modificados guardados...");

	}
}
