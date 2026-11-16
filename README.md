# Sistema de Gestión Hospitalaria — Programación II (UTN)

Modelo de objetos Java, desarrollado según la consigna de Gestión Hospitalaria. Todos los datos se mantienen en memoria; no utiliza bases de datos, archivos de persistencia ni dependencias externas.

## Organización

- `entidades`: personas, hospital, departamentos, salas, historias clínicas, matrículas y citas.
- `enums`: tipos de sangre, especialidades y estados de cita.
- `servicios`: contrato y gestión de citas, validación de disponibilidad.
- `excepciones`: excepción verificada para programación de citas.

## Ejecución

Se requiere Java 17 o superior. El archivo oficial `MainPruebas.java` está incluido en `src/main/java/org/jcr/`, sin modificaciones. Ejecutar su método `main` desde el IDE. El UML oficial se encuentra en `docs/UmlHospital.png`.

En PowerShell, desde la raíz:

```powershell
javac --release 17 -encoding UTF-8 -d bin (Get-ChildItem .\src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin org.jcr.MainPruebas
```

## Diseño

Las relaciones bidireccionales se sincronizan desde un único método por relación, sin duplicados. Las composiciones se crean desde su dueño. Las citas se validan antes de registrarse en las colecciones; la disponibilidad exige una separación mínima de dos horas para médico y sala. Los getters de listas protegen las colecciones internas.

## Estado de verificación

Compilación con JDK 21 y Java 17 como versión objetivo. El programa de pruebas oficial obtuvo 106 de 106 verificaciones correctas.

## Video demostrativo

[Explicación del sistema y ejecución de las pruebas oficiales](https://youtu.be/i52PyjlnWXk)
