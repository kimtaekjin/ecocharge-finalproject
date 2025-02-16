package com.example.demo.controller;

import com.example.demo.auth.TokenUserInfo;
import com.example.demo.dto.request.BoardRequestDTO;
import com.example.demo.dto.request.BoardUpdateRequestDTO;
import com.example.demo.dto.response.BoardDetailResponseDTO;
import com.example.demo.dto.response.BoardListResponseDTO;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping("/create")
    public ResponseEntity<?> createBoard(BoardRequestDTO requestDTO,
                                         @AuthenticationPrincipal TokenUserInfo userInfo
    ) throws IOException {
        log.info("/board/create - POST 요청: {}", requestDTO);
        log.info("userInfo: {}", userInfo);
        BoardListResponseDTO boardListResponseDTO = boardService.create(requestDTO,userInfo);

        return ResponseEntity.ok().body(boardListResponseDTO);

    }

    // 게시판 상세보기
    @GetMapping("/detail")
    public ResponseEntity<?> boardDetail(
            @RequestParam(name = "boardNo", defaultValue = "1") int boardNo
    ){
        log.info("/board/detail?boardNo={} GET response", boardNo);

        try {
            final BoardDetailResponseDTO responseDTO = boardService.boardDetail((long) boardNo);
            log.info("Board detail response: {}", responseDTO);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // 게시판 리스트 목록 불러오기
    @GetMapping
    public ResponseEntity<?> boardList(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        log.info("/board GET!, pageNo: {}", pageNo);
        try {
            BoardListResponseDTO responseDTO = boardService.retrieve(pageNo);

            return ResponseEntity.ok().body(responseDTO);

        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    @GetMapping("/{boardNo}")
    public void updateViewCount(@PathVariable("boardNo") Long boardNo) {
        log.info("/api/todos/{} GET", boardNo);

        boardService.updateViewCount(boardNo);
    }
    // board 삭제 요청 처리 (관리자)
    // 로그인 연동이 확인이 되면 qnaNo 와 함께 userInfo 넘겨줄 예정
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<?> deleteBoard(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @PathVariable("boardNo") String boardNo
    ){

        log.info("/api/todos/{} DELETE request!", boardNo);
        log.info("delete userInfo: {}", userInfo);

        if(boardNo == null){
            return ResponseEntity.badRequest()
                    .body("게시판 번호를 전달해 주세요.");
        }

        try {
            BoardListResponseDTO responseDTO = boardService.delete(Long.valueOf(boardNo), userInfo.getUserId());
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // 게시물 수정 요청 (관리자)
    // 로그인 연동이 확인이 되면 qnaNo 와 함께 userInfo 넘겨줄 예정
    @PatchMapping("/{boardNo}")
    public ResponseEntity<?> updateBoard(
            @Validated @RequestBody BoardUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal TokenUserInfo userInfo,
            BindingResult result
    ){

        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if(validatedResult != null) return validatedResult;

        try {
            BoardDetailResponseDTO responseDTO = boardService.update(requestDTO, userInfo);
            return ResponseEntity.ok().body(responseDTO);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }


    // 입력값 검증(Validation)의 결과를 처리해 주는 전역 메서드
    private static ResponseEntity<List<FieldError>> getValidatedResult(BindingResult result) {
        if (result.hasErrors()) { // 입력값 검증 단계에서 문제가 있었다면 true
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(err -> {
                log.warn("invalid client data - {}", err.toString());
            });
            return ResponseEntity
                    .badRequest()
                    .body(fieldErrors);
        }
        return null;
    }




}
