package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import javatxteditor.Txtmanagement;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField placeField;
	private JTextField timeField;
	private JTextField nevField;
	private JTextField placeField2;
	private JTextField timeField2;
	private JTextField nevField2;
	static Txtmanagement txt = new Txtmanagement();
	private String file = "./src/data/data.txt";
	private String file3 = "./src/data/compressed.txt";
	private String file2 = "./src/data/datas.pdf";
	List<String> adatok = new ArrayList<>();
	private int ID;
	private int ID2;
	String[] values = adatok.toArray(new String[0]);
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel dataAdd = new JPanel();
		dataAdd.setForeground(Color.BLACK);

		tabbedPane.addTab("Adatfelvitel", dataAdd);
		dataAdd.setLayout(null);
		
		JList<String> list0 = new JList<String>();
		list0.setBounds(0, 0, 445, 249);

		JScrollPane scrollPane0 = new JScrollPane();
		scrollPane0.setSize(470, 300);
		scrollPane0.setLocation(10, 80);
		scrollPane0.setViewportView(list0);
		list0.setLayoutOrientation(JList.VERTICAL);
		fillJList(list0, file);
		dataAdd.add(scrollPane0);

		JLabel lblNewLabel = new JLabel("N\u00E9v");
		lblNewLabel.setBounds(10, 10, 60, 20);
		dataAdd.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sz\u00FClet\u00E9si hely");
		lblNewLabel_1.setBounds(100, 10, 80, 20);
		dataAdd.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sz\u00FClet\u00E9si id\u0151");
		lblNewLabel_2.setBounds(200, 10, 80, 20);
		dataAdd.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nem");
		lblNewLabel_3.setBounds(300, 10, 40, 20);
		dataAdd.add(lblNewLabel_3);

		nevField = new JTextField();
		nevField.setColumns(10);
		nevField.setBounds(10, 40, 70, 20);

		dataAdd.add(nevField);

		placeField = new JTextField();
		placeField.setBounds(100, 40, 80, 20);
		dataAdd.add(placeField);
		placeField.setColumns(10);

		timeField = new JTextField();
		timeField.setBounds(200, 40, 80, 20);
		dataAdd.add(timeField);
		timeField.setColumns(10);

		JComboBox<String> Nem = new JComboBox<String>();
		Nem.setToolTipText("Nem");
		Nem.setBounds(300, 40, 60, 20);
		Nem.addItem("Férfi");
		Nem.addItem("Nõ");
		dataAdd.add(Nem);

		JButton dataAddConfirm = new JButton("Hozz\u00E1ad");
		dataAddConfirm.setBounds(380, 40, 90, 20);
		dataAdd.add(dataAddConfirm);

		JPanel dataEdit = new JPanel();
		tabbedPane.addTab("Adat módosítás", dataEdit);
		dataEdit.setLayout(null);

		JList<String> list = new JList<String>();
		fillJList(list, file);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					ID = list.getSelectedIndex();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(470, 300);
		scrollPane.setLocation(10, 80);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		dataEdit.add(scrollPane);

		list.setBounds(10, 92, 474, 174);

		JLabel lblNewLabel_4 = new JLabel("N\u00E9v");
		lblNewLabel_4.setBounds(10, 10, 60, 20);
		dataEdit.add(lblNewLabel_4);

		JLabel lblNewLabel_1_1 = new JLabel("Sz\u00FClet\u00E9si hely");
		lblNewLabel_1_1.setBounds(100, 10, 80, 20);
		dataEdit.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Sz\u00FClet\u00E9si id\u0151");
		lblNewLabel_2_1.setBounds(200, 10, 80, 20);
		dataEdit.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel("Nem");
		lblNewLabel_3_1.setBounds(300, 10, 40, 20);
		dataEdit.add(lblNewLabel_3_1);

		nevField2 = new JTextField();
		nevField2.setColumns(10);
		nevField2.setBounds(10, 40, 70, 20);
		dataEdit.add(nevField2);

		placeField2 = new JTextField();
		placeField2.setColumns(10);
		placeField2.setBounds(100, 40, 80, 20);
		dataEdit.add(placeField2);

		timeField2 = new JTextField();
		timeField2.setColumns(10);
		timeField2.setBounds(200, 40, 80, 20);
		dataEdit.add(timeField2);

		JComboBox<String> Nem2 = new JComboBox<String>();
		Nem2.setToolTipText("Nem");
		Nem2.setBounds(300, 40, 60, 20);
		Nem2.addItem("Férfi");
		Nem2.addItem("Nõ");
		dataEdit.add(Nem2);

		JButton btnNewButton_2 = new JButton("\u00C1t\u00EDr");
		btnNewButton_2.setBounds(380, 40, 90, 20);
		dataEdit.add(btnNewButton_2);

		JPanel dataDelet = new JPanel();
		tabbedPane.addTab("Adat törlése", dataDelet);
		dataDelet.setLayout(null);

		JList<String> list2 = new JList<String>();
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					ID2 = list2.getSelectedIndex();
				}
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Jel\u00F6lje ki a t\u00F6rlend\u0151 elemet:");
		lblNewLabel_6.setBounds(10, 10, 180, 20);
		dataDelet.add(lblNewLabel_6);
		list2.setBounds(0, 0, 445, 249);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setSize(470, 300);
		scrollPane2.setLocation(10, 40);
		scrollPane2.setViewportView(list2);
		list2.setLayoutOrientation(JList.VERTICAL);
		fillJList(list2, file);
		dataDelet.add(scrollPane2);

		JButton btnNewButton = new JButton("T\u00F6rl\u00E9s");

		btnNewButton.setBounds(390, 350, 90, 20);
		dataDelet.add(btnNewButton);

		JPanel dataGet = new JPanel();
		tabbedPane.addTab("Adat lekérdezés", dataGet);
		dataGet.setLayout(null);

		search = new JTextField();
		search.setBounds(10, 40, 100, 20);
		dataGet.add(search);
		search.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Keresett sz\u00F3:");
		lblNewLabel_5.setBounds(10, 10, 80, 20);
		dataGet.add(lblNewLabel_5);

		JButton btnNewButton_1 = new JButton("Keres");
		btnNewButton_1.setBounds(135, 38, 85, 21);
		dataGet.add(btnNewButton_1);

		JList<String> list3 = new JList<String>();
		list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list3.setBounds(10, 68, 352, 190);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setSize(470, 300);
		scrollPane3.setLocation(10, 80);
		scrollPane3.setViewportView(list3);
		list3.setLayoutOrientation(JList.VERTICAL);
		dataGet.add(scrollPane3);

		getContentPane().add(tabbedPane);

		JPanel pdfWrite = new JPanel();
		pdfWrite.setLayout(null);
		tabbedPane.addTab("Pdfbe mentés", null, pdfWrite, null);

		JButton btnNewButton_1_1 = new JButton("Pdf Ment\u00E9s");

		btnNewButton_1_1.setBounds(10, 10, 110, 20);
		pdfWrite.add(btnNewButton_1_1);

		JPanel compressedWrite = new JPanel();
		compressedWrite.setLayout(null);
		tabbedPane.addTab("Tömörített mentés", null, compressedWrite, null);

		JButton btnNewButton_1_1_1 = new JButton("T\u00F6m\u00F6r\u00EDtett Ment\u00E9s(gzip)");

		btnNewButton_1_1_1.setBounds(10, 10, 180, 20);
		compressedWrite.add(btnNewButton_1_1_1);

		setTitle("TXT kezelõ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);

		dataAddConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> input1 = new ArrayList<String>();

				input1.add(nevField.getText());
				input1.add(placeField.getText());
				input1.add(timeField.getText());
				input1.add((String) Nem.getSelectedItem());
				if (txt.validateData(input1)) {
					txt.dataAdd(input1, file);
				} else {
					JOptionPane.showMessageDialog(null, "Hiba történt a felvitel során!\n");
				}
				fillJList(list0, file);
				fillJList(list, file);
				fillJList(list2, file);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> input2 = new ArrayList<String>();

				input2.add(nevField2.getText());
				input2.add(placeField2.getText());
				input2.add(timeField2.getText());
				input2.add((String) Nem2.getSelectedItem());
				if (txt.validateData(input2)) {
					txt.dataReplace(file, ID, input2);
				} else {
					JOptionPane.showMessageDialog(null, "Hiba történt a módosítás során!\n");
				}
				fillJList(list0, file);
				fillJList(list, file);
				fillJList(list2, file);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.dataDelete(file, ID2);
				fillJList(list0, file);
				fillJList(list, file);
				fillJList(list2, file);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillJListSearch(list3, txt.searchData(file, search.getText()));

			}
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.writePDF(txt.readFile(file), file2);
			}
		});
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.compressFile(txt.readFile(file), file3);
			}
		});
	}
	public void fillJListSearch(JList<String> list, ArrayList<ArrayList<String>> data) {
		DefaultListModel<String> model = new DefaultListModel<>();
		int columnCount = 5;
		for (ArrayList<String> row : data) {
			StringBuilder rowStrBuilder = new StringBuilder();

			for (int i = 0; i < columnCount; i++) {
				rowStrBuilder.append(row.get(i));
				if (i < columnCount - 1) {
					rowStrBuilder.append(", ");
				}
			}
			model.addElement(rowStrBuilder.toString());
		}
		list.setModel(model);
	}

	public void fillJList(JList<String> list, String filePath) {
		DefaultListModel<String> model = new DefaultListModel<>();
		ArrayList<ArrayList<String>> data = txt.readFile(filePath);
		int columnCount = 5;
		for (ArrayList<String> row : data) {
			StringBuilder rowStrBuilder = new StringBuilder();

			for (int i = 0; i < columnCount; i++) {
				rowStrBuilder.append(row.get(i));
				if (i < columnCount - 1) {
					rowStrBuilder.append(", ");
				}
			}
			model.addElement(rowStrBuilder.toString());
		}
		list.setModel(model);
	}
}
