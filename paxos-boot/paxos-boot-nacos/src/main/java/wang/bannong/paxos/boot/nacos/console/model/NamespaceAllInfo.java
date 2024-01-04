package wang.bannong.paxos.boot.nacos.console.model;

public class NamespaceAllInfo extends Namespace {

	public NamespaceAllInfo(String namespace, String namespaceShowName, int quota, int configCount, int type,
			String namespaceDesc) {
		super(namespace, namespaceShowName, namespaceDesc, quota, configCount, type);
	}

}
