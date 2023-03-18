package org.utl.helpdesk.model;

public class Empleado {
    public int idEmpleado;
    public String nombreEmpleado;
    public String primerApellido;
    public String segundoApellido;
    public String rfc;
    public String email;
    public String telefono;
    public String foto;
    public Departamento departamento;
    public Usuario usuario;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombreEmpleado, String primerApellido, String segundoApellido, String rfc, String email, String telefono, String foto, Departamento departamento, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.rfc = rfc;
        this.email = email;
        this.telefono = telefono;
        this.foto = foto;
        this.departamento = departamento;
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
