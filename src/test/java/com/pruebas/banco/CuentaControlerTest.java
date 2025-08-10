package com.pruebas.banco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebas.banco.controller.CuentaController;
import com.pruebas.banco.entidad.Cuenta;
import com.pruebas.banco.entidad.TransaccionDTO;
import com.pruebas.banco.service.CuentaServicio;

import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;

// Levanta solo el contexto del controlador CuentaController
@WebMvcTest(CuentaController.class)
public class CuentaControlerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CuentaServicio cuentaServicio;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void configurar() {
		// Configuraciones extra del objectMapper si se necesitan
	}

	@Test
	public void testListarCuentas() throws Exception {
		List<Cuenta> cuentas = Arrays.asList(Datos.crearCuenta001().orElseThrow(),
				Datos.crearCuenta002().orElseThrow());

		when(cuentaServicio.listarCuentas()).thenReturn(cuentas);

		mockMvc.perform(get("/api/cuentas").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].persona").value("Dior"))
				.andExpect(jsonPath("$[0].saldo").value(1000)).andExpect(jsonPath("$[1].persona").value("Cristian"))
				.andExpect(jsonPath("$[1].saldo").value(2000))
				.andExpect(content().json(objectMapper.writeValueAsString(cuentas)));

		verify(cuentaServicio).listarCuentas();
	}

	@Test
	public void testObtenerCuentaPorId() throws Exception {
		when(cuentaServicio.buscarCuentaPorId(1L)).thenReturn(Datos.crearCuenta001().orElseThrow());

		mockMvc.perform(get("/api/cuentas/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.persona").value("Dior")).andExpect(jsonPath("$.saldo").value(1000));

		verify(cuentaServicio).buscarCuentaPorId(1L);
	}

	@Test
	public void testGuardarCuenta() throws Exception {
		Cuenta cuenta = new Cuenta(null, "juancho", new BigDecimal(3000));

		when(cuentaServicio.guardarCuenta(any())).then(invocation -> {
			Cuenta c = invocation.getArgument(0);
			c.setId(3L);
			return c;
		});

		mockMvc.perform(post("/api/cuentas").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuenta))).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id", is(3)))
				.andExpect(jsonPath("$.persona", is("juancho"))).andExpect(jsonPath("$.saldo", is(3000)));

		verify(cuentaServicio).guardarCuenta(any());
	}

	@Test
	public void testObtenerTotalTransferencias() throws Exception {
		when(cuentaServicio.revisarTotalDeTransferencias(1L)).thenReturn(5);

		mockMvc.perform(get("/api/cuentas/banco/1/transferencias").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("5"));

		verify(cuentaServicio).revisarTotalDeTransferencias(1L);
	}

	@Test
	public void testObtenerSaldo() throws Exception {
		when(cuentaServicio.revisarSaldo(1L)).thenReturn(new BigDecimal("1500"));

		mockMvc.perform(get("/api/cuentas/1/saldo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("1500"));

		verify(cuentaServicio).revisarSaldo(1L);
	}

	@Test
	public void testTransferirDinero() throws Exception {
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		transaccionDTO.setCuentaOriginId(1L);
		transaccionDTO.setCuentaDestinoId(2L);
		transaccionDTO.setMonto(new BigDecimal(100));
		transaccionDTO.setBancoId(1L);

		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("date", LocalDate.now().toString());
		respuesta.put("status", "Ok");
		respuesta.put("mensaje", "Transferencia realizada con exito");
		respuesta.put("transaccionDTO", transaccionDTO);

		mockMvc.perform(post("/api/cuentas/transferir").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transaccionDTO))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.status").value("Ok"))
				.andExpect(jsonPath("$.mensaje").value("Transferencia realizada con exito"))
				.andExpect(jsonPath("$.transaccionDTO.cuentaOriginId").value(1))
				.andExpect(jsonPath("$.transaccionDTO.cuentaDestinoId").value(2))
				.andExpect(jsonPath("$.transaccionDTO.monto").value(100))
				.andExpect(jsonPath("$.transaccionDTO.bancoId").value(1))
				.andExpect(content().json(objectMapper.writeValueAsString(respuesta)));
	}

}