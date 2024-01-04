package wang.bannong.paxos.boot.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.bannong.paxos.boot.nacos.config.NacosServerConstants;

/**
 * 启动类
 *
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2024/1/4
 */
@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.alibaba.nacos", "wang.bannong.paxos.boot.nacos"})
public class NacosServerApplication {
    public static void main(String[] args) {
        if (initEnv()) {
            SpringApplication.run(NacosServerApplication.class, args);
        }
    }

    /**
     * 初始化运行环境
     */
    private static boolean initEnv() {
        System.setProperty(NacosServerConstants.STANDALONE_MODE, "true");
        System.setProperty(NacosServerConstants.AUTH_ENABLED, "true");
        System.setProperty(NacosServerConstants.LOG_BASEDIR, "logs");
        System.setProperty(NacosServerConstants.LOG_ENABLED, "false");
        System.setProperty(NacosServerConstants.NACOS_CONTEXT_PATH, "/nacos");
        return true;
    }
}
