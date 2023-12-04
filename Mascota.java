package main;

import java.util.List;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String sexo;
    private int edad;
    private List<Cita> citas;

    public Mascota(int id, String nombre, String especie, String sexo, int edad, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.sexo = sexo;
        this.edad = edad;
        this.citas = citas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public List<Cita> getCitas() {
        return citas;
    }
}
