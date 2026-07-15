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
public class Libro {
    
    // 1. DECLARACIÓN DE ATRIBUTOS (PROPIEDADES)
    // El modificador 'final' asegura que la "caja contenedora" no sea reemplazada por otra,
    // aunque el valor que está guardado dentro de la caja sí pueda cambiar libremente.
    private final IntegerProperty id;
    private final StringProperty titulo;
    private final StringProperty autor;
    private final IntegerProperty anio;

    /**
     * Constructor 1: Diseñado para cuando creas un libro nuevo en el formulario.
     * Como aún no se ha guardado en SQLite, el ID se inicializa en 0 de forma temporal.
     */
    public Libro(String titulo, String autor, int anio) {
        // 'SimpleIntegerProperty' y 'SimpleStringProperty' se encargan de instanciar las propiedades
        this.id = new SimpleIntegerProperty(0);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anio = new SimpleIntegerProperty(anio);
    }

    /**
     * Constructor 2: Diseñado para cuando consultas la base de datos.
     * Reconstruye el objeto asignándole el ID real que SQLite generó automáticamente.
     */
    public Libro(int id, String titulo, String autor, int anio) {
        this.id = new SimpleIntegerProperty(id);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anio = new SimpleIntegerProperty(anio);
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

    // --- MÉTODOS PARA EL ATRIBUTO: TÍTULO ---
    
    // Devuelve el texto plano (String) del título.
    public String getTitulo() { 
        return titulo.get(); 
    }
    
    // Devuelve la propiedad del título para vincularla a componentes visuales.
    public StringProperty tituloProperty() { 
        return titulo; 
    }
    
    // Modifica el título del libro y actualiza las celdas de la interfaz al instante.
    public void setTitulo(String titulo) { 
        this.titulo.set(titulo); 
    }

    // --- MÉTODOS PARA EL ATRIBUTO: AUTOR ---
    
    // Devuelve el nombre del autor en texto plano (String).
    public String getAutor() { 
        return autor.get(); 
    }
    
    // Devuelve la propiedad del autor.
    public StringProperty autorProperty() { 
        return autor; 
    }
    
    // Modifica el autor del libro.
    public void setAutor(String autor) { 
        this.autor.set(autor); 
    }

    // --- MÉTODOS PARA EL ATRIBUTO: AÑO ---
    
    // Devuelve el número entero (int) del año.
    public int getAnio() { 
        return anio.get(); 
    }
    
    // Devuelve la propiedad del año.
    public IntegerProperty anioProperty() { 
        return anio; 
    }
    
    // Modifica el año del libro.
    public void setAnio(int anio) { 
        this.anio.set(anio); 
    }
}

