import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;


public class SocketWindowWordCount {

    public static void main(String[] args) throws Exception {
        String ip;
        int port;
        try {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.err.println("No ip/port specified.");
            return;
        }
        System.out.println(ip + ":" + port);

        // 获取flink的运行环境，这里获取的是流计算执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 绑定一个socket地址，获取socket内容
        DataStream<String> text = env.socketTextStream(ip, port, "\n");

        // 切分、压平、记1、分组、设置窗口参数、计数
        DataStream<WordWithCount> wordCounts = text.flatMap((FlatMapFunction<String, WordWithCount>) (value, out) -> {
                    for (String s : value.split("\n")) {
                        out.collect(new WordWithCount(s, 1L));
                    }
                }).keyBy("word")
                .timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce((ReduceFunction<WordWithCount>) (wc1, wc2) -> new WordWithCount(wc1.word, wc1.count + wc2.count));
        // 将结果输出在控制台
        wordCounts.print();
        // flink提交任务，开始计算
        env.execute("Socket Window Word Count Java");
    }

    // Data type for words with count
    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}
