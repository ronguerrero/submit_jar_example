package com.example.jar;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class ParquetToDeltaWriter {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: ParquetToDeltaWriter <input_parquet_dir> <output_delta_dir>");
            System.exit(1);
        }

        String inputDir = args[0];
        String outputDir = args[1];

        SparkSession spark = SparkSession.builder().getOrCreate();

        // Read Parquet
        Dataset<Row> df = spark.read().format("parquet").load(inputDir);

        // Write as Delta
        df.write().format("delta").save(outputDir);
    }
}
