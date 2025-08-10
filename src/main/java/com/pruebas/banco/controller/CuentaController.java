package com.pruebas.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.pruebas.banco.entidad.Cuenta;
import com.pruebas.banco.entidad.TransaccionDTO;
import com.pruebas.banco.service.CuentaServicio;

@RestController
@RequestMapping("api/cuentas")
public class CuentaController {

	private final CuentaServicio cuentaServicio;

    public CuentaController(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cuenta> listarCuentas() {
        return cuentaServicio.listarCuentas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta obtenerCuenta(@PathVariable Long id) {
        return cuentaServicio.buscarCuentaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuenta guardarCuenta(@RequestBody Cuenta cuenta) {
        return cuentaServicio.guardarCuenta(cuenta);
    }

    @GetMapping("/banco/{bancoId}/transferencias")
    @ResponseStatus(HttpStatus.OK)
    public int obtenerTotalTransferencias(@PathVariable Long bancoId) {
        return cuentaServicio.revisarTotalDeTransferencias(bancoId);
    }

    @GetMapping("/{cuentaId}/saldo")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal obtenerSaldo(@PathVariable Long cuentaId) {
        return cuentaServicio.revisarSaldo(cuentaId);
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferirDinero(@RequestBody TransaccionDTO transaccionDTO) {
        cuentaServicio.transferirDinero(
            transaccionDTO.getCuentaOriginId(),
            transaccionDTO.getCuentaDestinoId(),
            transaccionDTO.getMonto(),
            transaccionDTO.getBancoId());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("date", LocalDate.now().toString());
        respuesta.put("status", "Ok");
        respuesta.put("mensaje", "Transferencia realizada con exito");
        respuesta.put("transaccionDTO", transaccionDTO);

        return ResponseEntity.ok(respuesta);
    }
}
