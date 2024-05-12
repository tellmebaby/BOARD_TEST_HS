package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.dto.Board;
import com.aloha.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    /**
     * 게시글 목록 조회
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list( Model model ) throws Exception {
        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }
    
    /**
     * 게시글 조회 화면
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model) throws Exception{
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "board/read";
    }
    
    
    /**
     * 게시글 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        return "board/insert";
    }
    
    /**
     * 게시글 등록 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insertPro( Board board ) throws Exception {
        int result = boardService.insert(board);
        if ( result > 0 ) {
            log.info("게시글 등록 성공");
            return "redirect:/board/list";
        }
        log.info("게시글 등록 실패");
        return "redirect:/board/insert?error";
    }
    
    
    /**
     * 게시글 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception{
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "board/update";
    }
    
    
    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro( Board board ) throws Exception {
        int result = boardService.update(board);
        if ( result > 0 ) {
            log.info("수정 성공");
            return "redirect:/board/list";
        }
        log.info("수정 실패");

        int no = board.getNo();

        return "redirect:/board/update?no=" + no +"?error";
    }
    
    
    /**
     * 게시글 삭제 처리
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = boardService.delete(no);
        if( result > 0 ) {
            log.info("삭제 성공");
            return "redirect:/board/list";
        }
        log.info("삭제 성공");
        return "redirect:/board/update?no=" + no + "?error";
    }
    

}
