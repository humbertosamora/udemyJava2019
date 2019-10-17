package aula100Calendar;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* Objeto Calendar:
 * - Usado para manipular objetos do tipo data
 * - Possibilita somar/subtrair minutos, horas, dias ou obter dia, mês, anos, etec.
 * 
 * */

public class Main {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		// Timezone ajustado para 0:00 (GMT)
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Date d = Date.from(Instant.parse("2018-06-25T15:42:07Z"));
		
		System.out.println(sdf.format(d));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		int minutes = cal.get(Calendar.MINUTE);
		System.out.println("Minutes: " + minutes);
		
		// Os meses começam em "0" no objeto Calendar
		int month = 1 + cal.get(Calendar.MONTH);
		System.out.println("Month: " + month);

		// Os meses começam em "0" no objeto Calendar
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("Day of month: " + day);
		
		// Adiciona 4 horas à data
		cal.add(Calendar.HOUR_OF_DAY, 4);
		d = cal.getTime();
		System.out.println(sdf.format(d));
		
	}

}
