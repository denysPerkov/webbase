package filter.security;

import model.Role;
import model.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityManager {

	private final HashMap<Role, Pattern> constraintsPatternList;

	public SecurityManager(ArrayList<Constraints> constraintsList) {
		Pattern pattern;
		constraintsPatternList = new HashMap<>();

		for (Constraints constraints : constraintsList) {
			pattern = Pattern.compile(constraints.getUrlPattern());
			constraintsPatternList.put(constraints.getRole(), pattern);
		}

	}

	public boolean containInConstraints(String requestPath) {

		if (requestPath == null) {
			return false;
		}
		for (Entry<Role, Pattern> constraintsPatternItem : constraintsPatternList.entrySet()) {
			if (constraintsPatternItem.getValue().matcher(requestPath).find()) {
				return true;
			}
		}
		System.out.println("!!!");
		return false;
	}

	public boolean checkConstraintForRole(User user, String requestPath) {
		for (Entry<Role, Pattern> constraintsPatternItem : constraintsPatternList.entrySet()) {
			Matcher matcher = constraintsPatternItem.getValue().matcher(requestPath);
			if (matcher.find() && (constraintsPatternItem.getKey() == user.getRole())) {
				return true;
			}
		}
		return false;
	}

}
