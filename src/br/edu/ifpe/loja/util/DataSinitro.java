package br.edu.ifpe.loja.util;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DataSinitro implements AdapterSinitro {

	private static final DateTimeFormatter FORMATTER_PT_BR = DateTimeFormat.forPattern("dd 'de' MMMM 'de' yyyy");
	private static final DateTimeFormatter FORMATTER_SISTEMA_PORTUGUES = DateTimeFormat.forPattern("dd-MMMM-yyyy");
	@Override
	public String formatarExtenso(String dataSinitro) {
		LocalDate localDate = LocalDate.parse(dataSinitro);
		return localDate.toString(FORMATTER_PT_BR);
	}
	@Override
	public String formatarSistemaPortugues(String dataSinitro) {
		LocalDate localDate = LocalDate.parse(dataSinitro);
		return localDate.toString(FORMATTER_SISTEMA_PORTUGUES);
	}
	@Override
	public String parse(String dataSinistro) {
		LocalDate localDate = LocalDate.parse(dataSinistro);
		return localDate.toString();
	}
}
