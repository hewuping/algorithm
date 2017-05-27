package hash;

import java.util.*;

/**
 * @author Zero
 *         Created on 2017/5/27.
 */
public class KetamaHashingTest {


    public static void main(String[] args) {
        //环上的机器结点
        List<String> nodes = Arrays.asList("10.0.0.1", "10.0.0.2", "10.0.0.3");
        TreeMap<Long, String> hashNodes = new TreeMap<>();
        int virtualNodeCount = 2;
        for (String node : nodes) {
            for (int i = 0; i < virtualNodeCount; i++) {
//                newNodeMap.put(ConsistentHashing.KETAMA_HASH.hash(node + "-" + i), node);//生产
                hashNodes.put(ConsistentHashing.KETAMA_HASH.hash(node + "-" + i), node + "-" + i);//debug
            }
        }
        System.out.println(hashNodes);

        //模拟用户访问
        String clientIP = "183.15.246.157";
        long clientIPHash = ConsistentHashing.KETAMA_HASH.hash(clientIP);
        SortedMap<Long, String> tailMap = hashNodes.tailMap(clientIPHash);
        Long firstKey = tailMap.firstKey();

        System.out.println("Route to node : " + firstKey+" => "+hashNodes.get(firstKey));
    }


}
