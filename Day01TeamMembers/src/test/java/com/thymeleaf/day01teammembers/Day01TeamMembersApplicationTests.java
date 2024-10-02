package com.thymeleaf.day01teammembers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootTest
class Day01TeamMembersApplicationTests {


	static String fileName = "teams.txt";

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> playersByTeams = new HashMap<>();

		try (Scanner reader = new Scanner(new File(fileName))) {
			while (reader.hasNext()) {
				String line = reader.nextLine();
				// for debugging
				// System.out.println(line);
				String[] parts = line.split(":");
				if (parts.length == 2) {
					String teamName = parts[0].trim();
					String[] players = parts[1].split(",");

					for (String player : players) {
						player = player.trim();
						playersByTeams.putIfAbsent(player, new ArrayList<>());
						playersByTeams.get(player).add(teamName);
					}
				}

			}

        } catch (IOException e) {
			System.out.println(e.getMessage());
		}

		for (String player : playersByTeams.keySet()) {
			ArrayList<String> teams = playersByTeams.get(player);
			System.out.println(player + " plays in: " + String.join(", ", teams));
		}

    }

}
