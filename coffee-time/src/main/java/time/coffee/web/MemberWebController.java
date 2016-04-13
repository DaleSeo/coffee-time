package time.coffee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@Controller
@RequestMapping(value = "/web/member", method = RequestMethod.GET)
public class MemberWebController {

	@RequestMapping(value = "/{viewName}", method = RequestMethod.GET)
	public String view(@PathVariable String viewName) {
		return "member/" + viewName;
	}

}
