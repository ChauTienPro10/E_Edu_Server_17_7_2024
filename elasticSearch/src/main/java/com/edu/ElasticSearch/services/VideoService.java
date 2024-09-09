package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.Video;
import com.edu.ElasticSearch.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VideoService {
    VideoRepository videoRepository;
    public Video new_video(Video request){

        if(videoRepository.findByTitle(request.getTitle())==null){
            if(videoRepository.findByStt(request.getStt())==null){
               return videoRepository.save(request);
            }
            else{
                List<Video> vds=videoRepository.findByCourse(
                         request.getCourse());
                for (Video vd:vds){
                    if(vd.getStt()>=request.getStt()){
                        vd.setStt(vd.getStt()+1);
                        videoRepository.save(vd);
                    }

                }
               return videoRepository.save(request);
            }


        }
        return null;
    }
    public  boolean removeVideo(String id){
        Video video=videoRepository.findById(id).get();
        if(video==null){
            return false;
        }
        videoRepository.delete(video);
        return true;
    }

}
