package sistema.bienestar;

import java.time.LocalDate;
import java.time.Period;

public class Persona {

    private String nombre;
    private LocalDate nacimiento;
    private double kilogramos;
    private double metros;
    private String residencia;
    private String celular;
    private String email;

    public Persona(String nombre,
            LocalDate nacimiento,
            double kilogramos,
            double metros,
            String residencia,
            String celular,
            String email) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.kilogramos = kilogramos;
        this.metros = metros;
        this.residencia = residencia;
        this.celular = celular;
        this.email = email;
    }

    public int obtenerEdad() {
        return Period.between(
                nacimiento, LocalDate.now()).getYears();
    }

    public boolean aceptarRegistro() {
        return obtenerEdad() >= 18;
    }

    public double obtenerIndiceCorporal() {
        return kilogramos / Math.pow(metros, 2);
    }

    public String obtenerEstadoNutricional() {
        double indice = obtenerIndiceCorporal();
        if (indice < 18.5) {
            return "Bajo peso";
        }
        if (indice <= 24.9) {
            return "Normal";
        }
        if (indice <= 29.9) {
            return "Sobrepeso";
        }
        return "Obesidad";
    }

    public void imprimirFicha() {
        System.out.println("\nDATOS DEL REGISTRO");
        System.out.println("Nombre completo: " + nombre);
        System.out.println("Nacimiento: " + nacimiento);
        System.out.println("Edad: " + obtenerEdad());
        System.out.println(
                "Peso registrado: " + kilogramos + " kg");
        System.out.println(
                "Estatura registrada: " + metros + " m");
        System.out.println("Direccion: " + residencia);
        System.out.println("Numero telefonico: " + celular);
        System.out.println("Correo: " + email);
        System.out.printf(
                "Indice corporal: %.2f%n",
                obtenerIndiceCorporal());
        System.out.println(
                "Resultado: " + obtenerEstadoNutricional());
    }

    // ---- Getters ----
    public String getNombre() {
        return nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public double getKilogramos() {
        return kilogramos;
    }

    public double getMetros() {
        return metros;
    }

    public String getResidencia() {
        return residencia;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    // ---- Setters (para cumplir con el encapsulamiento completo) ----
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setKilogramos(double kilogramos) {
        this.kilogramos = kilogramos;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
