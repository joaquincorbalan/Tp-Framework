package corbalan.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuFramework {
    private List<Accion> acciones = new ArrayList<>();

    public MenuFramework(String rutaConfig) {
        cargarAcciones(rutaConfig);
    }

    private void cargarAcciones(String rutaConfig) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaConfig));
            for (String linea : lineas) {
                if (linea.startsWith("acciones:")) {
                    String[] clases = linea.replace("acciones:", "").trim().split(";");
                    for (String clase : clases) {
                        clase = clase.trim();
                        try {
                            Class<?> clazz = Class.forName(clase);
                            Object obj = clazz.getDeclaredConstructor().newInstance();
                            if (obj instanceof Accion) {
                                acciones.add((Accion) obj);
                            }
                        } catch (Exception e) {
                            System.out.println("Error cargando clase: " + clase);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de configuración: " + e.getMessage());
        }
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");
            int opcion = sc.nextInt();
            if (opcion == acciones.size() + 1) {
                System.out.println("Saliendo...");
                break;
            } else if (opcion > 0 && opcion <= acciones.size()) {
                try {
                    acciones.get(opcion - 1).ejecutar();
                    System.out.println("Acción ejecutada con éxito.");
                } catch (Exception e) {
                    System.out.println("Error al ejecutar la acción: " + e.getMessage());
                }
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\nBienvenido, estas son sus opciones:");
        for (int i = 0; i < acciones.size(); i++) {
            Accion a = acciones.get(i);
            System.out.println((i + 1) + ". " + a.nombreItemMenu() + " (" + a.descripcionItemMenu() + ")");
        }
        System.out.println((acciones.size() + 1) + ". Salir");
    }
}

