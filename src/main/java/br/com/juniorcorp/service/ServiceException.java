package br.com.juniorcorp.service;

/**
 * 
 * @author Junior
 * 
 *  Classe de exceção usada informar que houve erro na camada de serviço. 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 376541825245549673L;
	
	public ServiceException(String msg) {
		super(msg);
	}
	
}
