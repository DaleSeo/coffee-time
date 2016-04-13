package time.coffee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@Controller
@RequestMapping(value = "/web/member", method = RequestMethod.GET)
public class MemberWebController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String hello() {
		return "member/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "member/create";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail() {
		return "member/detail";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify() {
		return "member/modify";
	}

}
