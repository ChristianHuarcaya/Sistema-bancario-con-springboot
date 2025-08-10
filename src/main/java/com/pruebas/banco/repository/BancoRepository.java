package com.pruebas.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebas.banco.entidad.Banco;


@Repository
public interface BancoRepository extends JpaRepository<Banco,Long> {

}
