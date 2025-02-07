package com.example.demo.controller;

import com.example.demo.dto.request.ReservationListRequestDTO;
import com.example.demo.dto.request.ReservationRequestDTO;
import com.example.demo.dto.response.ReservationResponseDTO;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 등록
    @PostMapping
    public ResponseEntity<?> create (@RequestBody ReservationRequestDTO requestDTO) {
        log.info("/reservation - POST 요청 requestDTO: {}", requestDTO);
        reservationService.create(requestDTO);
        log.info("성공");
        return ResponseEntity.ok().body("");
    }

    // 예약 취소
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable String id) {
        log.info("/reservation - DELETE 요청. id: {}", id);
        if(id == null || id.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("올바른 예약 id를 보내주세요.");
        }
        boolean isDeleted =  reservationService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest().body("해당 예약이 존재하지 않습니다");
        }
        return ResponseEntity.ok().body("예약이 취소되었습니다.");
    }

    // 예약 불러오기
    @PostMapping("/list")
    public ResponseEntity<?> retrieve (@RequestBody ReservationListRequestDTO dto) {
        log.info("/reservation/list - post: {}", dto);
        List<ReservationResponseDTO> retrieve = reservationService.retrieve(dto);
        log.info("/reservation/list - responseDTO: {}", retrieve);
        return ResponseEntity.ok().body(retrieve);
    }

}
