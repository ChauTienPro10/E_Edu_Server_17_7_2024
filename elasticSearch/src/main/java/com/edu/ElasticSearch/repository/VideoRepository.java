package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.Video;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface VideoRepository extends ElasticsearchRepository<Video,String> {
    Video findByTitle(String title);
    List<Video> findByCourse(String course);
    Video findByStt(int stt);


    @Query("{\"bool\": { \"must\": [ { \"term\": { \"course\": \"?0\" } }, { \"range\": { \"stt\": { \"gte\": ?1 } } } ] }}")
    List<Video> findByCourseAndSttGreaterThanEqual(String course, int stt);


    @Query("{\"range\": {\"stt\": {\"gte\": ?0}}}")
    List<Video> findBySttGreaterThanEqual(int stt);

    @Query("{\"query_string\": {\"query\": \"?0\", \"fields\": [\"title\", \"description\"]}}")
    List<Video> searchByQueryString(String text);

}
