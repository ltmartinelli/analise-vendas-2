package application;

import model.entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Set;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.println("Entre o caminho do arquivo: ");
		String sourceFileStr = sc.nextLine();

		// Reading File and Creating List

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			List<Sale> saleList = new ArrayList<>();

			String item = br.readLine();

			while (item != null) {

				String[] fields = item.split(",");
				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);

				saleList.add(new Sale(month, year, seller, items, total));

				item = br.readLine();
			}

			Set<String> names = saleList.stream().map(x -> x.getSeller()).collect(Collectors.toSet());

			System.out.println("Total de Vendas por Vendedor");

			for (String name : names) {

				Double sum = saleList.stream().filter(x -> x.getSeller().equals(name)).mapToDouble(x -> x.getTotal())
						.reduce(0, (x, y) -> x + y);

				System.out.print(name + " - R$ ");
				System.out.printf("%.2f", sum);
				System.out.println();

			}

		}

		catch (java.io.IOException e) {
			System.out.println("Erro: " + sourceFileStr + " (O sistema não pôde encontrar o caminho especificado)");
		}

		finally {
			if (sc != null) {
				sc.close();
			}

		}

	}

}
