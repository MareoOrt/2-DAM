package com.gf.demoweb.jps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaConstructor {

	@GetMapping({ "/", "/hola" })
	public String mostrarPagina() {
		return "hola";
	}
}
