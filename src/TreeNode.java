public class TreeNode {

	public String code = "";// �ڵ��Huffman����
	public char ch = '~';// �ڵ���ַ�����
	public int weight; // �ڵ�Ȩ��
	public boolean havechild = false;// �Ƿ����ӽڵ�
	public TreeNode parent;// �ڵ��Ƿ��и���
	public TreeNode leftchild;// �ڵ��������
	public TreeNode rightchild;// �ڵ��������
	public int level = 0;// �ڵ����ڵĲ���
	public boolean basinNode = false;// �ڵ��Ƿ��ǳ�ʼ����ԭʼ�ڵ�
	public boolean used = false;// ԭʼ�ڵ��Ƿ��Ѿ���������Ϊ��������
	public boolean isparent = false;// �ڵ��Ƿ��и���

	// buttonλ��
	public int x;
	public int y;

}
