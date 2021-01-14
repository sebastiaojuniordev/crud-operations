package br.com.sebastiao.junior.infrastructure.exception;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1220868054700961742L;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Object... args) {
		super(MessageFormat.format(message, args));
	}
}
