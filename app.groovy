@Grab("org.webjars:jquery:2.0.3-1") // this will automatically fetch jquery
@Grab("thymeleaf-spring4")

@Controller
class Application {

	@RequestMapping("/")
	public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

		model.addAttribute("name", name)

		String index = "-"
		if( System.getenv("INSTANCE_INDEX") != null )
			index = System.getenv("INSTANCE_INDEX")
                if( System.getenv("CF_INSTANCE_INDEX") != null )
                        index = System.getenv("CF_INSTANCE_INDEX")
		model.addAttribute("index", index)

		java.lang.management.RuntimeMXBean rb = java.lang.management.ManagementFactory.getRuntimeMXBean()
		long uptime = rb.getUptime()
		model.addAttribute("uptime", uptime)

		return "index"
	}

@RequestMapping("/env")
public String index(Model model) {

	model.addAttribute("env", System.getenv() )

	return "env"
}


	@RequestMapping("/exit")
	public String exit(Model model) {
		System.exit(1);

		return "exit"
	}

}
