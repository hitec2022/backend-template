package com.hitec.board.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitec.board.dto.BoardDTO;
import com.hitec.board.model.Board;
import com.hitec.board.service.BoardService;

@WebMvcTest
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "lapine.io", uriPort = 80)
public class BoardControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    @DisplayName("게시글 1건 조회")
    void testGetBoard() throws Exception{
        Board board = new Board();
        board.setId(1L);
        board.setTitle("It's title");
        board.setContent("It's content");

        given(boardService.getBoard(1L)).willReturn(board);

        mockMvc.perform(get("/board/{boardId}", 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andDo(document("{class-name}/{method-name}", 
                pathParameters( // path 파라미터 정보 입력
                        parameterWithName("boardId").description("Board ID") 
                ),
                responseFields(
                    fieldWithPath("id").description("board Id"), 
                    fieldWithPath("title").description("board title"),
                    fieldWithPath("content").description("board content"),
                    fieldWithPath("userName").description("user name")
                )
            ));

    }

    @Test
    @DisplayName("게시글 작성")
    void testSaveBoard() throws Exception{
  
        Board board = new Board();
        board.setTitle("It's title");
        board.setContent("It's content");

        given(boardService.saveBoard(any(Board.class))).will( (InvocationOnMock invocation) -> {
            Board board1 = (Board)invocation.getArgument(0);
            board1.setId(2L);
            return board1;
        });

        mockMvc.perform(post("/board")
            .content(objectMapper.writeValueAsString(new BoardDTO(0, "It's title", "It's Content", "hhh")))
            .contentType("application/json")
            )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("id").value(2L))
            .andDo(document("{class-name}/{method-name}",
                requestFields(
                    fieldWithPath("id").description("board id"),
                    fieldWithPath("title").description("board title"),
                    fieldWithPath("content").description("board content"),
                    fieldWithPath("userName").description("username")
                ),
                responseFields(
                    fieldWithPath("id").description("board Id"), 
                    fieldWithPath("title").description("board title"),
                    fieldWithPath("content").description("board content"),
                    fieldWithPath("userName").description("user name")
                )
            ));

    }
}
