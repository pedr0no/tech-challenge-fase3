package br.com.fiap.techchallenge.tablereservation.main;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {

	public static URI getLocation(String path) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand().toUri();
	}

	public static DateTimeFormatter formatDate(String formatter) {
		return DateTimeFormatter.ofPattern(formatter);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isValidList(List list) {
		return Objects.nonNull(list) && !list.isEmpty();
	}

	@SuppressWarnings("null")
	public static boolean isValidString(String string) {
		return string != null && !string.isBlank();
	}

}
