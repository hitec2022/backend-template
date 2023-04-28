package com.hitec.board.repository;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.hitec.board.model.Board;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Testcontainers
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("Board 생성 테스트")
    void insertBoard(){
        Board board = new Board();
        board.setTitle("hi");
        board.setContent("It's first content");
        board.setUserName("Hitec");
        
        Board savedBoard = boardRepository.save(board);
        Board queryBoard = boardRepository.findById(savedBoard.getId()).orElseThrow();

        Assert.assertEquals(queryBoard.getTitle(), board.getTitle());
    }

}
