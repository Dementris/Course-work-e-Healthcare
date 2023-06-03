package com.cursework.ehelthcare.appointment;

import com.cursework.ehelthcare.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Appointment repository.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    /**
     * Find all by doctor list.
     *
     * @param doctor the doctor
     * @return the list
     */
    List<Appointment> findAllByDoctor(User doctor);

    /**
     * Find all by user list.
     *
     * @param user the user
     * @return the list
     */
    List<Appointment> findAllByUser(User user);
}
