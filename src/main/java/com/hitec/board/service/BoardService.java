package com.hitec.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitec.board.exception.BoardNotFoundException;
import com.hitec.board.model.Board;
import com.hitec.board.repository.BoardRepository;

@Service
public class BoardService {
    BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardList(){
        return boardRepository.findAll();
    }

    public Board getBoard(Long id){
        Optional<Board> boardOpt = boardRepository.findById(id);
        return boardOpt.orElseThrow(() -> new BoardNotFoundException("board not found for id " + id));
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }
    
}