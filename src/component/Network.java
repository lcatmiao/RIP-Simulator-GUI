package component;

/**
 * 网络顶点
 */
public class Network extends AbstractVex {
    // 网络顶点名称
    private String networkName;


    // 构造方法
    public Network(int vexIndex, char vexType, String networkName) {
        super(vexIndex, vexType);
        this.networkName = networkName;
    }

    // 获取顶点名称
    public String getVexName() {
        return networkName;
    }
}
