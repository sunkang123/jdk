package com.java.jaxp.xstream.tutorial;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@XStreamAlias("message")
public class RendezvousMessage {

	@XStreamAlias("type")
	private int messageType;

	@XStreamImplicit(itemFieldName = "part")
	private List<String> content;

	@XStreamAsAttribute
	@XStreamConverter(value=BooleanConverter.class, booleans={false}, strings={"yes", "no"})
	private boolean important;

	@XStreamConverter(SingleValueCalendarConverter.class)
	private Calendar created = new GregorianCalendar();

	public RendezvousMessage(int messageType, boolean important, String... content) {
		this.messageType = messageType;
		this.important = important;
		this.content = Arrays.asList(content);
	}
}