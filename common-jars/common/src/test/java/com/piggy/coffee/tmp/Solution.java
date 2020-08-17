package com.piggy.coffee.tmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public int rand10() {
		//bashCommand("ls -l");
		File directory = new File("");
		try {
			System.out.println(this.getClass().getResource("/").getPath());
			String path = this.getClass().getClassLoader().getResources("com/piggy/coffee/tmp/Solution.class")
					.nextElement().getPath();
			System.out.println(path);
			System.out.println(directory.getAbsolutePath());
		} catch (Exception e) {
			System.out.println(e);
		}
		return 1;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		s.rand10();
	}

	public void bashCommand(String command) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			process.destroyForcibly();
		}

	}
}