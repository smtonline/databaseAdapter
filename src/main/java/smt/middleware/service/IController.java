package smt.middleware.service;

import javax.jws.WebService;
import javax.xml.ws.Action;

@WebService
public interface IController {
	@Action(
	         input="http://tempuri.org/IController/DataBus",
	         output="http://tempuri.org/IController/DataBusResponse")
	public String dataBus(String xml);
	
	
	@Action(
	         input="http://tempuri.org/IController/BusEntry",
	         output="http://tempuri.org/IController/BusEntryResponse")
	public String busEntry(String xml);
}
