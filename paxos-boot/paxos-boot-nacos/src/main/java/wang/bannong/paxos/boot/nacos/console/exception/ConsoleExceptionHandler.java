package wang.bannong.paxos.boot.nacos.console.exception;

import javax.servlet.http.HttpServletRequest;


import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.alibaba.nacos.core.utils.Commons;
import com.alibaba.nacos.plugin.auth.exception.AccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConsoleExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleExceptionHandler.class);

	@ExceptionHandler(AccessException.class)
	private ResponseEntity<String> handleAccessException(AccessException e) {
		LOGGER.error("got exception. {}", e.getErrMsg());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrMsg());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtil.getAllExceptionMsg(e));
	}

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) {
		String uri = request.getRequestURI();
		LOGGER.error("CONSOLE {}", uri, e);
		if (uri.contains(Commons.NACOS_SERVER_VERSION_V2)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(RestResultUtils.failed(ExceptionUtil.getAllExceptionMsg(e)));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtil.getAllExceptionMsg(e));
	}

}
