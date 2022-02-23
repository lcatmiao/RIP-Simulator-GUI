package component;

/**
 * 整个网络的拓扑图
 */
public class NetworkGraph {
    // 顶点的数量
    private final int VEX_NUM;

    // 边的数量
    private final int EDGE_NUM;

    // 路由器的数量
    private final int ROUTER_NUM;

    // 网络的数量
    private final int NETWORK_NUM;

    // 顶点集合（Router, Network）
    private AbstractVex[] vexSet;

    // 邻接矩阵
    private int[][] matrix;


    // 构造方法
    public NetworkGraph() {
        // 初始化顶点的数量、边的数量、路由器的数量、网络的数量
        VEX_NUM = 12;
        EDGE_NUM = 13;
        ROUTER_NUM = 6;
        NETWORK_NUM = 6;

        // 初始化顶点集合（路由器顶点 + 网络顶点）
        // 初始化顶点集合，集合中的每一个元素都是抽象顶点
        vexSet = new AbstractVex[VEX_NUM];

        // 初始化路由器顶点，顶点0~5为路由器顶点
        vexSet[0] = new Router(0, 'R', "R0");
        vexSet[1] = new Router(1, 'R', "R1");
        vexSet[2] = new Router(2, 'R', "R2");
        vexSet[3] = new Router(3, 'R', "R3");
        vexSet[4] = new Router(4, 'R', "R4");
        vexSet[5] = new Router(5, 'R', "R5");

        // 初始化网络顶点，顶点6~11为网络顶点
        vexSet[6] = new Network(6, 'N', "N0");
        vexSet[7] = new Network(7, 'N', "N1");
        vexSet[8] = new Network(8, 'N', "N2");
        vexSet[9] = new Network(9, 'N', "N3");
        vexSet[10] = new Network(10, 'N', "N4");
        vexSet[11] = new Network(11, 'N', "N5");

        // 初始化邻接矩阵
        matrix = new int[VEX_NUM][VEX_NUM];

        /*
         * 邻接矩阵：
         *
         *       0  1  2  3  4  5  6  7  8  9  10  11
         *       _  _  _  _  _  _  _  _  _  _  _  _
         * 0  |  0  0  0  0  0  0  1  1  1  0  0  0
         * 1  |  0  0  0  0  0  0  0  0  1  1  0  0
         * 2  |  0  0  0  0  0  0  0  0  0  1  1  0
         * 3  |  0  0  0  0  0  0  0  1  0  0  0  1
         * 4  |  0  0  0  0  0  0  1  0  0  0  0  1
         * 5  |  0  0  0  0  0  0  0  0  0  0  1  1
         * 6  |  1  0  0  0  1  0  0  0  0  0  0  0
         * 7  |  1  0  0  1  0  0  0  0  0  0  0  0
         * 8  |  1  1  0  0  0  0  0  0  0  0  0  0
         * 9  |  0  1  1  0  0  0  0  0  0  0  0  0
         * 10 |  0  0  1  0  0  1  0  0  0  0  0  0
         * 11 |  0  0  0  1  1  1  0  0  0  0  0  0
         */

        matrix[0][6] = 1;
        matrix[6][0] = 1;

        matrix[0][7] = 1;
        matrix[7][0] = 1;

        matrix[0][8] = 1;
        matrix[8][0] = 1;

        matrix[1][8] = 1;
        matrix[8][1] = 1;

        matrix[1][9] = 1;
        matrix[9][1] = 1;

        matrix[2][9] = 1;
        matrix[9][2] = 1;

        matrix[2][10] = 1;
        matrix[10][2] = 1;

        matrix[3][7] = 1;
        matrix[7][3] = 1;

        matrix[3][11] = 1;
        matrix[11][3] = 1;

        matrix[4][6] = 1;
        matrix[6][4] = 1;

        matrix[4][11] = 1;
        matrix[11][4] = 1;

        matrix[5][10] = 1;
        matrix[10][5] = 1;

        matrix[5][11] = 1;
        matrix[11][5] = 1;
    }

    // 获取顶点集合
    public AbstractVex[] getVexSet() {
        return vexSet;
    }

    // 获取邻接矩阵
    public int[][] getMatrix() {
        return matrix;
    }

    // 输出邻接矩阵
    public void testPrintMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // 初始化所有路由表（得出到几个相邻网络的信息）
    public void initialAllRouterTable() {
        for (int i = 0; i < ROUTER_NUM; i++) {
            ((Router) vexSet[i]).initialRouterTable(vexSet, matrix);
        }
    }

    // 输出指定路由器的路由表
    public void printRouterTable(String routerName) {
        for (int i = 0; i < ROUTER_NUM; i++) {
            if (vexSet[i].getVexName().equals(routerName)) {
                ((Router) vexSet[i]).printRouterTable();
                return;
            }
        }
    }

    // 输出所有路由表
    public void printAllRouterTable() {
        for (int i = 0; i < ROUTER_NUM; i++) {
            ((Router) vexSet[i]).printRouterTable();
        }
    }

    /**
     * 两个路由表之间的发送与更新
     * <p>
     * 采用距离向量算法
     * <p>
     * 路由器send->路由器receive
     */
    private void oneSendOneUpdate(Router send, Router receive) {
        // 发送的路由表
        RouterTable sentRouterTable = send.getRouterTable();

        // 将发来的路由表拷贝一份，生成一个临时路由表
        RouterTable tpRouterTable = new RouterTable(sentRouterTable);

        // 修改这个临时路由表
        tpRouterTable.hopCountAllPlusOne();
        tpRouterTable.setAllNextRouter(send);

        // 临时路由表的当前项目数
        int tpRouterTableCurrentItemNum = tpRouterTable.getCurrentItemNum();

        // 临时路由表的目的网络
        String[] tpTargetNetwork = tpRouterTable.getTargetNetwork();

        // 临时路由表的距离
        int[] tpHopCount = tpRouterTable.getHopCount();

        // 临时路由表的下一跳路由器
        String[] tpNextRouter = tpRouterTable.getNextRouter();

        // 接收路由器的路由表
        RouterTable reRouterTable = receive.getRouterTable();

        // 接收路由器路由表的距离
        int[] reHopCount = reRouterTable.getHopCount();

        // 接收路由器路由表的下一跳路由器
        String[] reNextRouter = reRouterTable.getTargetNetwork();

        // 更新接收路由器的路由表
        for (int i = 0; i < tpRouterTableCurrentItemNum; i++) {
            int index;

            // 若未找到相同的目的网络，则追加记录
            if ((index = reRouterTable.searchTargetNetworkIndex(tpTargetNetwork[i])) == -1) {
                reRouterTable.appendItem(tpTargetNetwork[i], tpHopCount[i], tpNextRouter[i]);
                continue;
            }

            // 若找到了相同的目的网络，且下一跳相同，则更新记录
            if (tpNextRouter[i].equals(reNextRouter[index])) {
                reRouterTable.setHopCount(index, tpHopCount[i]);
                continue;
            }

            // 若找到了相同的目的网络，但下一跳不同，则比较距离。若距离小，则替换记录；若距离大，则什么都不做
            if (tpHopCount[i] < reHopCount[index]) {
                reRouterTable.setHopCount(index, tpHopCount[i]);
                reRouterTable.setNextRouter(index, tpNextRouter[i]);
            }
        }
    }

    // 随机执行一次发送与更新
    public void randomSendAndUpdate() {
        int random = (int) (Math.random() * 16);

        switch (random) {
            case 0:
                oneSendOneUpdate((Router) vexSet[0], (Router) vexSet[4]);
                break;
            case 1:
                oneSendOneUpdate((Router) vexSet[4], (Router) vexSet[0]);
                break;
            case 2:
                oneSendOneUpdate((Router) vexSet[0], (Router) vexSet[3]);
                break;
            case 3:
                oneSendOneUpdate((Router) vexSet[3], (Router) vexSet[0]);
                break;
            case 4:
                oneSendOneUpdate((Router) vexSet[0], (Router) vexSet[1]);
                break;
            case 5:
                oneSendOneUpdate((Router) vexSet[1], (Router) vexSet[0]);
                break;
            case 6:
                oneSendOneUpdate((Router) vexSet[1], (Router) vexSet[2]);
                break;
            case 7:
                oneSendOneUpdate((Router) vexSet[2], (Router) vexSet[1]);
                break;
            case 8:
                oneSendOneUpdate((Router) vexSet[2], (Router) vexSet[5]);
                break;
            case 9:
                oneSendOneUpdate((Router) vexSet[5], (Router) vexSet[2]);
                break;
            case 10:
                oneSendOneUpdate((Router) vexSet[3], (Router) vexSet[4]);
                break;
            case 11:
                oneSendOneUpdate((Router) vexSet[3], (Router) vexSet[5]);
                break;
            case 12:
                oneSendOneUpdate((Router) vexSet[4], (Router) vexSet[3]);
                break;
            case 13:
                oneSendOneUpdate((Router) vexSet[4], (Router) vexSet[5]);
                break;
            case 14:
                oneSendOneUpdate((Router) vexSet[5], (Router) vexSet[3]);
                break;
            case 15:
                oneSendOneUpdate((Router) vexSet[5], (Router) vexSet[4]);
                break;
        }
    }
}
