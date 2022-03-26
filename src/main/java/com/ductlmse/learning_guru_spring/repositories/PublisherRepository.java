package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}