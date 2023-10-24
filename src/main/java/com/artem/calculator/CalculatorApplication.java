package com.artem.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class CalculatorApplication {
	private static ConfigurableApplicationContext ctx = null;

	public static void main(String[] args) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(URI.create("http://localhost:8080/calculator"));
				}
			}
		} catch (IOException | InternalError e) {
			e.printStackTrace();
		}
		ctx = SpringApplication.run(CalculatorApplication.class, args);
	}

	public static void exitApplication() {
		ctx.close();
		System.exit(0);
	}
}
