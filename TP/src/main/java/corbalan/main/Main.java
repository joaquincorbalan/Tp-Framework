package corbalan.main;

import corbalan.framework.MenuFramework;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Debe indicar la ruta del archivo de configuración.");
            return;
        }
        String rutaConfig = args[0];
        MenuFramework framework = new MenuFramework(rutaConfig);
        framework.iniciar();
    }
}

