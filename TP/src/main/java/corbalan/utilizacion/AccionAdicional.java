package corbalan.utilizacion;

import corbalan.framework.Accion;

public class AccionAdicional implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando");
    }

    @Override
    public String nombreItemMenu() {
        return "Accion Adicional";
    }

    @Override
    public String descripcionItemMenu() {
        return "Hace un Drop a la BD de Amazon";
    }
}
