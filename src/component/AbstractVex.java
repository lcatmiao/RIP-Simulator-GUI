package component;

/**
 * 顶点的抽象类
 *
 * 两种顶点：Router, Network
 */
public abstract class AbstractVex {
    // 顶点序号
    private int vexIndex;

    // 顶点类型（R: Router, N: Network）
    private char vexType;


    // 构造方法
    public AbstractVex(int vexIndex, char vexType) {
        this.vexIndex = vexIndex;
        this.vexType = vexType;
    }

    // 获取顶点序号
    public int getVexIndex() {
        return vexIndex;
    }

    // 获取顶点类型
    public char getVexType() {
        return vexType;
    }

    // 获取顶点名称
    public abstract String getVexName();
}
