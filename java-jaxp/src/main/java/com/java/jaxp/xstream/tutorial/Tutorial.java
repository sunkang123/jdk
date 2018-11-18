package com.java.jaxp.xstream.tutorial;

import com.thoughtworks.xstream.XStream;

public class Tutorial {

	public static void main(String[] args) {
		XStream xstream = new XStream();
		xstream.processAnnotations(RendezvousMessage.class);
		RendezvousMessage msg = new RendezvousMessage(15,false, "firstPart","secondPart");
		System.out.println(xstream.toXML(msg));
	}

}