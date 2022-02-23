package component;

import java.util.Arrays;

/**
 * 路由表
 */
public class RouterTable {
    // 最大项目数
    private static final int MAX_ITEM_NUM;


    // 当前项目数
    private int currentItemNum;

    // 目的网络
    private String[] targetNetwork;

    // 距离（跳数）
    private int[] hopCount;

    // 下一跳路由器
    private String[] nextRouter;


    // 静态块
    static {
        // 初始化最大项目数
        MAX_ITEM_NUM = 6;
    }

    // 构造方法
    public RouterTable(int vexIndex, AbstractVex[] vexSet, int[][] matrix) {
        currentItemNum = 0;

        targetNetwork = new String[MAX_ITEM_NUM];
        hopCount = new int[MAX_ITEM_NUM];
        nextRouter = new String[MAX_ITEM_NUM];

        for (int i = 0; i < matrix[vexIndex].length; i++) {
            if (matrix[vexIndex][i] == 1) {
                // 当前项目指针
                int currentItemIndex = currentItemNum;

                // 当前项目数加1
                currentItemNum++;

                // 当前项目赋值
                targetNetwork[currentItemIndex] = vexSet[i].getVexName();
                hopCount[currentItemIndex] = 1;
                nextRouter[currentItemIndex] = null;
            }
        }
    }

    // 复制构造方法
    public RouterTable(RouterTable originalRouterTable) {
        this.currentItemNum = originalRouterTable.currentItemNum;
        this.targetNetwork = Arrays.copyOf(originalRouterTable.targetNetwork, MAX_ITEM_NUM);
        this.hopCount = Arrays.copyOf(originalRouterTable.hopCount, MAX_ITEM_NUM);
        this.nextRouter = Arrays.copyOf(originalRouterTable.nextRouter, MAX_ITEM_NUM);
    }

    // 获取当前项目数
    public int getCurrentItemNum() {
        return currentItemNum;
    }

    // 获取目的网络
    public String[] getTargetNetwork() {
        return targetNetwork;
    }

    // 获取距离
    public int[] getHopCount() {
        return hopCount;
    }

    // 获取下一跳路由器
    public String[] getNextRouter() {
        return nextRouter;
    }

    // 设置当前项目数
    public void setCurrentItemNum(int valueOfCurrentItemNum) {
        currentItemNum = valueOfCurrentItemNum;
    }

    // 设置某一项的目的网络
    public void setTargetNetwork(int itemIndex, String valueOfTargetNetwork) {
        targetNetwork[itemIndex] = valueOfTargetNetwork;
    }

    // 设置某一项的距离
    public void setHopCount(int itemIndex, int valueOfHopCount) {
        hopCount[itemIndex] = valueOfHopCount;
    }

    // 设置某一项的下一跳路由器
    public void setNextRouter(int itemIndex, String valueOfNextRouter) {
        nextRouter[itemIndex] = valueOfNextRouter;
    }

    // 所有距离加1
    public void hopCountAllPlusOne() {
        for (int i = 0; i < currentItemNum; i++) {
            hopCount[i]++;
        }
    }

    // 将所有下一跳路由器名称改为指定路由器名称
    public void setAllNextRouter(Router router) {
        String routerName = router.getVexName();

        for (int i = 0; i < currentItemNum; i++) {
            nextRouter[i] = routerName;
        }
    }

    // 查找某一目的网络对应的项目下标，未找到则返回-1
    public int searchTargetNetworkIndex(String targetNetworkName) {
        for (int i = 0; i < currentItemNum; i++) {
            if (targetNetwork[i].equals(targetNetworkName)) {
                return i;
            }
        }
        return -1;
    }

    // 追加一个项目
    public void appendItem(String targetNetworkName, int hopCountValue, String nextRouterName) {
        targetNetwork[currentItemNum] = targetNetworkName;
        hopCount[currentItemNum] = hopCountValue;
        nextRouter[currentItemNum] = nextRouterName;
        currentItemNum++;
    }
}
