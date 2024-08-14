package com.javeriana.pontimovil.ponti_movil.repositories;

import com.javeriana.pontimovil.ponti_movil.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface HorarioRepository extends JpaRepository<Horario, UUID> {
    Horario findHorarioByDiaAndHoraInicioAndHoraFin(String dia, LocalTime horaInicio, LocalTime horaFin);
    List<Horario> findHorariosByDia(String dia);
}