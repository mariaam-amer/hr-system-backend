package com.example.hr_system.repositories;

import com.example.hr_system.models.entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HrRepository extends JpaRepository<Hr, Long> {
    Optional<Hr> findByEmail(String email);
    Optional<Hr> findByPhone(String phone);

}
