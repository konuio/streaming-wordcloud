import com.google.common.base.Throwables;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Map;

/**
 * Created by matt on 3/13/16.
 */
public class WordCount {
    public static void main(String[] args) {
        try {
            SparkConf conf = new SparkConf();
            conf.setAppName("WordCount");
            conf.setMaster("spark://localhost:7077");
            JavaSparkContext sc = new JavaSparkContext(conf);
            JavaRDD<String> words = sc.textFile("words.txt");
            Map<String, Long> counts = words.countByValue();
            for (Map.Entry<String, Long> entry : counts.entrySet()) {
                System.out.printf("[%d] %s\n", entry.getValue(), entry.getKey());
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
