package com.hitec.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hitec.board.dto.BoardDTO;
import com.hitec.board.dtomapper.BoardDtoMapper;
import com.hitec.board.exception.BoardNotFoundException;
import com.hitec.board.model.Board;
import com.hitec.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardController {

    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO saveBoard(@Valid @RequestBody BoardDTO boardDTO){
        log.info("save board");
        Board board = boardService.saveBoard(BoardDtoMapper.MAPPER.boardDtoToBoard(boardDTO));
	    return BoardDtoMapper.MAPPER.boardToBoardDto(board);
    }

    @GetMapping("/boards")
    public List<BoardDTO> getAllBoard(){
        List<Board> boards = boardService.getBoardList();
        if(boards.size() == 0) throw new BoardNotFoundException("No Board in Here");
        return boards.stream().map(BoardDtoMapper.MAPPER::boardToBoardDto).toList();
    }

    @GetMapping("/board/{id}")
    public BoardDTO getBoard(@PathVariable Long id) throws Exception {
        Board board = boardService.getBoard(id);
        return BoardDtoMapper.MAPPER.boardToBoardDto(board);
    }

}
