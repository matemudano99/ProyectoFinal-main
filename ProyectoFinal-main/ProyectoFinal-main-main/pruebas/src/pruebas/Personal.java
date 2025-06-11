package pruebas;

public abstract class Personal {
    protected int id;
    protected String dni;
    protected String password;
    protected String nombre;
    protected String email;
    protected String telefono;
    protected String rol;
    protected Turno turno;

    // Constructor general (usado por las subclases)
    public Personal( String dni, String nombre, String email, String telefono, String rol, Turno turno) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.turno = turno;
        this.password = ""; // Valor por defecto si no se especifica
    }
    public Personal(int id, String dni, String nombre, String email, String telefono, String rol, Turno turno) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.turno = turno;
        this.password = ""; // Valor por defecto si no se especifica
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
