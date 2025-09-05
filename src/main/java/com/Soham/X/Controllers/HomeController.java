package com.Soham.X.Controllers;

import com.Soham.X.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    public ResponseEntity<ApiResponse> homeController(){
        ApiResponse res= new ApiResponse("Welcome to twitter api",true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);

    }
}
