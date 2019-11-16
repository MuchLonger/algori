package Day5;

import java.util.Arrays;

/**
 * @description: 有一个数组：存的是会议开始时间和结束时间，当前有一个会议室，求当前会议室最多能执行多少个会议
 *
 * 思路：使用会议的结束时间来做贪心算法
 *
 * 做法：先将数组按会议结束时间排序，之后排除掉“会议开始时间”在会议结束时间之间的所有会议，遍历一轮数组就能找到能执行多少个会议
 * @Time: 2019/11/15 21:21
 */
public class ArrangeMeeting {

    //会议类
    public static class Meeting {
        public int startTime;
        public int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * 求出最多能开启多少个会议
     * @param meetings 会议总数
     * @param curTime 表示当前时间
     * @return
     */
    public static int bestArrange(Meeting[] meetings,int curTime){
        // 先按照会议的结束时间排序
        Arrays.sort(meetings,(o1,o2)->o1.endTime-o2.endTime);
        int howTime=0;  //记录能执行几次
        for (int i = 0; i < meetings.length; i++) {
            // 因为meeting是按照“结束时间”排的，所以越是在前面的就越早执行。并筛选出执行过程中同时会开的会议（即下个会议开始时间小于当前会议结束时间）
            // 这个if语句表示“当前时间”需要 小于“下个会议开始时间”！！！
            if (curTime <= meetings[i].startTime) {
                curTime=meetings[i].endTime;
                howTime++;
            }
        }
        return howTime;
    }

}
