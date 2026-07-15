package edu.utng.model;

// Importamos los contenedores de propiedades (Property) y sus implementaciones básicas (Simple)
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase de negocio (Modelo) que representa un Libro.
 * En JavaFX no usamos 'int' o 'String' nativos directamente en los atributos.
 * Usamos 'Property' porque son objetos inteligentes que "avisan" a la interfaz visual
 * (como las tablas o formularios) cada vez que el valor de una variable cambia.
 */
public class Usuario {
    
    // 1. DECLARACIÓN DE ATRIBUTOS (PROPIEDADES)
    // El modificador 'final' asegura que la "caja contenedora" no sea reemplazada por otra,
    // aunque el valor que está guardado dentro de la caja sí pueda cambiar libremente.
    private final IntegerProperty id;
    private final StringProperty nombre;
    private final StringProperty correo;
    private final StringProperty telefono;

    /**
     * Constructor 1: Diseñado para cuando creas un libro nuevo en el formulario.
     * Como aún no se ha guardado en SQLite, el ID se inicializa en 0 de forma temporal.
     */
    public Usuario(String nombre, String correo, String telefono) {
        // 'SimpleIntegerProperty' y 'SimpleStringProperty' se encargan de instanciar las propiedades
        this.id = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.telefono = new SimpleStringProperty(telefono);
    }

    /**
     * Constructor 2: Diseñado para cuando consultas la base de datos.
     * Reconstruye el objeto asignándole el ID real que SQLite generó automáticamente.
     */
    public Usuario(int id, String nombre, String correo, String telefono) {
        this.id = new SimpleIntegerProperty(id);
         this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.telefono = new SimpleStringProperty(telefono);
    }

    // =========================================================================
    // TRÍOS DE MÉTODOS ESTÁNDAR (Obligatorios en JavaFX para el Data Binding)
    // =========================================================================

    // --- MÉTODOS PARA EL ATRIBUTO: ID ---
    
    // 1. El Getter: Extrae el valor numérico puro (int). Útil para código Java estándar (ej. sentencias SQL).
    public int getId() { 
        return id.get(); 
    }
    
    // 2. El método Property: Devuelve el objeto completo. Es el que usa el TableView para "escuchar" cambios.
    public IntegerProperty idProperty() { 
        return id; 
    }
    
    // 3. El Setter: Cambia el valor que está dentro de la caja e informa automáticamente a la pantalla.
    public void setId(int id) { 
        this.id.set(id); 
    }

    // --- MÉTODOS PARA EL ATRIBUTO: NOMBRE ---
    
    // Devuelve el texto plano (String) del nombre.
    public String getNombre() { 
        return nombre.get(); 
    }
    
    // Devuelve la propiedad del nombre para vincularla a componentes visuales.
    public StringProperty nombreProperty() { 
        return nombre; 
    }
    
    // Modifica el nombre del usuario y actualiza las celdas de la interfaz al instante.
    public void setNombre(String nombre) { 
        this.nombre.set(nombre); 
    }

    // --- MÉTODOS PARA EL ATRIBUTO: CORREO ---
    
    // Devuelve el correo del usuario en texto plano (String).
    public String getCorreo() { 
        return correo.get(); 
    }
    
    // Devuelve la propiedad del correo.
    public StringProperty correoProperty() { 
        return correo; 
    }
    
    // Modifica el correo del usuario.
    public void setCorreo(String correo) { 
        this.correo.set(correo); 
    }

    // --- MÉTODOS PARA EL ATRIBUTO: TELEFONO ---
    
    // Devuelve el  TELEFONO.
    public String getTelefono() { 
        return telefono.get(); 
    }
    
    // Devuelve la propiedad del año.
    public StringProperty telefonoProperty() { 
        return telefono; 
    }
    
    // Modifica el telefono del usuario.
    public void setTelefono(String telefono) { 
        this.telefono.set(telefono); 
    }
}

