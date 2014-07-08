import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//绘制霍夫曼树的面板类
public class DrawPanel extends Huffman {

	Mainframe m;
	// 绘制霍夫曼树的节点需要的一些位置、大小设定值
	public final int PANEL_WIDE = 602;
	public final int PANEL_HEIGHT = 432;
	public final int HEIGHT_LEVEL = 50;
	public final int BUTTON_Y = 360;
	public final int BUTTON_HEIGHT = 15;
	public final int BUTTON_WIDE = 30;
	public final int ROOT_HEIGHT = 30;
	public int BUTTON_SMALL_WIDE;
	public final int HORIZEN = 8;
	public final int VERTICAL = 15;
	public final int LEVEL = 10;
	public int signal = 1;

	static Label[] upPart;
	static Label[] downPart;
	Label[] charbutton;
	Label[] numbutton;
	static JLabel[] leftline = new JLabel[80];
	static JLabel[] rightline = new JLabel[80];
	static TreeNode temp = new TreeNode();
	static Panel drawpane;

	public DrawPanel() {

		super();
		drawpane = new Panel();
		drawpane.setBounds(247, 21, 628, 432);
		drawpane.setBackground(new Color(238, 232, 170));

	}

	// 将所有初始化的最初的节点显示在绘图面板上
	public void InitialHuffman()

	{

		charbutton = new Label[initialNode];
		numbutton = new Label[initialNode];

		// 如果初始按键过多的话，用这种公平分配法
		if (initialNode > 10) {

			BUTTON_SMALL_WIDE = PANEL_WIDE * 17 / (initialNode * 20);

			for (int i = 0; i < initialNode; i++) {

				treenode[i].ch = this.ch2[i + 1];
				treenode[i].basinNode = true;
				charbutton[i] = new Label(treenode[i].ch + "", Label.CENTER);
				charbutton[i].setBounds(PANEL_WIDE / (initialNode * 5)
						* (5 * (i + 1) - 4) + 10, BUTTON_Y, BUTTON_SMALL_WIDE,
						BUTTON_HEIGHT);

				if (treenode[i].used == true) {
					charbutton[i].setBackground(Color.GRAY);
				} else {
					charbutton[i].setBackground(Color.magenta);
				}
				charbutton[i].setVisible(true);
				charbutton[i].setAlignment(1);
				drawpane.add(charbutton[i]);

				treenode[i].weight = this.countch2[i + 1];
				numbutton[i] = new Label(treenode[i].weight + "", Label.CENTER);
				numbutton[i].setBounds(PANEL_WIDE / (initialNode * 5)
						* (5 * (i + 1) - 4) + 10, BUTTON_Y + 15,
						BUTTON_SMALL_WIDE, BUTTON_HEIGHT);
				if (treenode[i].used == true) {
					charbutton[i].setBackground(Color.GRAY);
				} else {
					numbutton[i].setBackground(Color.cyan);
				}
				numbutton[i].setForeground(Color.red);
				numbutton[i].setAlignment(1);
				numbutton[i].setVisible(true);
				drawpane.add(numbutton[i]);

			}
		}

		// 如果按键少于十个，就采用固定宽度的按键布局方式
		else {
			for (int i = 0; i < initialNode; i++) {

				treenode[i].ch = this.ch2[i + 1];
				treenode[i].basinNode = true;
				charbutton[i] = new Label(treenode[i].ch + "");
				charbutton[i].setBounds(PANEL_WIDE / (initialNode * 5)
						* (5 * (i + 1) - 4) + 10, BUTTON_Y, BUTTON_WIDE,
						BUTTON_HEIGHT);
				if (treenode[i].used == true) {
					charbutton[i].setBackground(Color.GRAY);
				} else {
					charbutton[i].setBackground(Color.magenta);
				}
				charbutton[i].setVisible(true);
				charbutton[i].setAlignment(1);
				drawpane.add(charbutton[i]);

				treenode[i].weight = this.countch2[i + 1];
				numbutton[i] = new Label(treenode[i].weight + "");
				numbutton[i].setBounds(PANEL_WIDE / (initialNode * 5)
						* (5 * (i + 1) - 4) + 10, BUTTON_Y + 15, BUTTON_WIDE,
						BUTTON_HEIGHT);
				if (treenode[i].used == true) {
					numbutton[i].setBackground(Color.GRAY);
				} else {
					numbutton[i].setBackground(Color.cyan);
				}
				numbutton[i].setForeground(Color.red);
				numbutton[i].setAlignment(1);
				numbutton[i].setVisible(true);
				drawpane.add(numbutton[i]);

			}
		}

	}

	// 绘制霍夫曼树的方法
	public void DrawTree(TreeNode root) {

		// 画树根
		upPart[th] = new Label(root.ch + "");
		upPart[th].setBounds(root.x, root.y, BUTTON_WIDE, BUTTON_HEIGHT);
		upPart[th].setBackground(Color.magenta);
		upPart[th].setVisible(true);
		upPart[th].setAlignment(1);
		drawpane.add(upPart[th]);

		downPart[th] = new Label(root.weight + "");
		downPart[th].setBounds(root.x, root.y + 15, BUTTON_WIDE, BUTTON_HEIGHT);
		downPart[th].setBackground(Color.cyan);
		downPart[th].setForeground(Color.red);
		downPart[th].setVisible(true);
		downPart[th].setAlignment(1);
		drawpane.add(downPart[th]);

		// 左画箭头
		ImageIcon left = new ImageIcon(Mainframe.class.getResource("left.jpg"));
		if (root == parent_root) {
			left.setImage(left.getImage()
					.getScaledInstance(left.getIconWidth() * 5, BUTTON_HEIGHT,
							Image.SCALE_DEFAULT));
			leftline[th] = new JLabel(left);
			leftline[th].setBounds(root.x - 120, root.y + VERTICAL * 2,
					left.getIconWidth(), left.getIconHeight());
		} else {
			left.setImage(left.getImage().getScaledInstance(
					left.getIconWidth() * (8 - root.level) / 3, BUTTON_HEIGHT,
					Image.SCALE_DEFAULT));
			leftline[th] = new JLabel(left);
			leftline[th].setBounds(root.x - 6 * (LEVEL - root.level), root.y
					+ VERTICAL * 2, left.getIconWidth(), left.getIconHeight());
		}
		drawpane.add(leftline[th]);

		// 画右箭头
		ImageIcon right = new ImageIcon(Mainframe.class.getResource("right.jpg"));
		if (root == parent_root) {
			right.setImage(right.getImage().getScaledInstance(
					right.getIconWidth() * 5, BUTTON_HEIGHT,
					Image.SCALE_DEFAULT));
			rightline[th] = new JLabel(right);
			rightline[th].setBounds(root.x - 20, root.y + VERTICAL * 2,
					right.getIconWidth(), left.getIconHeight());
		} else {
			right.setImage(right.getImage().getScaledInstance(
					right.getIconWidth() * (8 - root.level) / 3, BUTTON_HEIGHT,
					Image.SCALE_DEFAULT));
			rightline[th] = new JLabel(right);
			rightline[th].setBounds(root.x + (LEVEL - root.level), root.y
					+ VERTICAL * 2, right.getIconWidth(), left.getIconHeight());
		}

		drawpane.add(rightline[th]);

		// 画左子树
		root.leftchild.level = root.level + 1;
		// System.out.print(root.leftchild.level);
		if (root == parent_root)
			root.leftchild.x = root.x - 13 * (LEVEL - root.leftchild.level);
		else
			root.leftchild.x = root.x - HORIZEN
					* (LEVEL - root.leftchild.level);

		root.leftchild.y = root.y + VERTICAL + BUTTON_HEIGHT * 2;

		upPart[th] = new Label(root.leftchild.ch + "");
		upPart[th].setBounds(root.leftchild.x, root.leftchild.y, BUTTON_WIDE,
				BUTTON_HEIGHT);
		upPart[th].setBackground(Color.magenta);
		upPart[th].setVisible(true);
		upPart[th].setAlignment(1);
		drawpane.add(upPart[th]);
		nodes++;

		downPart[th] = new Label(root.leftchild.weight + "");
		// System.out.print(root.rightchild.level);
		downPart[th].setBounds(root.leftchild.x, root.leftchild.y + 15,
				BUTTON_WIDE, BUTTON_HEIGHT);
		downPart[th].setBackground(Color.cyan);
		downPart[th].setForeground(Color.red);
		downPart[th].setVisible(true);
		downPart[th].setAlignment(1);
		drawpane.add(downPart[th]);

		if (root.leftchild.leftchild != null
				&& root.leftchild.rightchild != null) {
			root.leftchild.rightchild.x = root.leftchild.x + HORIZEN
					* (8 - root.leftchild.rightchild.level);
			root.leftchild.rightchild.y = root.leftchild.y + VERTICAL;
			root.leftchild.leftchild.x = root.leftchild.x - HORIZEN
					* (8 - root.leftchild.leftchild.level);
			root.leftchild.leftchild.y = root.leftchild.y + VERTICAL;

		}

		// 画右子树
		root.rightchild.level = root.level + 1;
		if (root == parent_root)
			root.rightchild.x = root.x + 13 * (LEVEL - root.rightchild.level);
		else
			root.rightchild.x = root.x + HORIZEN
					* (LEVEL - root.rightchild.level);
		root.rightchild.y = root.y + VERTICAL + BUTTON_HEIGHT * 2;

		upPart[th] = new Label(root.rightchild.ch + "");
		upPart[th].setBounds(root.rightchild.x, root.rightchild.y, BUTTON_WIDE,
				BUTTON_HEIGHT);
		upPart[th].setBackground(Color.magenta);
		upPart[th].setVisible(true);
		upPart[th].setAlignment(1);
		drawpane.add(upPart[th]);
		nodes++;

		downPart[th] = new Label(root.rightchild.weight + "");
		downPart[th].setBounds(root.rightchild.x, root.rightchild.y + 15,
				BUTTON_WIDE, BUTTON_HEIGHT);
		downPart[th].setBackground(Color.cyan);
		downPart[th].setForeground(Color.red);
		downPart[th].setVisible(true);
		downPart[th].setAlignment(1);
		drawpane.add(downPart[th]);

		// 调整之后节点的坐标

		if (root.rightchild.leftchild != null
				&& root.rightchild.rightchild != null) {
			root.rightchild.rightchild.x = root.rightchild.x + HORIZEN
					* (8 - root.rightchild.rightchild.level);
			root.rightchild.rightchild.y = root.rightchild.y + VERTICAL;
			root.rightchild.leftchild.x = root.rightchild.x - HORIZEN
					* (8 - root.rightchild.leftchild.level);
			root.rightchild.leftchild.y = root.rightchild.y + VERTICAL;
		}

		// 通过递归的方式绘制左子树
		if (root.leftchild.leftchild != null
				&& root.leftchild.rightchild != null) {
			temp = root;
			root = root.leftchild;
			DrawTree(root);
			root = temp;
		}
		// 通过递归的方式绘制右子树
		if (root.rightchild.leftchild != null
				&& root.rightchild.rightchild != null) {
			root = root.rightchild;
			DrawTree(root);

		}

	}

	// 本来想用drawline的方法绘制连线，但出现了不能及时正常显示的问题，因此改用加载箭头图片的方法
	/*
	 * public void paint(Graphics g, int a, int b, int c, int d, int f, int h) {
	 * 
	 * g.setColor(Color.BLUE);
	 * 
	 * b += BUTTON_HEIGHT / 2; d += BUTTON_HEIGHT / 2; h += BUTTON_HEIGHT / 2;
	 * 
	 * if (ActionProcess.initial_huffman.initialNode > 10) { a +=
	 * BUTTON_SMALL_WIDE / 2; c += BUTTON_SMALL_WIDE / 2; f += BUTTON_SMALL_WIDE
	 * / 2; }
	 * 
	 * if (ActionProcess.initial_huffman.initialNode <= 10) { a += BUTTON_WIDE /
	 * 2; c += BUTTON_WIDE / 2; f += BUTTON_WIDE / 2; } g.drawLine(a, b, c, d);
	 * g.drawLine(a, b, f, h);
	 * 
	 * }
	 */

}
