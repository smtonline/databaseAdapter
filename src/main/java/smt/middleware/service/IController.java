package smt.middleware.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Action;

@WebService(targetNamespace="http://tempuri.org/",serviceName = "Controller")
public interface IController {
	@Action(
	         input="DataBus",
	         output="DataBusResponse")
	@WebMethod(operationName="DataBus")
	public String dataBus(@WebParam(name="xml") String xml);
	
	@WebMethod(operationName="BusEntry")
	@Action(
	         input="http://tempuri.org/IController/BusEntry",
	         output="http://tempuri.org/IController/BusEntryResponse")
	public String busEntry(@WebParam(name="xml") String xml);
}
