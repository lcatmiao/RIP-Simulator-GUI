package component;

/**
 * 路由器顶点
 */
public class Router extends AbstractVex {
    // 路由器名称
    private String routerName;

    // 路由表
    private RouterTable routerTable;


    // 构造方法
    public Router(int vexIndex, char vexType, String routerName) {
        super(vexIndex, vexType);
        this.routerName = routerName;
        routerTable = null;
    }

    // 初始化路由表
    public void initialRouterTable(AbstractVex[] vexSet, int[][] matrix) {
        int vexIndex = super.getVexIndex();

        routerTable = new RouterTable(vexIndex, vexSet, matrix);
    }

    // 获取顶点名称
    public String getVexName() {
        return routerName;
    }

    // 获取路由表
    public RouterTable getRouterTable() {
        return routerTable;
    }

    // 输出路由表
    public void printRouterTable() {
        // 获取当前路由器的路由表
        RouterTable currentRouterTable = getRouterTable();

        System.out.println("===== " + routerName + " RouterTable" + " =====");

        // 路由表为空
        if (currentRouterTable == null) {
            System.out.println("              Empty");
            return;
        }

        // 路由表非空
        // 获取路由表的当前项目数、目的网络、距离、下一跳路由器
        int currentItemNum = currentRouterTable.getCurrentItemNum();
        String[] targetNetwork = currentRouterTable.getTargetNetwork();
        int[] hopCount = currentRouterTable.getHopCount();
        String[] nextRouter = currentRouterTable.getNextRouter();

        // 进行输出
        System.out.println("  Target      Hop      Next");
        for (int i = 0; i < currentItemNum; i++) {
            System.out.println("    " + targetNetwork[i] + "         " + hopCount[i] + "         " + nextRouter[i]);
        }
        System.out.println();
    }
}
