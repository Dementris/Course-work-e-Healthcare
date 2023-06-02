package com.cursework.ehelthcare.appointment;

import com.cursework.ehelthcare.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAllByDoctor(User doctor);
    List<Appointment> findAllByUser(User user);
}
