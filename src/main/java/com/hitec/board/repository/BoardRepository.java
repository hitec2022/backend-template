package com.hitec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hitec.board.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    @Query(value="select * from board", nativeQuery=true)
    List<Board> selectAll();
}
