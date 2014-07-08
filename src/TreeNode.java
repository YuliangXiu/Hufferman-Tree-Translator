public class TreeNode {

	public String code = "";// 节点的Huffman代码
	public char ch = '~';// 节点的字符类型
	public int weight; // 节点权重
	public boolean havechild = false;// 是否有子节点
	public TreeNode parent;// 节点是否有父亲
	public TreeNode leftchild;// 节点的左子树
	public TreeNode rightchild;// 节点的右子树
	public int level = 0;// 节点所在的层数
	public boolean basinNode = false;// 节点是否是初始化的原始节点
	public boolean used = false;// 原始节点是否已经被构建成为霍夫曼树
	public boolean isparent = false;// 节点是否有父亲

	// button位置
	public int x;
	public int y;

}
