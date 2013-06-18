package smt.middleware.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Action;

@WebService(name="IController",serviceName="Controller",targetNamespace="http://tempuri.org/",portName="Controller")
public interface Controller {
	
	@WebMethod(operationName="DataBus")
	public @Action(input="http://tempuri.org/IController/DataBus", output="http://tempuri.org/IController/DataBusResponse")
	   		String dataBus(@WebParam(name="xml", targetNamespace="http://tempuri.org/") String xml);
	
	
	
	@WebMethod(operationName="BusEntry")
	public @Action(input="http://tempuri.org/IController/BusEntry", output="http://tempuri.org/IController/BusEntryResponse") 
			String busEntry(@WebParam(name="xml",targetNamespace="http://tempuri.org/") String xml);
}
