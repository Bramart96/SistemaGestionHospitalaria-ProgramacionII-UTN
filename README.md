# Sistema de Gestión Hospitalaria | Java y POO

Desarrollé un modelo de gestión hospitalaria en **Java**, aplicando programación orientada a objetos, composición, asociaciones bidireccionales, enumeraciones, validaciones y excepciones. Utilicé colecciones en memoria, sin bases de datos ni bibliotecas externas.

## Diseño que implementé

- Modelé personas, pacientes, médicos, hospitales, departamentos, salas, historias clínicas, matrículas y citas.
- Incorporé enumeraciones para tipos de sangre, especialidades médicas y estados de las citas.
- Organicé los servicios de programación y consulta de citas y las validaciones de disponibilidad.
- Sincronicé asociaciones bidireccionales desde métodos controlados, evitando duplicados.
- Validé la creación de citas antes de incorporarlas a las colecciones.
- Protegí las listas internas mediante vistas no modificables.

Las reglas de disponibilidad exigen una separación mínima de dos horas para el médico y la sala.

## Estructura

```text
src/main/java/org/jcr/   Código fuente y MainPruebas.java
  entidades/             Modelo del dominio
  enums/                 Enumeraciones
  servicios/             Gestión y validación de citas
  excepciones/           Excepciones del sistema
docs/UmlHospital.png     Diagrama de clases
```

## Ejecución

Compilé con **JDK 21**, utilizando **Java 17** como versión objetivo. Desde PowerShell, en la raíz del repositorio:

```powershell
javac --release 17 -encoding UTF-8 -d bin (Get-ChildItem .\src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin org.jcr.MainPruebas
```

El archivo `MainPruebas.java` incluido en el proyecto ejecuta las comprobaciones del modelo.

## Resultados de verificación

Ejecuté el programa de pruebas oficial y obtuve **106 de 106 verificaciones correctas**. La ejecución abarcó el modelo de entidades, reglas de validación, relaciones y programación de citas.

[Video de explicación y ejecución del sistema](https://youtu.be/i52PyjlnWXk)

Este repositorio corresponde a un integrador de Programación II de la **UTN** y representa el comportamiento del sistema mediante objetos en memoria.
