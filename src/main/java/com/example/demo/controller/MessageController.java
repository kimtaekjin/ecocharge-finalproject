package com.example.demo.controller;

import com.example.demo.dto.request.MessageRequestDTO;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/api/send-sms")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDTO dto) {
        log.info("controller단에 요청이 들어옴");
        SingleMessageSentResponse response = messageService.sendSms("ECO"+dto.getPhoneNumber());
//        String response = String.valueOf(messageService.sendSms("ECO"+dto.getPhoneNumber()));

//        log.info("phoneNumber: {}", phoneNumber);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/api/socialSend-sms")
    public ResponseEntity<?> socialsendMessage(@RequestBody MessageRequestDTO dto) {
        log.info("소셜controller단에 요청이 들어옴");
        SingleMessageSentResponse response = messageService.sendSms(dto.getPhoneNumber());
//        String response = String.valueOf(messageService.sendSms(dto.getPhoneNumber()));

//        log.info("phoneNumber: {}", phoneNumber);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/Socialverify-code")
    public ResponseEntity<?> SocialverifyCode(@RequestBody MessageRequestDTO request) {
        log.info("request확인: {}",request);
        String phoneNumber =  request.getPhoneNumber();
        String verificationCodeInput = request.getVerificationCodeInput();
        log.info("서비스단 확인:{}", verificationCodeInput);
        boolean response = messageService.verifyCode(phoneNumber, verificationCodeInput);

        log.info("reseponse의 결과값: {} ",response);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody MessageRequestDTO request) {
        log.info("request확인: {}",request);
        String phoneNumber = "ECO"+ request.getPhoneNumber();
        String verificationCodeInput = request.getVerificationCodeInput();
        log.info("서비스단 확인:{}", verificationCodeInput);
        boolean response = messageService.verifyCode(phoneNumber, verificationCodeInput);

        log.info("reseponse의 결과값: {} ",response);
        return  ResponseEntity.ok().body(response);
    }


}
