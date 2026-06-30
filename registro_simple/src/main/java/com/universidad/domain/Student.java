package com.universidad.domain;
// es un contenedor que permite organizar y agrupar clases, interfaces y otros paquetes 
// relacionados entre sí. Funciona exactamente como una carpeta en tu sistema operativo, 
// sirviendo para estructurar proyectos grandes y evitar conflictos de nombres

public class Student {
    private String fullName;
    private String address;
    private String phone;
    private String email;

    public Student(String fullName, String address, String phone, String email) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getFullName() { return fullName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        
        //Funciona como una plantilla. En lugar de concatenar textos con el símbolo + 
        // (lo cual se vuelve confuso y propenso a errores), usas marcadores de posición (%s) 
        // que luego se rellenan con las variables que pases en orden.

        return String.format("Estudiante: %s | Dir: %s | Tel: %s | E-mail: %s", 
                fullName, address, phone, email);

    }
}
