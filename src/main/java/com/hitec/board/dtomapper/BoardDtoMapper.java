package com.hitec.board.dtomapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hitec.board.dto.BoardDTO;
import com.hitec.board.model.Board;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardDtoMapper {
    
    BoardDtoMapper MAPPER = Mappers.getMapper(BoardDtoMapper.class);

    /**
     * board 객체를 boardDto 에 매핑
     * @param board
     * @return
     */
    @Mapping(target="id", source="board.id")
    @Mapping(target="title", source="board.title")
    @Mapping(target="content", source="board.content")
	BoardDTO boardToBoardDto(Board board);
    /**
     * boardDto 객체를 Board 에 매핑 
     * @param boardDto
     * @return
     */
    @Mapping(target="id", source="boardDto.id")
    @Mapping(target="title", source="boardDto.title")
    @Mapping(target="content", source="boardDto.content")
    Board boardDtoToBoard(BoardDTO boardDto);
}
