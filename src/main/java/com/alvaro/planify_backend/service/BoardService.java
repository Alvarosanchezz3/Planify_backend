package com.alvaro.planify_backend.service;

import com.alvaro.planify_backend.entity.Board;
import com.alvaro.planify_backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoardsByUserEmail(String userEmail) {
        return boardRepository.findByUserEmail(userEmail);
    }
}