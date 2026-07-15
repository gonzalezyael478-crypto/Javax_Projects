# 📚 Sistema de Gestión de Libros (CRUD) - JavaFX + SQLite

Este es un proyecto escolar modular desarrollado para la **UTNG** que implementa un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para la gestión de una biblioteca local. La aplicación destaca por separar completamente la interfaz gráfica de la lógica del negocio mediante un diseño de software limpio y profesional.

---

## 🏗️ Arquitectura y Patrones de Diseño

El proyecto está construido bajo una **Arquitectura en Capas** estricta utilizando dos patrones fundamentales de la ingeniería de software:

1. **MVC (Modelo-Vista-Controlador):** 
   * **Modelo (`model`):** Contiene la clase `Libro` con propiedades reactivas de JavaFX (`StringProperty`, `IntegerProperty`) que permiten el *Data Binding* automático con la interfaz.
   * **Vista (`view`):** El diseño visual estructurado en un archivo declarativo `libro_view.fxml` manejado desde Scene Builder.
   * **Controlador (`controller`):** El cerebro lógico (`LibroController`) que escucha los eventos visuales y comunica los datos del formulario.

2. **Patrón DAO (Data Access Object):**
   * Separa por completo la lógica de la aplicación del motor de persistencia. Mediante la interfaz `LibroDAO`, el controlador desconoce cómo se guardan los datos, permitiendo intercambiar el motor de base de datos en el futuro sin romper el sistema.

---

## 🛠️ Tecnologías y Herramientas Utilizadas

* **Java 17+** (Con soporte nativo para el Sistema de Módulos del Proyecto Jigsaw).
* **JavaFX 17+** (Motor gráfico para interfaces de escritorio enriquecidas).
* **SQLite** (Motor de base de datos embebido, ligero y sin servidores externos; almacena todo en el archivo local `biblioteca.db`).
* **Maven** (Gestor de dependencias y ciclo de vida del proyecto).

---

## 📂 Estructura del Proyecto

```text
src/
├── main/
│   ├── java/
│   │   ├── edu.utng.app/          # Punto de entrada oficial (Clase Main)
│   │   ├── edu.utng.controller/   # Controlador que gestiona los eventos de la UI
│   │   ├── edu.utng.dao/          # Conexión e interfaces del patrón DAO
│   │   ├── edu.utng.dao.impl/     # Implementación física de las sentencias SQL (JDBC)
│   │   ├── edu.utng.model/        # Modelos de datos y propiedades de JavaFX
│   │   └── module-info.java       # Configuración y seguridad del módulo Java
│   └── resources/
│       └── edu.utng.view/         # Archivos de diseño visual (.fxml)
└── pom.xml                        # Configuración de dependencias de Maven
```

---

## 🚀 Requisitos e Instalación

### 1. Clonar o descargar el proyecto
Asegúrate de que la estructura de carpetas en tu IDE (IntelliJ, NetBeans o VS Code) respete la separación estricta entre código Java y Recursos de Maven (`src/main/resources`).

### 2. Recargar dependencias
Al abrir el proyecto, el gestor descargará automáticamente el driver `sqlite-jdbc`. Asegúrate de hacer un **Reload/Refresh Maven** para sincronizar las librerías.

### 3. Compilar y Ejecutar
Para evitar errores de caché con el cargador FXML, se recomienda limpiar el entorno antes de arrancar:
1. Ejecuta el comando o ciclo de vida: `mvn clean compile`
2. Ve a la clase `edu.utng.app.Main` y presiona **Run**.

> **Nota:** Al arrancar por primera vez, el sistema invocará automáticamente a `Conexion.inicializarBaseDatos()`, creando el archivo físico `biblioteca.db` y la tabla `libros` en la raíz de tu proyecto si es que aún no existen.