package wang.bannong.paxos.boot.nacos.console.controller;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.nacos.common.model.RestResult;
import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateHolder;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/console/server")
public class ServerStateController {

	private static final String ANNOUNCEMENT_FILE = "conf/announcement.conf";

	/**
	 * Get server state of current server.
	 * @return state json.
	 */
	@GetMapping("/state")
	public ResponseEntity<Map<String, String>> serverState() {
		Map<String, String> serverState = new HashMap<>(4);
		for (ModuleState each : ModuleStateHolder.getInstance().getAllModuleStates()) {
			each.getStates().forEach((s, o) -> serverState.put(s, null == o ? null : o.toString()));
		}
		return ResponseEntity.ok().body(serverState);
	}

	@SneakyThrows
	@GetMapping("/announcement")
	public RestResult<String> getAnnouncement() {
		return RestResultUtils.success();
	}

}
