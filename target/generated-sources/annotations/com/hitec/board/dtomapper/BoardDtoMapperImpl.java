package com.hitec.board.dtomapper;

import com.hitec.board.dto.BoardDTO;
import com.hitec.board.model.Board;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T07:14:27+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
public class BoardDtoMapperImpl implements BoardDtoMapper {

    @Override
    public BoardDTO boardToBoardDto(Board board) {
        if ( board == null ) {
            return null;
        }

        long id = 0L;
        String title = null;
        String content = null;
        String userName = null;

        if ( board.getId() != null ) {
            id = board.getId();
        }
        title = board.getTitle();
        content = board.getContent();
        userName = board.getUserName();

        BoardDTO boardDTO = new BoardDTO( id, title, content, userName );

        return boardDTO;
    }

    @Override
    public Board boardDtoToBoard(BoardDTO boardDto) {
        if ( boardDto == null ) {
            return null;
        }

        Board board = new Board();

        board.setId( boardDto.getId() );
        board.setTitle( boardDto.getTitle() );
        board.setContent( boardDto.getContent() );
        board.setUserName( boardDto.getUserName() );

        return board;
    }
}
