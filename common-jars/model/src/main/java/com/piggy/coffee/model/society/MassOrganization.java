package com.piggy.coffee.model.society;

import java.util.ArrayList;
import java.util.List;

public class MassOrganization {
	private Citizen leader;

	private List<Citizen> members = new ArrayList<>();

	public List<Citizen> getMembers() {
		return members;
	}

	public void setMembers(List<Citizen> members) {
		this.members = members;
	}

	public void addMember(Citizen citizen) {
		this.members.add(citizen);
	}

	public Citizen getLeader() {
		return leader;
	}

	public void setLeader(Citizen leader) {
		this.leader = leader;
	}

}
