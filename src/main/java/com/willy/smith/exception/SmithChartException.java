package com.willy.smith.exception;

import static com.willy.smith.util.UtilConstant.DIVISION_BY_ZERO;

/**
 * Class Manage Custom Exceptions
 */
public class SmithChartException extends Exception {
	
	private String msg;
	
	private static final long serialVersionUID = 1L;

	public SmithChartException(String msg) {
		this.msg = msg;
		
		if (msg.equalsIgnoreCase(DIVISION_BY_ZERO))
			System.out.println("Smith Exception Handler: " + DIVISION_BY_ZERO);
		
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
