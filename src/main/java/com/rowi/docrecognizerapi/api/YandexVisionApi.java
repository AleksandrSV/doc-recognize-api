package com.rowi.docrecognizerapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rowi.docrecognizerapi.model.yandex_api.YandexCloudRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YandexVisionApi {
    /**
     *
     * @param content - закодированнае изображение в формате base64
     * @return {@code }
     */
    public ResponseEntity<String> recognition(String content) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Api-Key AQVNxRwLjq1NjctY3cIOV9S-qy_UGkdTlnM2uVrk");

            YandexCloudRequest yandexCloudRequest = new YandexCloudRequest();
            yandexCloudRequest.addContent(content);
            ObjectMapper objectMapper = new ObjectMapper();
            String objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(yandexCloudRequest);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(objJackson, headers);
            return restTemplate.postForEntity( "https://vision.api.cloud.yandex.net/vision/v1/batchAnalyze", request , String.class );
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
