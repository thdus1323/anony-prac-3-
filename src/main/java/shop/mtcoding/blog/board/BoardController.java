package shop.mtcoding.blog.board;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    //1. 게시판 글쓰기
    @PostMapping("board/save")
   public void save(String title, String content){
        boardNativeRepository.save(title, content);
    }
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    //2. 게시판 목록보기
    @GetMapping("/")
    public String index(HttpServletRequest request){
        List<Board> boardList = boardNativeRepository.findByAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }


    //3. 게시판 보기
    @GetMapping("/board/{id}/detail")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board",board);
        return "board/detail";
    }

    //4. 게시판 삭제하기
    @PostMapping("board/{id}/delete")
    public String deleteById(@PathVariable Integer id){
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    //5. 게시판 수정하기
    @GetMapping("board/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    @PostMapping("board/{id}/update")
    public String updateById(@PathVariable Integer id, String title, String content){
        boardNativeRepository.updateById(id, title, content);
        return "redirect:/";

    }



}
