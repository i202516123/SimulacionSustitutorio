package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Asignatura;

public interface IAsignaturaRepository extends JpaRepository<Asignatura, Integer> { }
