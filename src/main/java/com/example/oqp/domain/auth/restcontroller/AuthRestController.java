package com.example.oqp.domain.auth.restcontroller;

import com.example.oqp.common.error.ErrorResponse;
import com.example.oqp.common.jwt.JwtTokenResponse;
import com.example.oqp.db.entity.UserInfo;
import com.example.oqp.domain.auth.restcontroller.request.LoginRequest;
import com.example.oqp.domain.auth.restcontroller.request.RegisterRequest;
import com.example.oqp.domain.auth.service.AuthRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "사용자 Auth Controller")
public class AuthRestController {

    private final AuthRestService authRestService;

    @Operation(summary = "사용자 회원가입 API", description = "사용자 회원가입 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfo.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping("/register")
    public ResponseEntity<UserInfo> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "사용자 회원가입 요청 객체"
            )
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authRestService.register(registerRequest));
    }

    @Operation(summary = "사용자 로그인 API", description = "사용자 로그인 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JwtTokenResponse.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "사용자 로그인 요청 객체"
            )
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authRestService.login(loginRequest));
    }
}