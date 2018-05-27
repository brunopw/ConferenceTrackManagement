package br.com.brunopw.conferencetrackmanagement.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	public List readListInFile(String filePath) throws IOException {
		List list = new ArrayList<>();

		Path path = Paths.get(filePath);
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);

		list = lines.collect(Collectors.toList());
		lines.close();
		// linhas.forEach(System.out::println);

		return list;
	}

}
