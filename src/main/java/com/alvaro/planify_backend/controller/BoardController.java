package com.alvaro.planify_backend.controller;

import com.alvaro.planify_backend.entity.Board;
import com.alvaro.planify_backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/user/{userEmail}")
    public List<Board> getBoardsByUserId(@PathVariable String userEmail) {
        return boardService.getBoardsByUserEmail(userEmail);
    }
}
