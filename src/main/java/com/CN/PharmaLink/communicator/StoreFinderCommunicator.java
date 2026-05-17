package com.CN.PharmaLink.communicator;

import com.CN.PharmaLink.dto.MedicalStoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StoreFinderCommunicator {


    private final RestTemplate restTemplate;

    @Autowired
    public StoreFinderCommunicator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate=restTemplateBuilder.build();
    }


    public List<MedicalStoreDto> getNearestMedicalStores(Long userId, Long distance,String jwtToken ) {
        String url = "http://localhost:8081/store/getNearestStores/{userId}/{distance}";

        // Set Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        // Wrap headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Make the REST call
        ResponseEntity<List<MedicalStoreDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<MedicalStoreDto>>() {}
                );

        // Return the list
        return response.getBody();
    }
    public List<MedicalStoreDto> getMedicalStoresWithMedicine(String medicine, String jwtToken) {
        String url = "http://localhost:8081/store/getStoresWithMedicine/{medicine}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Map<String , Long>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<MedicalStoreDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<MedicalStoreDto>>() {
                        }
                );

        return response.getBody();
    }

}
