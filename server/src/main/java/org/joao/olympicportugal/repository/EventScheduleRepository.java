package org.joao.olympicportugal.repository;

import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventScheduleRepository extends JpaRepository<EventSchedule, Long> {

    List<EventSchedule> findAllByDayGreaterThanEqualOrderByDay(LocalDate date);

    Optional<EventSchedule> findEventScheduleByDayIs(LocalDate date);
}
