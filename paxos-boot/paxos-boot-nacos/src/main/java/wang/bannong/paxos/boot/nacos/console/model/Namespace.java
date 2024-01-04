package wang.bannong.paxos.boot.nacos.console.model;

import wang.bannong.paxos.boot.nacos.console.enums.NamespaceTypeEnum;

public class Namespace {

	private String namespace;

	private String namespaceShowName;

	private String namespaceDesc;

	private int quota;

	private int configCount;

	/**
	 * see {@link NamespaceTypeEnum}.
	 */
	private int type;

	public String getNamespaceShowName() {
		return namespaceShowName;
	}

	public void setNamespaceShowName(String namespaceShowName) {
		this.namespaceShowName = namespaceShowName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Namespace() {
	}

	public Namespace(String namespace, String namespaceShowName) {
		this.namespace = namespace;
		this.namespaceShowName = namespaceShowName;
	}

	public Namespace(String namespace, String namespaceShowName, int quota, int configCount, int type) {
		this.namespace = namespace;
		this.namespaceShowName = namespaceShowName;
		this.quota = quota;
		this.configCount = configCount;
		this.type = type;
	}

	public Namespace(String namespace, String namespaceShowName, String namespaceDesc, int quota, int configCount,
			int type) {
		this.namespace = namespace;
		this.namespaceShowName = namespaceShowName;
		this.quota = quota;
		this.configCount = configCount;
		this.type = type;
		this.namespaceDesc = namespaceDesc;
	}

	public String getNamespaceDesc() {
		return namespaceDesc;
	}

	public void setNamespaceDesc(String namespaceDesc) {
		this.namespaceDesc = namespaceDesc;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getConfigCount() {
		return configCount;
	}

	public void setConfigCount(int configCount) {
		this.configCount = configCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
