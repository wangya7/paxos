package wang.bannong.paxos.boot.nacos.console.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.NamespaceUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.config.server.model.TenantInfo;
import com.alibaba.nacos.config.server.service.repository.CommonPersistService;
import com.alibaba.nacos.config.server.service.repository.ConfigInfoPersistService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import wang.bannong.paxos.boot.nacos.console.enums.NamespaceTypeEnum;
import wang.bannong.paxos.boot.nacos.console.model.Namespace;
import wang.bannong.paxos.boot.nacos.console.model.NamespaceAllInfo;

@Service
public class NamespaceOperationService {

	private final ConfigInfoPersistService configInfoPersistService;

	private final CommonPersistService commonPersistService;

	private static final String DEFAULT_NAMESPACE = "public";

	private static final String DEFAULT_NAMESPACE_SHOW_NAME = "Public";

	private static final String DEFAULT_NAMESPACE_DESCRIPTION = "Public Namespace";

	private static final int DEFAULT_QUOTA = 200;

	private static final String DEFAULT_CREATE_SOURCE = "nacos";

	private static final String DEFAULT_TENANT = "";

	private static final String DEFAULT_KP = "1";

	public NamespaceOperationService(ConfigInfoPersistService configInfoPersistService,
			CommonPersistService commonPersistService) {
		this.configInfoPersistService = configInfoPersistService;
		this.commonPersistService = commonPersistService;
	}

	public List<Namespace> getNamespaceList() {
		// TODO 获取用kp
		List<TenantInfo> tenantInfos = commonPersistService.findTenantByKp(DEFAULT_KP);

		Namespace namespace0 = new Namespace(NamespaceUtil.getNamespaceDefaultId(), DEFAULT_NAMESPACE, DEFAULT_QUOTA,
				configInfoPersistService.configInfoCount(DEFAULT_TENANT), NamespaceTypeEnum.GLOBAL.getType());
		List<Namespace> namespaceList = new ArrayList<>();
		namespaceList.add(namespace0);

		for (TenantInfo tenantInfo : tenantInfos) {
			int configCount = configInfoPersistService.configInfoCount(tenantInfo.getTenantId());
			Namespace namespaceTmp = new Namespace(tenantInfo.getTenantId(), tenantInfo.getTenantName(),
					tenantInfo.getTenantDesc(), DEFAULT_QUOTA, configCount, NamespaceTypeEnum.CUSTOM.getType());
			namespaceList.add(namespaceTmp);
		}
		return namespaceList;
	}

	/**
	 * query namespace by namespace id.
	 * @param namespaceId namespace Id.
	 * @return NamespaceAllInfo.
	 */
	public NamespaceAllInfo getNamespace(String namespaceId) throws NacosException {
		// TODO 获取用kp
		if (StringUtils.isBlank(namespaceId) || namespaceId.equals(NamespaceUtil.getNamespaceDefaultId())) {
			return new NamespaceAllInfo(namespaceId, DEFAULT_NAMESPACE_SHOW_NAME, DEFAULT_QUOTA,
					configInfoPersistService.configInfoCount(DEFAULT_TENANT), NamespaceTypeEnum.GLOBAL.getType(),
					DEFAULT_NAMESPACE_DESCRIPTION);
		}
		else {
			TenantInfo tenantInfo = commonPersistService.findTenantByKp(DEFAULT_KP, namespaceId);
			if (null == tenantInfo) {
				throw new NacosApiException(HttpStatus.NOT_FOUND.value(), ErrorCode.NAMESPACE_NOT_EXIST,
						"namespaceId [ " + namespaceId + " ] not exist");
			}
			int configCount = configInfoPersistService.configInfoCount(namespaceId);
			return new NamespaceAllInfo(namespaceId, tenantInfo.getTenantName(), DEFAULT_QUOTA, configCount,
					NamespaceTypeEnum.CUSTOM.getType(), tenantInfo.getTenantDesc());
		}
	}

	/**
	 * create namespace.
	 * @param namespaceId namespace ID
	 * @param namespaceName namespace Name
	 * @param namespaceDesc namespace Desc
	 * @return whether create ok
	 */
	public Boolean createNamespace(String namespaceId, String namespaceName, String namespaceDesc)
			throws NacosException {
		// TODO 获取用kp
		if (commonPersistService.tenantInfoCountByTenantId(namespaceId) > 0) {
			throw new NacosApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.NAMESPACE_ALREADY_EXIST,
					"namespaceId [" + namespaceId + "] already exist");
		}

		commonPersistService.insertTenantInfoAtomic(DEFAULT_KP, namespaceId, namespaceName, namespaceDesc,
				DEFAULT_CREATE_SOURCE, System.currentTimeMillis());
		return true;
	}

	/**
	 * edit namespace.
	 */
	public Boolean editNamespace(String namespaceId, String namespaceName, String namespaceDesc) {
		// TODO 获取用kp
		commonPersistService.updateTenantNameAtomic(DEFAULT_KP, namespaceId, namespaceName, namespaceDesc);
		return true;
	}

	/**
	 * remove namespace.
	 */
	public Boolean removeNamespace(String namespaceId) {
		commonPersistService.removeTenantInfoAtomic(DEFAULT_KP, namespaceId);
		return true;
	}

}
