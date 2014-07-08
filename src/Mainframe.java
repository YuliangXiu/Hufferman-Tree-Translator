import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;

import java.awt.Font;

//程序总UI界面绘制类
public class Mainframe extends JFrame

{

	public static final int WIDTH = 900;
	public static final int HEIGHT = 1000;
	public static final int main_x = 250;
	public static final int main_y = 200;

	public JTextField import_field;
	public JTextField export_field;
	public TextArea textArea;
	public TextArea codeArea;
	public TextArea current_area;
	public TextArea statistics_area;

	static JButton start_button;
	static JButton next_button;
	static JButton restart_button;

	DrawPanel display_panel;
	static JTextField Array_field;

	public static void main(String[] args)

	{
		new Mainframe();
	}

	public Mainframe()

	{
		super();
		setTitle("Hufferman Tree Translator");
		setSize(WIDTH, HEIGHT);
		setLocation(main_x, main_y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		display_panel = new DrawPanel();
		this.getContentPane().add(display_panel.drawpane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem Import_file = new JMenuItem("Import File");
		Import_file.addActionListener(new ActionProcess(this));
		Import_file.setActionCommand("import");
		Import_file
				.setIcon(new ImageIcon(
						Mainframe.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		Import_file.setSelectedIcon(null);
		file.add(Import_file);

		JMenuItem export_file = new JMenuItem("Export File");
		export_file.addActionListener(new ActionProcess(this));
		export_file
				.setIcon(new ImageIcon(
						Mainframe.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		export_file.setSelectedIcon(null);
		export_file.setActionCommand("export");
		file.add(export_file);

		JSeparator separator = new JSeparator();
		file.add(separator);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionProcess(this));
		file.add(exit);

		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem operation_guide = new JMenuItem("Operation Guide");
		operation_guide.addActionListener(new ActionProcess(this));
		operation_guide
				.setIcon(new ImageIcon(
						Mainframe.class
								.getResource("/com/sun/java/swing/plaf/motif/icons/Question.gif")));
		help.add(operation_guide);

		// 三个About
		JMenu about = new JMenu("About");
		menuBar.add(about);

		JMenuItem about_huffman_code = new JMenuItem("About Hufferman Code");
		about_huffman_code.addActionListener(new ActionProcess(this));
		about.add(about_huffman_code);

		JMenuItem about_author = new JMenuItem("About Author");
		about_author.addActionListener(new ActionProcess(this));
		about.add(about_author);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(234, 377, 1, 1);

		JLabel state_label = new JLabel("Current Operation");
		state_label.setBounds(75, 21, 113, 18);

		JLabel statistics_label = new JLabel("Statistics Result");
		statistics_label.setBounds(76, 197, 101, 16);

		JButton allstep_button = new JButton("Display All Steps");
		allstep_button.addActionListener(new ActionProcess(this));
		allstep_button.setForeground(Color.BLACK);
		allstep_button.setBackground(Color.PINK);
		allstep_button.setBounds(29, 162, 193, 29);

		start_button = new JButton("Start");
		start_button.addActionListener(new ActionProcess(this));
		start_button.setBounds(93, 430, 68, 29);
		start_button.setEnabled(true);

		next_button = new JButton("Next");
		next_button.addActionListener(new ActionProcess(this));
		next_button.setBounds(154, 430, 68, 29);

		JLabel import_label = new JLabel("Import:");
		import_label.setBounds(29, 723, 46, 30);

		import_field = new JTextField();
		import_field.setBounds(75, 724, 216, 28);
		import_field.setColumns(10);

		JButton import_browse_button = new JButton("Browse");
		import_browse_button.addActionListener(new ActionProcess(this));
		import_browse_button.setActionCommand("import");
		import_browse_button.setBounds(303, 725, 94, 29);

		export_field = new JTextField();
		export_field.setBounds(559, 724, 210, 28);
		export_field.setColumns(10);

		JButton export_browse_button = new JButton("Save");
		export_browse_button.setBounds(781, 725, 94, 29);
		export_browse_button.addActionListener(new ActionProcess(this));
		export_browse_button.setActionCommand("export");

		JLabel export_label = new JLabel("Export\uFF1A");
		export_label.setBounds(507, 730, 58, 16);

		JButton translator_button = new JButton("");
		translator_button.setBounds(420, 560, 67, 78);
		translator_button.setActionCommand("translate");
		translator_button.addActionListener(new ActionProcess(this));
		translator_button.setIcon(new ImageIcon(Mainframe.class.getResource("arrow.png")));
		
		getContentPane().setLayout(null);
		getContentPane().add(import_label);
		getContentPane().add(import_field);
		getContentPane().add(import_browse_button);
		getContentPane().add(export_label);
		getContentPane().add(export_field);
		getContentPane().add(export_browse_button);

		getContentPane().add(translator_button);
		getContentPane().add(allstep_button);
		getContentPane().add(start_button);
		getContentPane().add(next_button);
		getContentPane().add(desktopPane);
		getContentPane().add(state_label);
		getContentPane().add(statistics_label);

		textArea = new TextArea();
		textArea.setRows(30);
		textArea.setColumns(40);
		textArea.setEditable(false);
		textArea.setBackground(new Color(153, 204, 153));
		textArea.setBounds(29, 471, 368, 240);
		getContentPane().add(textArea);

		codeArea = new TextArea();
		codeArea.setRows(30);
		// codeArea.setLineWrap(true);
		codeArea.setColumns(40);
		codeArea.setBackground(new Color(153, 204, 153));
		codeArea.setBounds(507, 471, 368, 240);
		getContentPane().add(codeArea);

		JPanel sta_bar_panel = new JPanel();
		sta_bar_panel.setBounds(29, 217, 193, 26);
		getContentPane().add(sta_bar_panel);

		JLabel sta_char_label = new JLabel("Char  ");
		sta_bar_panel.add(sta_char_label);

		JLabel sta_times_label = new JLabel("Times           ");
		sta_bar_panel.add(sta_times_label);

		JLabel sta_code_label = new JLabel("Code");
		sta_bar_panel.add(sta_code_label);

		restart_button = new JButton("Restart");
		restart_button.setBounds(29, 430, 68, 29);
		getContentPane().add(restart_button);
		restart_button.addActionListener(new ActionProcess(this));
		restart_button.setEnabled(false);

		current_area = new TextArea();
		current_area.setBounds(29, 45, 193, 59);
		getContentPane().add(current_area);
		// current_area(true);
		current_area
				.setText("\nUse \"File\" menu to import  \nthe original file(.txt)");
		current_area.setEditable(false);
		current_area.setFont(new Font("Hiragino Maru Gothic ProN", Font.BOLD,
				12));
		current_area.setBackground(new Color(238, 232, 170));
		current_area.setRows(4);
		current_area.setColumns(15);

		statistics_area = new TextArea();
		statistics_area.setFont(new Font("Big Caslon", Font.BOLD, 14));
		statistics_area.setEditable(false);
		statistics_area.setBackground(new Color(238, 232, 170));
		statistics_area.setBounds(29, 242, 193, 182);
		getContentPane().add(statistics_area);
		
		Array_field = new JTextField();
		Array_field.setFont(new Font("Wingdings 2", Font.BOLD, 13));
		Array_field.setBounds(29, 126, 193, 28);
		getContentPane().add(Array_field);
		Array_field.setColumns(10);
		
		JLabel array_label = new JLabel("Array Display");
		array_label.setBounds(83, 107, 113, 18);
		getContentPane().add(array_label);

		this.setVisible(true);
	}
}
