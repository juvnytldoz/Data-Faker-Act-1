package com.biblioteca_salas_duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.model.;        // Importa todas las clases del paquete model (entidades)
import biblioteca.salas.duoc.repository.;   // Importa todas las interfaces de acceso a datos (repositorios)
import net.datafaker.Faker;                  // Importa la librería Java Faker para generar datos falsos
import org.springframework.beans.factory.annotation.Autowired;      // Permite inyectar dependencias automáticamente
import org.springframework.boot.CommandLineRunner;                  // Permite ejecutar código al iniciar la app
import org.springframework.context.annotation.Profile;              // Limita esta clase a ejecutarse solo en un perfil específico
import org.springframework.stereotype.Component;                    // Marca esta clase como componente gestionado por Spring

import java.util.Date;   // Clase para manejar fechas y horas
import java.util.List;   // Clase para manejar listas de objetos
import java.util.Random; // Clase para generar números aleatorios

@Profile("dev")         // Esta clase solo se ejecuta si el perfil activo es "dev"
@Component              // Marca esta clase para que Spring la detecte y la ejecute automáticamente
public class DataLoader implements CommandLineRunner { // Clase que se ejecutará al iniciar la app

    @Autowired private CarreraRepository carreraRepository;         // Inyección del repositorio de carreras
    @Autowired private EstudianteRepository estudianteRepository;   // Inyección del repositorio de estudiantes
    @Autowired private ReservaRepository reservaRepository;         // Inyección del repositorio de reservas
    @Autowired private SalaRepository salaRepository;               // Inyección del repositorio de salas
    @Autowired private TipoSalaRepository tipoSalaRepository;       // Inyección del repositorio de tipos de sala

    @Override
    public void run(String... args) {                // Método que se ejecuta automáticamente al iniciar la app
        Faker faker = new Faker();                   // Instancia para generar datos falsos
        Random random = new Random();                // Instancia para seleccionar elementos al azar

// Tipos de sala
        for (int i = 0; i < 3; i++) {                    // Genera 3 tipos de sala
            TipoSala tipo = new TipoSala();              // Crea nueva instancia
            tipo.setIdTipo(i + 1);                       // Asigna ID incremental (1, 2, 3)
            tipo.setNombre(faker.book().genre());        // Asigna un nombre falso basado en géneros de libros
            tipoSalaRepository.save(tipo);               // Guarda en la base de datos
        }

// Carreras
        for (int i = 0; i < 5; i++) {                    // Genera 5 carreras distintas
            Carrera carrera = new Carrera();             // Nueva carrera
            carrera.setCodigo(faker.code().asin());      // Código aleatorio tipo ASIN
            carrera.setNombre(faker.educator().course()); // Nombre de curso aleatorio
            carreraRepository.save(carrera);             // Guarda en la base
        }

        List<Carrera> carreras = carreraRepository.findAll(); // Obtiene todas las carreras guardadas

// Estudiantes
        for (int i = 0; i < 50; i++) {                               // Genera 50 estudiantes
            Estudiante est = new Estudiante();                      // Nuevo estudiante
            est.setId(i + 1);                                       // ID incremental
            est.setRun(faker.idNumber().valid());                   // RUN falso
            est.setNombres(faker.name().fullName());                // Nombre completo falso
            est.setCorreo(faker.internet().emailAddress());         // Email falso
            est.setJornada(faker.options().option("D", "N").charAt(0)); // Jornada aleatoria: Diurna (D) o Nocturna (N)
            est.setTelefono(faker.number().numberBetween(100000000, 999999999)); // Teléfono chileno simulado
            est.setCarrera(carreras.get(random.nextInt(carreras.size())));       // Asocia estudiante a una carrera aleatoria
            estudianteRepository.save(est);                        // Guarda estudiante en BD
        }

// Salas
        for (int i = 0; i < 10; i++) {                                // Crea 10 salas
            Sala sala = new Sala();                                   // Nueva sala
            sala.setCodigo(i + 1);                                    // Código incremental
            sala.setNombre(faker.university().name());                // Nombre de universidad como nombre de sala
            sala.setCapacidad(faker.number().numberBetween(10, 100)); // Capacidad entre 10 y 100
            sala.setIdInstituo(faker.number().randomDigit());         // ID aleatorio de instituto
            sala.setTipoSala(tipoSalaRepository.findAll().get(random.nextInt(3))); // Asigna tipo de sala aleatorio
            salaRepository.save(sala);                                // Guarda la sala
        }

        List<Estudiante> estudiantes = estudianteRepository.findAll(); // Lista de estudiantes desde la BD
        List<Sala> salas = salaRepository.findAll();                   // Lista de salas desde la BD

// Reservas
        for (int i = 0; i < 20; i++) {                               // Crea 20 reservas
            Reserva reserva = new Reserva();                        // Nueva reserva
            reserva.setId(i + 1);                                   // ID incremental
            reserva.setEstudiante(estudiantes.get(random.nextInt(estudiantes.size()))); // Estudiante aleatorio
            reserva.setSala(salas.get(random.nextInt(salas.size())));                   // Sala aleatoria
            reserva.setFechaSolicitada(new Date());                // Fecha actual
            reserva.setHoraSolicitada(new Date());                 // Hora de inicio = ahora
            reserva.setHoraCierre(new Date(System.currentTimeMillis() + faker.number().numberBetween(3600000, 7200000))); // Hora de cierre = 1-2 horas después
            reserva.setEstado(faker.number().numberBetween(0, 2)); // Estado aleatorio entre 0 y 1
            reservaRepository.save(reserva);                       // Guarda la reserva
        }
    }
}
