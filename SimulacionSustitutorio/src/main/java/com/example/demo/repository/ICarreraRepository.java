package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Carrera;

public interface ICarreraRepository extends JpaRepository<Carrera, Integer> { }
