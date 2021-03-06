/**
 * BarangUserSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.frexesc.soap;

import com.frexesc.Constants;

public class BarangUserSoapServiceLocator extends
		org.apache.axis.client.Service implements
		com.frexesc.soap.BarangUserSoapService {
	
	// Use to get a proxy class for BarangUserSoap
	private java.lang.String BarangUserSoap_address = Constants.HOSTNAME + "services/BarangUserSoap";

	public BarangUserSoapServiceLocator() {
	}

	public BarangUserSoapServiceLocator(
			org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public BarangUserSoapServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	public java.lang.String getBarangUserSoapAddress() {
		return BarangUserSoap_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String BarangUserSoapWSDDServiceName = "BarangUserSoap";

	public java.lang.String getBarangUserSoapWSDDServiceName() {
		return BarangUserSoapWSDDServiceName;
	}

	public void setBarangUserSoapWSDDServiceName(java.lang.String name) {
		BarangUserSoapWSDDServiceName = name;
	}

	public com.frexesc.soap.BarangUserSoap getBarangUserSoap()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(BarangUserSoap_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getBarangUserSoap(endpoint);
	}

	public com.frexesc.soap.BarangUserSoap getBarangUserSoap(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.frexesc.soap.BarangUserSoapSoapBindingStub _stub = new com.frexesc.soap.BarangUserSoapSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getBarangUserSoapWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setBarangUserSoapEndpointAddress(java.lang.String address) {
		BarangUserSoap_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (com.frexesc.soap.BarangUserSoap.class
					.isAssignableFrom(serviceEndpointInterface)) {
				com.frexesc.soap.BarangUserSoapSoapBindingStub _stub = new com.frexesc.soap.BarangUserSoapSoapBindingStub(
						new java.net.URL(BarangUserSoap_address), this);
				_stub.setPortName(getBarangUserSoapWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("BarangUserSoap".equals(inputPortName)) {
			return getBarangUserSoap();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName(
				"http://soap.frexescwebservice.com", "BarangUserSoapService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName(
					"http://soap.frexescwebservice.com", "BarangUserSoap"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("BarangUserSoap".equals(portName)) {
			setBarangUserSoapEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
