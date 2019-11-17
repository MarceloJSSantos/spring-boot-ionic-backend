package com.marcelojssantos.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcelojssantos.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preenchePagamentoComBoleto(PagamentoComBoleto pgmto, Date instantePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgmto.setDataVencimento(cal.getTime());
	}
}
