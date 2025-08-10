package com.pruebas.banco.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebas.banco.entidad.Banco;
import com.pruebas.banco.entidad.Cuenta;
import com.pruebas.banco.repository.BancoRepository;
import com.pruebas.banco.repository.CuentaRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaServicioImpl implements CuentaServicio {

	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private BancoRepository bancoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> listarCuentas() {
		return cuentaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta buscarCuentaPorId(Long id) {
		return cuentaRepository.findById(id).orElseThrow();
	}

	@Override
	@Transactional
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public int revisarTotalDeTransferencias(Long bancoId) {
		Banco banco = bancoRepository.findById(bancoId).orElseThrow();
		return banco.getTotaltransferencia();
	}

	@Override
	@Transactional(readOnly = true)
	public BigDecimal revisarSaldo(Long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
		return cuenta.getSaldo();
	}

	@Override
	@Transactional
	public void transferirDinero(Long numeroCuentaOrigin, Long numeroCuentaDestino, BigDecimal monto, Long bancoId) {
		Cuenta cuentaOrigin = cuentaRepository.findById(numeroCuentaOrigin).orElseThrow();
		Cuenta cuentaDestino = cuentaRepository.findById(numeroCuentaDestino).orElseThrow();

		// Debitar de la cuenta origen
		cuentaOrigin.realizarDebito(monto);
		cuentaRepository.save(cuentaOrigin);

		// Acreditar a la cuenta destino
		cuentaDestino.realizarCredito(monto);
		cuentaRepository.save(cuentaDestino);

		// Actualizar total de transferencias del banco
		Banco banco = bancoRepository.findById(bancoId).orElseThrow();
		banco.setTotaltransferencia(banco.getTotaltransferencia() + 1);
		bancoRepository.save(banco);
	}
}
