package graph;

public class Floyd {

    private int mEdNum; // 边的数量
    private char[] mVexs; // 定点集合
    private int[][] mMatrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE; //最大值

    /**
     * floyd 最短路径
     * @param path path[i][j] = k => 定点i到定点j的最短路径会经过定点k
     * @param dist dist[i][j] 表示最短路径
     */
    public void floyd(int[][] path,int[][] dist){
        // 初始化
        for (int i = 0;i<mVexs.length;i++){
            for (int j = 0;j<mVexs.length;j++){
                path[i][j] = j;
                dist[i][j] = mMatrix[i][j];
            }
        }

        // 计算最短路径
        for (int k = 0;k<mVexs.length;k++){
            for (int i = 0;i<mVexs.length;i++){
                for (int j = 0;j<mVexs.length;j++){
                    int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                    if(dist[i][j] > tmp){
                        dist[i][j] = tmp;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
    }
}
