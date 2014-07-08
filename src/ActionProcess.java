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

	public static char[] let;// 字母表
	public static char[] test;// 文本中的所有字
	public static int[] times;// 字母对应频数
	public static String[] code;// 字母对应编码
	public static int nodesum;// 权值非零的节点的个数
	public static int testsum;// 文本字数
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

		// 文件导出的事件响应处理
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

		// 导入文本文件的事件响应处理
		if (e.getActionCommand().equals("import")) {
			nodesum = 0;
			testsum = 0;

			JFileChooser choosefile = new JFileChooser();
			int filebutton = choosefile.showOpenDialog(ActionProcess.this);

			if (filebutton == JFileChooser.APPROVE_OPTION) {
				m.import_field.setText(choosefile.getSelectedFile().getName());
				// 用来读取选中的文件
				try {
					inputstream = new BufferedReader(new FileReader(choosefile
							.getCurrentDirectory().toString()
							+ "/"
							+ choosefile.getSelectedFile().getName()));
				} catch (FileNotFoundException e1) {
					System.out.println("No Input File");
				}
				// 将读取文件显示到TextArea中
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
				// 用来统计文件中的字符频数和总字符数

				test = totaltest.toCharArray();
				times = new int[27];
				let = new char[27];
				for (int i = 0; i < 27; i++)
					times[i] = 0;
				for (int i = 0; i < 27; i++)
					let[i] = '~';
				// 统计出来一共有多少个输入字符
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

				// 统计出来一共有多少种节点
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

		// 开始键的事件响应处理
		if (e.getActionCommand().equals("Start")) {
			click_next = 0;
			m.start_button.setEnabled(false);
			initial_minheap.select = -1;
			m.display_panel.removeAll();
			initial_huffman = new Huffman(m, nodesum, times, let);// 初始化一个包含Huffman树所有节点（空间点）的树
			m.display_panel.InitialHuffman();// 将Huffman树的前半部分填充上basinnode
			initial_minheap = new MinHeap(initial_huffman.countch2,
					initial_huffman.initialNode);// 初始化一个包含Huffman树所有节点的最小堆
			m.next_button.setEnabled(true);
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));

		}
		// 展现所有步骤的响应事件
		if (e.getActionCommand().equals("Display All Steps")) {
			final JDialog dialog = new JDialog();
			dialog.setBounds(300, 300, 300, 500);
			JTextArea all = new JTextArea("                  ALL STEPS:" + '\n'
					+ "   " + initial_huffman.totalmsg2);
			all.setLineWrap(true);
			dialog.getContentPane().add(all);
			dialog.setVisible(true);
		}
		// 重新从头开始构建霍夫曼树
		if (e.getActionCommand().equals("Restart")) {
			m.restart_button.setEnabled(false);
			click_next = 0;
			initial_minheap.select = -1;
			initial_huffman.totalmsg = "";
			initial_huffman.totalmsg2 = "";
			m.codeArea.setText("");
			totalcode = "Translated Code:" + "\n";
			m.display_panel.drawpane.removeAll();
			initial_huffman = new Huffman(m, nodesum, times, let);// 初始化一个包含Huffman树所有节点（空间点）的树
			m.display_panel.InitialHuffman();// 将Huffman树的前半部分填充上basinnode
			initial_minheap = new MinHeap(initial_huffman.countch2,
					initial_huffman.initialNode);// 初始化一个包含Huffman树所有节点的最小堆
			m.next_button.setEnabled(true);
			
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));


		}
		// 将文本文件根据得到的每个字符的霍夫曼编码全部翻译成霍夫曼编码
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

		// Next键的事件响应处理
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
			// 寻找权重最小的两个节点的权重值
			min1 = initial_minheap.Findmin();// System.out.print(min1);
			min2 = initial_minheap.Findmin();// System.out.print(min2);
			// 找到对应最小两个权重值的节点
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
			// 将这两个节点合并
			initial_huffman.MergeTree(initial_huffman.treenode[sign1],
					initial_huffman.treenode[sign2],
					initial_huffman.treenode[initial_minheap.heapCurrentSize]);
			// 将合并后产生的父节点的和权值插入到最小堆中
			initial_minheap
					.Insert(initial_huffman.treenode[initial_minheap.heapCurrentSize].weight);

			m.display_panel.InitialHuffman();
			m.current_area.setText(initial_huffman.msg);

			
			initial_minheap.convertstr ="";
			m.Array_field.setText(initial_minheap.ConvertStr(initial_minheap.heapArray));

			// 当点击到最后一次NEXT的时候
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

		// 操作指南
		if (e.getActionCommand().equals("Operation Guide")) {
			final JDialog dialog = new JDialog();
			ImageIcon document = new ImageIcon(Mainframe.class.getResource("document.jpg"));
			dialog.setBounds(100, 80, document.getIconWidth(),
					document.getIconHeight() - 10);
			JLabel label = new JLabel(document);
			dialog.getContentPane().add(label);
			dialog.setVisible(true);
		}
		// 关于霍夫曼树
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
		// 关于作者本人
		if (e.getActionCommand().equals("About Author")) {
			final JDialog dialog = new JDialog();
			dialog.setBounds(400, 300, 220, 250);
			JLabel label = new JLabel(
					"<html><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp数据结构课程设计</br><br></br><br></br>"
							+ "<table><tr><td>姓名:</td><td>修宇亮</td></tr>"
							+ "<tr><td>学号:</td><td>201200302043</td></tr>"
							+ "<tr><td>院校:</td><td>山东大学软件学院</td></tr>"
							+ "<tr><td>班级:</td><td>数字媒体技术班</td></tr>"
							+ "<tr><td>邮箱:</td><td>xyl421608878@126.com</td></tr>"
							+ "<tr><td></td><td align=right><br></br>2014年4月1日</td></tr>"
							+ "</table></html>");
			dialog.getContentPane().add(label);
			dialog.setVisible(true);
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}

}
