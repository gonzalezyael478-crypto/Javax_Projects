package com.universidad.usecase;

import com.universidad.domain.Student;
import java.util.ArrayList;
import java.util.List;

//En Java, List es una interfaz (un contrato) y ArrayList es una clase concreta 
// que implementa ese contrato. Juntas sirven para crear listas de datos dinámicas, 
// es decir, colecciones de elementos que pueden crecer o achicarse automáticamente a 
// diferencia de los arreglos (arrays) tradicionales que tienen un tamaño fijo

public class ManageStudentsUseCase {

    //Creando una lista de estudiantes vacía y protegiendo su referencia
    private final List<Student> studentList = new ArrayList<>();
    //La palabra final significa que la variable solo se puede asignar una vez. 
    // Una vez que la lista se crea con new ArrayList<>(), esa variable queda "soldada" a 
    // esa lista específica en memoria.

    public void registerStudent(Student student) {
        // Aquí se pueden añadir validaciones de negocio en el futuro
        studentList.add(student); //Se agrega una list de Estudiantes (Student)
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList); //Regresa la totalidad de la lista
    }
}

