package com.example.elderlycare.controller;

import com.example.elderlycare.dto.HospitalInfo;
import com.example.elderlycare.dto.SymptomRequest;
import com.example.elderlycare.dto.SymptomResponse;
import com.example.elderlycare.service.GptService;
import com.example.elderlycare.service.KakaoMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HospitalController {

    private final GptService gptService;
    private final KakaoMapService kakaoMapService;

    @PostMapping("/analyze-symptom")
    public ResponseEntity<Map<String, String>> analyzeSymptom(@RequestBody Map<String, String> request) {
        String symptom = request.get("symptom");
        Map<String, String> result = gptService.analyzeSymptomsAndGetResult(symptom);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search-hospitals")
    public ResponseEntity<List<HospitalInfo>> searchHospitals(
            @RequestParam String department,
            @RequestParam double lat,
            @RequestParam double lng) {
        List<HospitalInfo> hospitals = kakaoMapService.searchNearbyHospitals(department, lat, lng);
        return ResponseEntity.ok(hospitals);
    }
}
