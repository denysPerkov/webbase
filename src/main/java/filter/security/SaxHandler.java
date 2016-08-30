package filter.security;

import java.util.ArrayList;

import model.Role;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxHandler extends DefaultHandler {

	private final ArrayList<Constraints> constraints = new ArrayList<>();
	private Constraints constrainElement;
	private String node;

	@Override
	public void startDocument() throws SAXException {
		constrainElement = new Constraints();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		node = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length).trim();
		if (value == null || value.isEmpty()) {
			return;
		}
		switch (node) {
			case "role":
				constrainElement.setRole(Role.valueOf(value.toUpperCase()));
				break;
			case "url-pattern":
				constrainElement.setUrlPattern(value);
				break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (constrainElement.getRole() != null && constrainElement.getUrlPattern() != null) {
			constraints.add(constrainElement);
			constrainElement = new Constraints();
			node = null;
		}
	}

	public ArrayList<Constraints> getConstraintsList() {
		return constraints;
	}
}
