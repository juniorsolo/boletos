package br.com.juniorcorp.util;

import java.time.LocalDate;
import java.util.Date;

public class ConvertDate {
	
	public static Date convertLocalDateInDate(LocalDate ld) {
		@SuppressWarnings("deprecation")
		Date dataConvertida = new Date(ld.getYear() - 1900, ld.getMonthValue() - 1, ld.getDayOfMonth());
		return dataConvertida;
	}
	public static LocalDate convertDateInLocalDate(Date data ) {
		@SuppressWarnings("deprecation")
		LocalDate dataConvertida = LocalDate.of( data.getYear()+1900, data.getMonth() + 1, data.getDate());
		return dataConvertida;
	}
}
