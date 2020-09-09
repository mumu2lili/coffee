package com.piggy.coffee.license.model;

import java.util.ArrayList;
import java.util.List;

public class BridgeGrantInfo {
	private List<BridgeHost> bridgeHosts = new ArrayList<>();

	public List<BridgeHost> getBridgeHosts() {
		return bridgeHosts;
	}

	public void setBridgeHosts(List<BridgeHost> bridgeHosts) {
		this.bridgeHosts = bridgeHosts;
	}
	
	public void addBridgeHost(BridgeHost bridgeHost) {
		this.bridgeHosts.add(bridgeHost);
	}

}
