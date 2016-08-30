package filter.security;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLSecurityParser {

	private final SAXParserFactory saxParserFactory;
	private final SAXParser saxParser;

	public XMLSecurityParser() throws SAXException, IOException, ParserConfigurationException {
		saxParserFactory = SAXParserFactory.newInstance();
		saxParser = saxParserFactory.newSAXParser();
	}

	public ArrayList<Constraints> parse(String xmlSecurityFile) throws SAXException, IOException {
		SaxHandler saxHandler = new SaxHandler();
		saxParser.parse(new File(xmlSecurityFile), saxHandler);
		List<Constraints> empList = saxHandler.getConstraintsList();
		return saxHandler.getConstraintsList();
	}
}
