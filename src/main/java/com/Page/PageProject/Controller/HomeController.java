package com.Page.PageProject.Controller;

import com.Page.PageProject.Entity.PageEntity;
import com.Page.PageProject.Repository.PageRepository;
import com.Page.PageProject.dto.CommentDto;
import com.Page.PageProject.dto.dtoForm;
import com.Page.PageProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private CommentService commentService;


    @GetMapping("/")
    public String home(Model model) {
      List<PageEntity> PageEntityList = pageRepository.findAll();
      model.addAttribute("EntityList",PageEntityList);
        return "homePage";
    };


    @GetMapping("/post")
    public String createPage() {
        return "create";
    }

    @PostMapping("/post/create")
    public String createForm(dtoForm dtoform) {
        PageEntity pageentity = dtoform.toEntity(dtoform);

        PageEntity saved = pageRepository.save(pageentity);

        return "redirect:/detail/" + saved.getId();
    }

    @GetMapping("/detail/{id}")
    public String DetailPage(@PathVariable Long id, Model model) {
        PageEntity pageEntity = pageRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);

        model.addAttribute("Entity",pageEntity);
        model.addAttribute("commentDtos", commentsDtos);



        return "detailbyid";
    }

    @GetMapping("/detail/{id}/edit")
    public String Edited(@PathVariable Long id, Model model) {
        PageEntity pageEntity = pageRepository.findById(id).orElse(null);

        model.addAttribute("Entity",pageEntity);
        return "EditPage";
    }

    @PostMapping("/detail/update")
    public String update(dtoForm dtoform) {

        PageEntity pageEntity = dtoform.toEntity(dtoform);

        PageEntity target = pageRepository.findById(pageEntity.getId()).orElse(null);

        if (target != null) {
            pageRepository.save(pageEntity);
        }
    return "redirect:/detail/" + pageEntity.getId();
    }

    @GetMapping("/detail/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        PageEntity target = pageRepository.findById(id).orElse(null);


        if (target != null) {
            pageRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다");
        }

        return "redirect:/";
    }


}
