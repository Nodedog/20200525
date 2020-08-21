
/*
*
*
*                                  二叉搜索树
*
*
* */



public class TestDemo {


    public static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    //查找操作
    //根节点,root为空表示为空树
    private Node root = null;

    //查找key是否存在于树中,如果在 返回对应的Node
    public Node find(int key) {
        Node cur = root;
        while (cur != null) {
            if (key < cur.key) {
                //在左子树中找
                cur = cur.left;
            } else if (key > cur.key) {
                //在右子树中找
                cur = cur.right;
            } else {
                //上面两种都不是说明相等 则直接返回cur
                return cur;
            }
        }
        //上面三种情况都没有说明没有对应的key 返回null
        return null;
    }


    //插入操作
    //二叉搜索树中不允许存在相同的key元素
    //如果发现新插入的key重复 则插入失败 返回false
    //插入成功返回true
    public boolean insert(int key) {
        if (root == null) {
            //如果当前为空树,直接让root指向key对应的新节点即可
            root = new Node(key);
            return true;
        }
        //和查找类似,需要先找到合适的位置,再插入元素
        Node cur = root;
        //parent始终指向cur的父节点
        Node parent = null;
        while (cur != null) {
            if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                parent = cur;
                cur = cur.right;
            } else {
                //当前找到某个元素和key相同
                //插入失败
                return false;
            }
        }
        //循环结束,cur就指向null,当前元素就要插入到parent的子树上
        //要判断一下 才能决定插到左子树还是右子树
        if (key < parent.key) {
            parent.left = new Node(key);
        } else {
            parent.right = new Node(key);
        }
        return true;
    }


    //删除操作
    //key在树中存在,就删除成功 返回true
    //key在树中不存在,就删除失败,返回false
    public boolean remove(int key) {
        //先查找要删除节点的位置,再进行具体的删除
        //找到这个待删除元素后,再判断是笔记中1-6的哪种情况
        Node cur = root;
        //parent始终指向cur的父节点
        Node parent = null;
        while (cur != null) {
            if (key < cur.key) {
                parent = cur;
                cur.left = cur;
            } else if (key > cur.key) {
                parent = cur;
                cur.right = cur;
            } else {
                //当前找到某个节点的元素和key相同,也就是找到要删除的节点
                //在removeNode这个方法中 判断是1-6哪种情况并删除
                removeNode(parent, cur);
                return true;
            }
        }
        return false;
    }

    private void removeNode(Node parent, Node cur) {
        //1.待删除的节点cur没有左子树
        if (cur.left == null) {
            //1.1要删除的节点为root
            if (cur == root) {
                root = cur.right;
                //1.2cur为parent的左子树
            } else if (cur == parent.left) {
                parent.left = cur.right;//对应笔记中1情况
                //1.3cur为parent的右子树
            } else {
                parent.right = cur.right;//对应笔记中2情况
            }
            //2.待删除的节点cur没有右子树
        } else if (cur.right == null) {
            //2.1要删除的节点为root
            if (cur == root) {
                root = cur.left;
                //2.2cur为parent的左子树
            } else if (cur == parent.left) {
                parent.left = cur.left;//对应笔记中3情况
                //2.3cur为parent的右子树
            } else {
                parent.right = cur.left;//对应笔记中4情况
            }
        } else {
            //3.当前待删除节点左右子树都存在,对应笔记5,6情况
            //1)先找右子树中最小值
            Node goatparent = cur;//最小值的父节点
            Node smallnode = cur.right;//最小值节点
            while (smallnode.left != null) {
                goatparent = smallnode;
                smallnode = smallnode.left;
            }
            //循环结束时,smallnode指向的就是右子树中的最小值
            //2)把找到的最小值赋值给待删除节点cur
            cur.key = smallnode.key;
            //3)删除最小值节点
            //最小值节点肯定没有左子树 对应的笔记中1,2的情况
            if (smallnode == goatparent.left) {
                goatparent.left = smallnode.right;
            } else {
                goatparent.right = smallnode.right;
            }
        }
    }


    public void preOrder(Node root){
        if (root == null){
            return;
        }
        System.out.print(root.key + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.key + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TestDemo tree = new TestDemo();
        tree.insert(9);
        tree.insert(7);
        tree.insert(6);
        tree.insert(11);
        tree.insert(8);
        tree.insert(4);
        tree.insert(2);

        tree.preOrder(tree.root);
        System.out.println("  ");
        tree.inOrder(tree.root);
        System.out.println("  ");

        Node cur = tree.find(100);
        System.out.println(cur.key);
        
    }
}
