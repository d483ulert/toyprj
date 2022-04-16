package com.my.toyprj.board.controller;
import com.my.toyprj.board.dto.BoardDTO;
import com.my.toyprj.board.entity.Board;
import com.my.toyprj.board.repository.BoardRepository;
import com.my.toyprj.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/list")
    public String BoardList(Model model) {
        model.addAttribute("data",boardService.list());

        return "board/boardList";
    }

    @GetMapping("/read")
    public String BoardRead(int num, Model model)  {
        model.addAttribute("data",boardService.read(num));
        return "board/boardRead";
    }

    @GetMapping("/write")
    public String BoardWrite(BoardDTO boardDTO ){
        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String CreateBoard(BoardDTO boardDTO,Board board) throws Exception{
        boardDTO.setWriteTime(LocalDateTime.now());
        boardService.write(boardDTO,board);
        return "redirect:/board/list";
    }

    @DeleteMapping("/delete/{num}")
    public String  DeleteBoard(@PathVariable int num)  {
        boardService.delete(num);
        return "redirect:/board/list";

    }

}
