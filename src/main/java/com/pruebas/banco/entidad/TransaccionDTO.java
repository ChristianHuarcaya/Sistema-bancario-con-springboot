package com.pruebas.banco.entidad;

import java.math.BigDecimal;

public class TransaccionDTO {

	private Long cuentaOriginId;

	private Long cuentaDestinoId;

	private BigDecimal monto;

	private Long bancoId;

	public Long getCuentaOriginId() {
		return cuentaOriginId;
	}

	public Long getCuentaDestinoId() {
		return cuentaDestinoId;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public Long getBancoId() {
		return bancoId;
	}

	public void setCuentaOriginId(Long cuentaOriginId) {
		this.cuentaOriginId = cuentaOriginId;
	}

	public void setCuentaDestinoId(Long cuentaDestinoId) {
		this.cuentaDestinoId = cuentaDestinoId;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public void setBancoId(Long bancoId) {
		this.bancoId = bancoId;
	}

}
