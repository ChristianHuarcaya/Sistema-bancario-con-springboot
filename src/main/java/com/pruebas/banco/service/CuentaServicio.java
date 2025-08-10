package com.pruebas.banco.service;

import java.math.BigDecimal;
import java.util.List;

import com.pruebas.banco.entidad.Cuenta;

public interface CuentaServicio {

	List<Cuenta> listarCuentas();

    Cuenta buscarCuentaPorId(Long id);

    Cuenta guardarCuenta(Cuenta cuenta);

    int revisarTotalDeTransferencias(Long bancoId);

    BigDecimal revisarSaldo(Long cuentaId);

    void transferirDinero(Long numeroCuentaOrigen, Long numeroCuentaDestino, BigDecimal monto, Long bancoId);
}

