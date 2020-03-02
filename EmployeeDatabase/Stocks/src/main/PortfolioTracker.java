package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PortfolioTracker {

	public static void main(String[] args) throws Exception {

		File file = new File("C:\\Users\\akaash.h\\Desktop\\stocks.txt");
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		Map<Double, String> map = new HashMap<>();
		Map<Double, String> result2 = new LinkedHashMap<>();

		while ((line = br.readLine()) != null) {
			Reader inputString = new StringReader(line);
			BufferedReader reader = new BufferedReader(inputString);
			double totalValue = sumNumber(reader);

			map.put(totalValue, line);

		}

		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.<Double, String>comparingByKey()))
				.forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

		for (String str : result2.values()) {
			System.out.println(str);
		}

	}

	private static double sumNumber(BufferedReader br) throws IOException {
		StreamTokenizer stok = new StreamTokenizer(br);
		stok.parseNumbers();
		double sum = 0;
		stok.nextToken();
		while (stok.ttype != StreamTokenizer.TT_EOF) {
			if (stok.ttype == StreamTokenizer.TT_NUMBER) {
				sum += stok.nval;

			}

			stok.nextToken();

		}
		return sum;
	}

}
