import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionProcess extends JFrame implements ActionListener {

	private String inputcontents;
	private String totaltest;
	private String totalcode = "Translated Code:" + "\n";
	private String totalstatistics;

	public static char[] let;// ��ĸ��
	public static char[] test;// �ı��е�������
	public static int[] times;// ��ĸ��ӦƵ��
	public static String[] code;// ��ĸ��Ӧ����
	public static int nodesum;// Ȩֵ����Ľڵ�ĸ���
	public static int testsum;// �ı�����
	public static int click_next = 0;
	public static boolean divide = false;
	public ActionEvent e;

	Mainframe m;
	static Huffman initial_huffman;
	static MinHeap initial_minheap;

	public ActionProcess(Mainframe m) {
		this.m = m;
	}

	public void actionPerformed(ActionEvent e) {

		BufferedReader inputstream = null;
		PrintWriter outputstream = null;

		// �ļ��������¼���Ӧ����
		if (e.getActionCommand().equals("export")) {
			JFileChooser choosefile = new JFileChooser();
			int filebutton = choosefile.showSaveDialog(ActionProcess.this);

			if (filebutton == JFileChooser.APPROVE_OPTION) {
				m.export_field.setText(choosefile.getSelectedFile().getName());
				try {
					outputstream = new PrintWriter(new FileOutputStream(
							choosefile.getCurrentDirectory().toString() + "/"
									+ choosefile.getSelectedFile().getName()));
				} catch (FileNotFoundException e1) {
					System.out.println("No Output File");
				}
				outputstream.print(m.codeArea.getText());
				m.export_field.setText(choosefile.getSelectedFile().getName());
				outputstream.close();

			}
			if (filebutton == JFileChooser.CANCEL_OPTION) {
				m.export_field.setText("No exporting file.");
			}
		}

		// �����ı��ļ����¼���Ӧ����
		if (e.getActionCommand().equals("import")) {
			nodesum = 0;
			testsum = 0;

			JFileChooser choosefile = new JFileChooser();
			int filebutton = choosefile.showOpenDialog(ActionProcess.this);

			if (filebutton == JFileChooser.APPROVE_OPTION) {
				m.import_field.setText(choosefile.getSelectedFile().getName());
				// ������ȡѡ�е��ļ�
				try {
					inputstream = new BufferedReader(new FileReader(choosefile
							.getCurrentDirectory().toString()
							+ "/"
							+ choosefile.getSelectedFile().getName()));
				} catch (FileNotFoundException e1) {
					System.out.println("No Input File");
				}
				// ����ȡ�ļ���ʾ��TextArea��
				try {
					inputcontents = inputstream.readLine();
					totaltest = inputcontents + '\n';

					while ((inputcontents = inputstream.readLine()) != null)
						totaltest += inputcontents + '\n';

					m.textArea.setText("File Content:" + '\n' + '\n'
							+ totaltest);
					inputstream.close();
				} catch (IOException e1) {
					System.out.println("Can't display file on the testarea");
				}
				// ����ͳ���ļ��е��ַ�Ƶ�������ַ���

				test = totaltest.toCharArray();
				times = new int[27];
				let = new char[27];
				for (int i = 0; i < 27; i++)
					times[i] = 0;
				for (int i = 0; i < 27; i++)
					let[i] = '~';
				// ͳ�Ƴ���һ���ж��ٸ������ַ�
				for (int i = 0; i < totaltest.length(); i++) {
					if (test[i] != '\n')
						testsum++;
				}

				code = new String[27];
				for (int i = 0; i <= 26; i++)
					code[i] = "00";

				for (int a = 0; a < initial_huffman.initialNode * 2 - 1; a++) {
					for (int b = 0; b < times.length; b++) {

						if (initial_huffman.treenode[a].weight == times[b])
							code[b] = initial_huffman.treenode[a].code;
					}
				}

				for (int i = 0; i < totaltest.length(); i++) {

					switch (test[i]) {
					case 'a':
						times[1]++;
						break;
					case 'b':
						times[2]++;
						break;
					case 'c':
						times[3]++;
						break;
					case 'd':
						times[4]++;
						break;
					case 'e':
						times[5]++;
						break;
					case 'f':
						times[6]++;
						break;
					case 'g':
						times[7]++;
						break;
					case 'h':
						times[8]++;
						break;
					case 'i':
						times[9]++;
						break;
					case 'j':
						times[10]++;
						break;
					case 'k':
						times[11]++;
						break;
					case 'l':
						times[12]++;
						break;
					case 'm':
						times[13]++;
						break;
					case 'n':
						times[14]++;
						break;
					case 'o':
						times[15]++;
						break;
					case 'p':
						times[16]++;
						break;
					case 'q':
						times[17]++;
						break;
					case 'r':
						times[18]++;
						break;
					case 's':
						times[19]++;
						break;
					case 't':
						times[20]++;
						break;
					case 'u':
						times[21]++;
						break;
					case 'v':
						times[22]++;
						break;
					case 'w':
						times[23]++;
						break;
					case 'x':
						times[24]++;
						break;
					case 'y':
						times[25]++;
						break;
					case 'z':
						times[26]++;
						break;

					}

				}

				// ͳ�Ƴ���һ���ж����ֽڵ�
				for (int i = 1; i <= 26; i++) {

					if (times[i] > 0)
						nodesum++;
				}
				totalstatistics = "";
				for (int i = 1; i <= 26; i++) {
					let[i] = (char) ('a' + i - 1);
					String letter = String.valueOf(let[i]);
					if (times[i] > 0) {
						totalstatistics += "      " + letter
								+ "               " + times[i]
								+ "                      " + code[i] + '\n';
					}
				}
				m.statistics_area.setText(totalstatistics);

				m.display_panel.drawpane.removeAll();
				m.codeArea.setText("");

			}

			if (filebutton == JFileChooser.CANCEL_OPTION) {
				m.import_field.setText("No importing file .");
				m.display_panel.drawpane.removeAll();
				m.codeArea.setText("");
			}
			
			m.Array_field.setText("");
		}

		// ��ʼ�����¼���Ӧ����
		if (e.getActionCommand().equals("Start")) {
			click_next = 0;
			m.start_button.setEnabled(false);
			initial_minheap.select = -1;
			m.display_panel.removeAll();
			initial_huffman = new Huffman(m, nodesum, times, let);// ��ʼ��һ������Huffman�����нڵ㣨�ռ�㣩����
			m.display_panel.InitialHuffman();// ��Huffman����ǰ�벿�������basinnode
			initial_minheap = new MinHeap(initial_huffman.countch2,
					initial_huffman.initialNode);// ��ʼ��һ������Huffman�����нڵ����С��
			m.next_button.setEnabled(true);
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));

		}
		// չ�����в������Ӧ�¼�
		if (e.getActionCommand().equals("Display All Steps")) {
			final JDialog dialog = new JDialog();
			dialog.setBounds(300, 300, 300, 500);
			JTextArea all = new JTextArea("                  ALL STEPS:" + '\n'
					+ "   " + initial_huffman.totalmsg2);
			all.setLineWrap(true);
			dialog.getContentPane().add(all);
			dialog.setVisible(true);
		}
		// ���´�ͷ��ʼ������������
		if (e.getActionCommand().equals("Restart")) {
			m.restart_button.setEnabled(false);
			click_next = 0;
			initial_minheap.select = -1;
			initial_huffman.totalmsg = "";
			initial_huffman.totalmsg2 = "";
			m.codeArea.setText("");
			totalcode = "Translated Code:" + "\n";
			m.display_panel.drawpane.removeAll();
			initial_huffman = new Huffman(m, nodesum, times, let);// ��ʼ��һ������Huffman�����нڵ㣨�ռ�㣩����
			m.display_panel.InitialHuffman();// ��Huffman����ǰ�벿�������basinnode
			initial_minheap = new MinHeap(initial_huffman.countch2,
					initial_huffman.initialNode);// ��ʼ��һ������Huffman�����нڵ����С��
			m.next_button.setEnabled(true);
			
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));


		}
		// ���ı��ļ����ݵõ���ÿ���ַ��Ļ���������ȫ������ɻ���������
		if (e.getActionCommand().equals("translate")
				|| e.getActionCommand().equals("")) {
			char[] temp;
			totalcode = "Translated Code:" + "\n";
			m.codeArea.setText("");
			for (int b = 0; b < test.length; b++) {
				for (int a = 0; a < initial_huffman.initialNode * 2 - 1; a++) {

					if (test[b] == initial_huffman.treenode[a].ch)
						totalcode += initial_huffman.treenode[a].code;
					// System.out.println(code[b]+"code");
				}
			}

			temp = totalcode.toCharArray();
			for (int i = 0; i < temp.length; i++) {
				m.codeArea.append(temp[i] + "");
				if (i % 16 == 0 && i >= 16) {
					m.codeArea.append('\n' + "");
				}
			}
			// m.codeArea.append(totalcode);
		}

		// Next�����¼���Ӧ����
		if (e.getActionCommand().equals("Next")) {
			click_next++;
			if (click_next == nodesum - 1) {
				m.next_button.setEnabled(false);
				m.start_button.setEnabled(false);
				m.restart_button.setEnabled(true);

			}

			int min1, min2;
			int sign1 = 0;
			int sign2 = 0;
			m.display_panel.drawpane.removeAll();
			// Ѱ��Ȩ����С�������ڵ��Ȩ��ֵ
			min1 = initial_minheap.Findmin();// System.out.print(min1);
			min2 = initial_minheap.Findmin();// System.out.print(min2);
			// �ҵ���Ӧ��С����Ȩ��ֵ�Ľڵ�
			for (int i = 0; i < initial_huffman.initialNode * 2 - 1; i++) {

				if (initial_huffman.treenode[i].weight == min1) {
					sign1 = i;

					initial_huffman.treenode[sign1].used = true;

				}

				if (initial_huffman.treenode[i].weight == min2) {
					sign2 = i;

					initial_huffman.treenode[sign2].used = true;

				}
			}
			// ���������ڵ�ϲ�
			initial_huffman.MergeTree(initial_huffman.treenode[sign1],
					initial_huffman.treenode[sign2],
					initial_huffman.treenode[initial_minheap.heapCurrentSize]);
			// ���ϲ�������ĸ��ڵ�ĺ�Ȩֵ���뵽��С����
			initial_minheap
					.Insert(initial_huffman.treenode[initial_minheap.heapCurrentSize].weight);

			m.display_panel.InitialHuffman();
			m.current_area.setText(initial_huffman.msg);

			
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));

			// ����������һ��NEXT��ʱ��
			if (click_next == nodesum - 1) {

				initial_huffman.getcode();
				totalstatistics = "";
				for (int a = 0; a < initial_huffman.initialNode * 2 - 1; a++) {
					for (int b = 0; b < times.length; b++) {

						if (initial_huffman.treenode[a].weight == times[b])
							code[b] = initial_huffman.treenode[a].code;
						// System.out.println(code[b]+"code");
					}
				}

				for (int i = 1; i <= 26; i++) {
					let[i] = (char) ('a' + i - 1);
					String letter = String.valueOf(let[i]);
					if (times[i] > 0) {
						totalstatistics += "      " + letter
								+ "               " + times[i]
								+ "                      " + code[i] + '\n';
					}
				}

				m.statistics_area.setText(totalstatistics);

			}

		}

		// ����ָ��
		if (e.getActionCommand().equals("Operation Guide")) {
			final JDialog dialog = new JDialog();
			ImageIcon document = new ImageIcon(Mainframe.class.getResource("document.jpg"));
			dialog.setBounds(100, 80, document.getIconWidth(),
					document.getIconHeight() - 10);
			JLabel label = new JLabel(document);
			dialog.getContentPane().add(label);
			dialog.setVisible(true);
		}
		// ���ڻ�������
		if (e.getActionCommand().equals("About Hufferman Code")) {
			final JDialog dialog = new JDialog();
			ImageIcon document = new ImageIcon(Mainframe.class.getResource("Huffman.jpg"));
			dialog.setBounds(100, 80, 100, 60);
			JLabel label = new JLabel(document);
			dialog.setBounds(100, 80, document.getIconWidth(),
					document.getIconHeight());
			dialog.getContentPane().add(label);
			dialog.setVisible(true);
		}
		// �������߱���
		if (e.getActionCommand().equals("About Author")) {
			final JDialog dialog = new JDialog();
			dialog.setBounds(400, 300, 220, 250);
			JLabel label = new JLabel(
					"<html><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp���ݽṹ�γ����</br><br></br><br></br>"
							+ "<table><tr><td>����:</td><td>������</td></tr>"
							+ "<tr><td>ѧ��:</td><td>201200302043</td></tr>"
							+ "<tr><td>ԺУ:</td><td>ɽ����ѧ���ѧԺ</td></tr>"
							+ "<tr><td>�༶:</td><td>����ý�弼����</td></tr>"
							+ "<tr><td>����:</td><td>xyl421608878@126.com</td></tr>"
							+ "<tr><td></td><td align=right><br></br>2014��4��1��</td></tr>"
							+ "</table></html>");
			dialog.getContentPane().add(label);
			dialog.setVisible(true);
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}

}
