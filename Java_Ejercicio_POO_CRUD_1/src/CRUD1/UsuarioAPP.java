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
								+ "Crear un usuario con contraseña y activo por defecto\n[6] = editar el nombreUsuario y contraseña\n[7] = Guardar\n[8] = Salir");
				numeritos = Integer.parseInt(sc.nextLine());

				switch (numeritos) {

				case 1:
					mostrarUsuario(usuarioList, sc, cantidadUsuarios);
					break;

				case 2:
					mostrarTodos(usuarioList, sc, cantidadUsuarios);
					break;

				case 3:
					borrarUsuario(usuarioList, sc, cantidadUsuarios);
					break;

				case 4:
					break;

				case 5:
					break;

				case 6:
					break;

				case 7:
					break;

				case 8:
					System.out.println("Fin del programa...");
					break;

				default:
				}
			} while (numeritos != 8);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("[!] ERROR: El archivo no se encuentra");
		}
	}

	public static void mostrarUsuario(ArrayList<Usuario> usuarioList, Scanner sc, int cant) {
		String introd = "";
		System.out.println("Introduzca su nombre de usuario: ");
		introd = sc.nextLine();
		for (int i = 0; i < cant; i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				do {
					System.out.println("Introduce la contraseña: ");
					introd = sc.nextLine();
					if (introd.equals(usuarioList.get(i).getContras())) {
						break;
					}
					System.out.println("[!] ERROR: Contrasña incorrecta\n");
				} while (!introd.equals(usuarioList.get(i).getContras()));
				System.out.println("¡Bienvenido! " + usuarioList.get(i).getNombre() + ", estos son tus datos:\n");
				System.out.println("ID: " + usuarioList.get(i).getId());
				System.out.println("Nombre y Apellidos: " + usuarioList.get(i).getNombre() + " "
						+ usuarioList.get(i).getApellido());
				System.out.println("Nombre de usuario: " + usuarioList.get(i).getNombreUsuario());
				if (usuarioList.get(i).isActivo() == true) {

				} else if (usuarioList.get(i).isActivo() == false)
					System.out.println("Actividad: " + usuarioList.get(i).isActivo());
			}
		}
	}

	public static void mostrarTodos(ArrayList<Usuario> usuarioList, Scanner sc, int cant) {
		for (int i = 0; i < usuarioList.size(); i++) {
			System.out.println(usuarioList.get(i).toString());
		}
	}

	public static void borrarUsuario(ArrayList<Usuario> usuarioList, Scanner sc, int cant) {
		String introd = "";
		char sOn;
		System.out.println("Introduzca el nombre de usuario que quiera deshabilitar: ");
		introd = sc.nextLine();

		for (int i = 0; i < cant; i++) {
			if (introd.equalsIgnoreCase(usuarioList.get(i).getNombre())) {
				System.out.println("Seguro que quiere eliminar el usuario? (s/n)");
				sOn = sc.nextLine().charAt(0);
				if (sOn == 's' || sOn == 'n' || sOn == 'S' || sOn == 'N') {
					if (sOn == 's' || sOn == 'S') {
						usuarioList.remove(i);
						break;
					} else {
						System.out.println("Abortando el borrado de usuario...");
						break;
					}
				}

			} else {
				System.out.println("Introduce un nombre correcto");
				break;
			}
		}
	}
	
	public static void nuevoUsuario(ArrayList<Usuario> usuarioList, Scanner sc) {
		
	}
}
