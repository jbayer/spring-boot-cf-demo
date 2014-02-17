@Grab("org.webjars:jquery:2.0.3-1") // this will automatically fetch jquery
@Grab("thymeleaf-spring4")

import org.springframework.ui.Model

@Controller
class Application {
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name)
		return "greeting"
	}
}
