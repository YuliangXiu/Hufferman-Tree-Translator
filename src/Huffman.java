import java.awt.*;

public class Huffman extends Panel {

	static int initialNode;
	static int mergeNumber = 0;
	int totalNode;
	static int th = 0;
	static int turn = 0;
	static int nodes = 0;
	static String msg = new String();
	static String totalmsg = "";
	static String msg2 = new String();
	static String totalmsg2 = "";

	static int[] countch;
	static int[] countch2;// ��0Ƶ��ͳ��
	static char[] ch;
	static char[] ch2;// ��0�ַ�����

	static TreeNode[] treenode;
	static TreeNode root;

	static TreeNode parent_root;
	static TreeNode parent2_root;

	Mainframe m;

	// ��ʼ������������ȷ������������һЩ������Ϣ����ڵ������ԭʼ�ַ���ԭʼ�ַ���ӦƵ��
	public Huffman(Mainframe m, int initialNode, int[] countch, char[] ch) {

		this.m = m;
		this.initialNode = initialNode;
		this.totalNode = initialNode * 2 - 1;
		this.countch = countch;
		this.ch = ch;

		m.display_panel.upPart = new Label[initialNode * 4];
		m.display_panel.downPart = new Label[initialNode * 4];

		ch2 = new char[initialNode + 1];
		countch2 = new int[initialNode + 1];

		treenode = new TreeNode[2 * initialNode - 1];

		// ȥ������countchΪ�����Ŀ
		int t = 1;
		for (int i = 1; i < 27; i++) {

			if (this.countch[i] != 0) {
				ch2[t] = ch[i];
				countch2[t] = countch[i];
				t++;
			}
		}

		for (int i = 0; i < 2 * initialNode - 1; i++) {
			treenode[i] = new TreeNode();

		}
	}

	// �չ��캯��
	public Huffman() {
		// TODO Auto-generated constructor stub
	}

	// �����������ӽڵ�ϲ������µĸ��ڵ�
	public void MergeTree(TreeNode c1, TreeNode c2, TreeNode p) {
		p.leftchild = c1;
		p.rightchild = c2;

		p.weight = c1.weight + c2.weight;
		p.parent = null;
		c1.parent = p;
		c2.parent = p;
		p.havechild = true;
		p.isparent = true;
		c1.isparent = false;
		c2.isparent = false;

		// ��ǰ������Ϣ
		msg = "Combine " + c1.ch + " (" + c1.weight + ") " + " and " + c2.ch
				+ " (" + c2.weight + ") " + '\n' + " to make " + p.ch + " ("
				+ p.weight + ") ";
		msg2 = "Combine " + c1.ch + " (" + c1.weight + ") " + " and " + c2.ch
				+ " (" + c2.weight + ") " + " to make " + p.ch + " ("
				+ p.weight + ") ";
		totalmsg += '\n' + msg;
		totalmsg2 += '\n' + msg2;

		// p.leftchild.code += "0";
		// p.rightchild.code += "1";

		this.root = p; // System.out.print(root.leftchild.ch + " ");
		this.parent_root = p;
		this.parent2_root = p;

		root.x = m.display_panel.PANEL_WIDE / 2;
		root.y = m.display_panel.ROOT_HEIGHT;
		parent_root.x = m.display_panel.PANEL_WIDE / 2;
		parent_root.y = m.display_panel.ROOT_HEIGHT;

		// c1.level++;System.out.print(c1.level);
		// c2.level++;System.out.print(c2.level);
		mergeNumber++;
		// ��ʾ�ϲ�����

		this.th = 0;
		nodes = 0;

	}

	// ����ÿ���ڵ�Ļ���������
	public void getcode() {

		if ((this.parent_root.leftchild != null)
				&& (this.parent_root.rightchild != null)) {
			this.parent_root.leftchild.code += this.parent_root.code + "0";// System.out.print(this.parent_root.leftchild.code);
			this.parent_root.rightchild.code += this.parent_root.code + "1";// System.out.print(this.parent_root.rightchild.code);
			if ((this.parent_root.leftchild.havechild)
					&& (this.parent_root.rightchild.havechild)) {
				this.parent_root = this.parent_root.leftchild;
				getcode();
				this.parent_root = this.parent2_root.rightchild;
				this.parent2_root = this.parent2_root.rightchild;
				getcode();
			}
			if ((!this.parent_root.leftchild.havechild)
					&& (this.parent_root.rightchild.havechild)) {
				this.parent_root = this.parent_root.leftchild;
				getcode();
				this.parent_root = this.parent2_root.rightchild;
				this.parent2_root = this.parent2_root.rightchild;
				getcode();
			} else if ((!this.parent_root.rightchild.havechild)
					&& (this.parent_root.leftchild.havechild)) {
				this.parent_root = this.parent_root.rightchild;
				getcode();
				this.parent_root = this.parent2_root.leftchild;
				this.parent2_root = this.parent2_root.leftchild;
				getcode();
			}

		}

	}

}
