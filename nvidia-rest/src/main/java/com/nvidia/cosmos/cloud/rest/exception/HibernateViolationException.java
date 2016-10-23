package com.nvidia.cosmos.cloud.rest.exception;

import com.nvidia.cosmos.cloud.exceptions.BaseException;

public class HibernateViolationException extends BaseException {
	 
		private static final long serialVersionUID = 1L;

		public HibernateViolationException(String message) {
			super(message);
		}

		public HibernateViolationException(String message, Throwable cause) {
			super(message, cause);
		}
}
