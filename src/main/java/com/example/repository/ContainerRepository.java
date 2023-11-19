package com.example.repository;

import com.example.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContainerRepository extends JpaRepository<Container, UUID> {
}
