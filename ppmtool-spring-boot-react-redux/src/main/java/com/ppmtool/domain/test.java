package com.ppmtool.domain;

public class test {

	public static void main(String[] args) {
		Backlogs backlogs = new Backlogs();
		projects projects = new projects();
		projects.setBacklogs(backlogs);
		backlogs.setName("Rajan");
		System.out.println(projects.getBacklogs().getName());
	}

}

class projects {
	private Backlogs Backlogs;

	public Backlogs getBacklogs() {
		return Backlogs;
	}

	public void setBacklogs(Backlogs backlogs) {
		Backlogs = backlogs;
	}

}

class Backlogs {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
