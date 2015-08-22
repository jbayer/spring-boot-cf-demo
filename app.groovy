@Grab("org.webjars:jquery:2.0.3-1") // this will automatically fetch jquery
@Grab("thymeleaf-spring4")

@Controller
class Application {

	@RequestMapping("/")
	public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

		model.addAttribute("name", name)
		model.addAttribute("index", findIndex())

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

    model.addAttribute("index", findIndex())
		try{
			new Thread(new Runnable() {

					@Override
					public void run() {
					  Thread.sleep(500);
						System.exit(1);
					}
			}).start();
		}
		catch(Exception e){}

		return "exit"
	}

	private String findIndex()
	{
			String index = "-"

			if( System.getenv("INSTANCE_INDEX") != null )
				index = System.getenv("INSTANCE_INDEX")
	    if( System.getenv("CF_INSTANCE_INDEX") != null )
	    	index = System.getenv("CF_INSTANCE_INDEX")

			return index;
	}
}
