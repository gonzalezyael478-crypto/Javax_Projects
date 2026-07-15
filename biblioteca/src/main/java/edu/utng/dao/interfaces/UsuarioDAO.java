package edu.utng.dao.interfaces;

// Importamos la clase del modelo para saber qué tipo de objeto va a manipular esta interfaz
import edu.utng.model.Usuario;
// Importamos la utilidad List de Java para poder retornar colecciones o conjuntos de libros
import java.util.List;

/**
 * 💡 NOTA PARA JUNIOR — ¿Qué es el Patrón DAO (Data Access Object)?
 * Es un patrón de diseño que se encarga de separar por completo la lógica de tu aplicación 
 * (los controladores y las vistas) de la base de datos (SQL, archivos, etc.).
 * 
 * ¿Por qué creamos una INTERFAZ en lugar de una clase directa?
 * Porque una interfaz funciona como un "CONTRATO" obligatorio. Aquí solo definimos QUÉ operaciones 
 * puede hacer el sistema con los libros (el diseño), pero no nos importa CÓMO lo hace (la implementación).
 * Esto permite que si hoy usas MySQL y mañana cambias a PostgreSQL, tu controlador no sufra ningún cambio.
 */
public interface UsuarioDAO {

    /**
     * Registra un nuevo usuario en el sistema de persistencia (Base de datos).
     * 
     * @param usuario El objeto de tipo 'Usuario' que contiene los datos a registrar (Nombre, Correo, Telefono).
     * @return true si el usuario se insertó con éxito; false si ocurrió algún error (ej. conexión caída).
     */
    boolean insertar(Usuario usuario);

    /**
     * Recupera todos los registros de usuarios disponibles.
     * 
     * @return Una lista (List) que contiene todos los objetos 'Usuario' encontrados. 
     *         Si no hay registros, devolverá una lista vacía, nunca null.
     */
    List<Usuario> listar();

    /**
     * Modifica los datos de un libro existente en el sistema.
     * 
     * @param usuario El objeto 'Usuario' modificado. Es indispensable que este objeto ya incluya su 
     *              ID numérico correspondiente para saber exactamente qué registro de la base de datos sobreescribir.
     * @return true si la actualización fue exitosa; false si no se pudo modificar.
     */
    boolean actualizar(Usuario usuario);

    /**
     * Elimina de forma permanente un usuario utilizando su identificador único.
     * 
     * @param id El identificador numérico (Primary Key) del usuario que se desea borrar.
     * @return true si el registro fue eliminado correctamente; false si el ID no existía o falló el borrado.
     */
    boolean eliminar(int id);
}

