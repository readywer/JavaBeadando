package javatxteditor;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class Txtmanagement {

	public ArrayList<ArrayList<String>> readFile(String filePath) {
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(";");
				ArrayList<String> row = new ArrayList<>();
				for (String value : values) {
					row.add(value);
				}
				data.add(row);
			}
		} catch (IOException e) {
			System.err.println("Hiba történt a fájl olvasása közben: " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println("Hiba történt a fájl bezárása közben: " + e.getMessage());
				}
			}
		}
		return data;
	}

	private void writeFile(ArrayList<ArrayList<String>> data, String filePath) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			int i = 0;
			for (ArrayList<String> row : data) {
				String line = String.join(";", row);
				if (i != 0) {
					writer.newLine();
					writer.write(line);
				} else {
					writer.write(line);
				}
				i++;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dataAdd(ArrayList<String> row, String filePath) {
		try {
			ArrayList<ArrayList<String>> data = readFile(filePath);
			if (data.isEmpty()) {
				row.add(0, "1");
			} else {
				int lastId = Integer.parseInt(data.get(data.size() - 1).get(0));
				row.add(0, String.valueOf(lastId + 1));

			}
			data.add(row);
			writeFile(data, filePath);
			JOptionPane.showMessageDialog(null, "Sikeres adatfelivtel.\n");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Hiba történt a fájl olvasása során:nem sikerült számot alakítani a sor azonosítójából.\n"
							+ e.getMessage(),
					"Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void dataDelete(String filePath, int id) {
		ArrayList<ArrayList<String>> data = readFile(filePath);
		int rowToDelete = id;
		if (rowToDelete < 0 || rowToDelete >= data.size()) {
			JOptionPane.showMessageDialog(null, "Az adott ID-vel rendelkezõ sor nem található a fájlban.\n");
			return;
		}
		data.remove(rowToDelete);
		writeFile(data, filePath);
		JOptionPane.showMessageDialog(null, "Sikeres adat törlés.\n");
	}

	public void dataReplace(String filePath, int id, ArrayList<String> newRow) {
		ArrayList<ArrayList<String>> data = readFile(filePath);
		newRow.add(0, String.valueOf(data.get(id).get(0)));
		int rowToReplace = id;
		if (rowToReplace < 0 || rowToReplace >= data.size()) {
			JOptionPane.showMessageDialog(null, "Az adott ID-vel rendelkezõ sor nem található a fájlban.\n");
			return;
		}
		data.set(rowToReplace, newRow);
		writeFile(data, filePath);
		JOptionPane.showMessageDialog(null, "Sikeres adatváltozatás.\n");
	}

	public ArrayList<ArrayList<String>> searchData(String filePath, String searchText) {
		ArrayList<ArrayList<String>> data = readFile(filePath);
		ArrayList<ArrayList<String>> matchingRows = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> row : data) {
			for (String col : row) {
				if (col.contains(searchText)) {
					matchingRows.add(row);
					break;
				}
			}
		}
		if (matchingRows.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nincs ilyen adattal rendelkezõ sor a fájlban.\n");
		}
		return matchingRows;
	}

	public boolean validateData(ArrayList<String> row) {
		if (row.size() < 4) {
			JOptionPane.showMessageDialog(null, "Hiba!\n");
			return false;
		}
		String firstCol = row.get(0);
		String secondCol = row.get(1);
		String thirdCol = row.get(2);
		if (secondCol.isEmpty() || firstCol.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Hiba üres adatsorok!\n");
			return false;
		}

		if (thirdCol == null || !thirdCol.matches("\\d{4}\\.\\d{2}\\.\\d{2}")) {
			JOptionPane.showMessageDialog(null, "Hibás dátum!\n");
			return false;
		}

		return true;
	}

	public void writePDF(ArrayList<ArrayList<String>> data, String filePath) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			contentStream.beginText();
			// Set font and font size
			PDFont font = PDType0Font.load(document, new File("c:/windows/fonts/times.ttf"));
			contentStream.setFont(font, 12);
			// Set position for first line
			contentStream.newLineAtOffset(50, 700);

			// Iterate over data and write each row to the PDF
			int i = 0;
			for (ArrayList<String> row : data) {
				String line = String.join(";", row);
				if (i != 0) {
					contentStream.newLineAtOffset(0, -20);
					contentStream.showText(line);
				} else {
					contentStream.showText(line);
				}
				i++;
			}
			contentStream.endText();
			contentStream.close();

			// Save the PDF file
			document.save(filePath);
			JOptionPane.showMessageDialog(null, "Sikeres pdfbe kiírás.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void compressFile(ArrayList<ArrayList<String>> data, String filePath) {
		try {
			GZIPOutputStream outputStream = new GZIPOutputStream(new FileOutputStream(filePath));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			int i = 0;
			for (ArrayList<String> row : data) {
				String line = String.join(";", row);
				if (i != 0) {
					writer.newLine();
					writer.write(line);
				} else {
					writer.write(line);
				}
				i++;
			}
			writer.close();
			JOptionPane.showMessageDialog(null, "Sikeres tömörített kiírás.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
