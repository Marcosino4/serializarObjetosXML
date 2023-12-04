package main;

import java.util.Date;

public class Cita {
    private Date fecha;
    private String motivo;

    public Cita(Date fecha, String motivo) {
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }
}