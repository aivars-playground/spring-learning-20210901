package com.example.demo.integration.producer;

import com.example.demo.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.atomic.AtomicLong;

@EnableBatchProcessing
@SpringBootApplication
@RequiredArgsConstructor
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final KafkaTemplate<Long, Customer> kafkaTemplate;

    @Bean
    Job fakeDataGeneratorJob() {
        return this.jobBuilderFactory
                .get("fakeDataGeneratorJob")
                .start(fakeDataGeneratorStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    Step fakeDataGeneratorStep() {
        return this.stepBuilderFactory
                .get("fakeDataGeneratorStep")
                .<Customer, Customer>chunk(10)
                .reader(readerFakeDaaGenerator())
                .writer(kafkaTopicWriter())
                .build();
    }

    //Generates fake data
    @Bean
    ItemReader<Customer> readerFakeDaaGenerator() {
        var id = new AtomicLong();
        return new ItemReader<Customer>() {
            @Override
            public Customer read() throws Exception {
                if (id.incrementAndGet() <= 1_001)
                    return new Customer(id.get(), Math.random() > 0.5 ? "Jane" : "Joe");
                else
                    return null;
            }
        };
    }

    //Writes fake data to kafka topic
    @Bean
    KafkaItemWriter<Long, Customer> kafkaTopicWriter() {
        return new KafkaItemWriterBuilder<Long, Customer>()
                .kafkaTemplate(kafkaTemplate)
                .itemKeyMapper(Customer::getId)
                .build();
    }
}
