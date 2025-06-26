package com.booksajo.dasibom.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.booksajo.dasibom.vo.UsedBookVO;
import com.booksajo.dasibom.service.UsedBookService;

@Controller
public class JunggoController {

    @Autowired
    private UsedBookService usedBookService;

    @RequestMapping("/junggo_list.do")
    public String showListPage(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        int totalCount = usedBookService.getUsedBookCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;

        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;

        ArrayList<UsedBookVO> list = usedBookService.getUsedBooksByPage(startRow, endRow);

        // ?ó¨?ü¨ ?ù¥ÎØ∏Ï? Í≤ΩÎ°ú Ï§? Ï≤? Î≤àÏß∏Îß? ?ç∏?Ñ§?ùº?ö©?úºÎ°? ?ì∞Í≥?,
        // ?†ÑÏ≤? Í≤ΩÎ°ú Î¶¨Ïä§?ä∏Î•? VO?óê ?ûÑ?ãúÎ°? Ï∂îÍ??ïò?äî Î∞©Ïãù
        for (UsedBookVO post : list) {
            String fullPath = post.getImage_Path();
            if (fullPath != null && !fullPath.trim().isEmpty()) {
                String[] paths = fullPath.split(";");
                post.setImage_Path(paths[0].trim()); // ?ç∏?Ñ§?ùº?ö© Ï≤? Î≤àÏß∏ ?ù¥ÎØ∏Ï? ?Ñ§?†ï

                // Î¶¨Ïä§?ä∏Î°úÎèÑ ?ù¥ÎØ∏Ï? Í≤ΩÎ°úÎ•? ?†Ñ?ã¨?ïòÍ≥? ?ã∂?ã§Î©? UsedBookVO?óê List<String> imageList ?ïÑ?ìú Ï∂îÍ? ?ïÑ?öî
                List<String> imageList = new ArrayList<String>();
                for (String path : paths) {
                    if (!path.trim().isEmpty()) {
                        imageList.add(path.trim());
                    }
                }
                post.setImagePathList(imageList); // VO?óê setter ÎßåÎì§?ñ¥?ïº ?ï®
            }
        }

        model.addAttribute("usedBookList", list);
        
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasPrev", page > 1);
        model.addAttribute("hasNext", page < totalPages);
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);

        return "junggo_list";
    }


    @RequestMapping("/junggo_view.do")
    public String showViewPage(@RequestParam("post_Id") int post_Id, Model model) {
    	
        UsedBookVO book = usedBookService.getUsedBook(post_Id);
        
        model.addAttribute("book", book);
        return "junggo_view";
    }

    @GetMapping("/junggo_write.do")
    public String showWritePage() {
        return "junggo_write";
    }

    @PostMapping("/junggo_write.do")
    public String submitWrite(
        @ModelAttribute UsedBookVO usedBook,
        @RequestParam(value = "images", required = false) MultipartFile[] images,
        HttpServletRequest request
    ) {
        // ?ûë?Ñ±?ùº ?Ñ§?†ï
        usedBook.setPost_Date(new Date());

        // ?ù¥ÎØ∏Ï? ???û• Ï≤òÎ¶¨
        if (images != null && images.length > 0) {
            String uploadDir = request.getServletContext().getRealPath("/resources/uploads/");
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            StringBuilder imagePaths = new StringBuilder();

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String originalFilename = image.getOriginalFilename();
                    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String newFileName = UUID.randomUUID().toString() + extension;

                    File dest = new File(uploadPath, newFileName);
                    try {
                        image.transferTo(dest);
                        if (imagePaths.length() > 0) imagePaths.append(";");
                        imagePaths.append("/resources/uploads/").append(newFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            usedBook.setImage_Path(imagePaths.toString());
        }

        usedBookService.insertUsedBook(usedBook);

        return "redirect:/junggo_list.do";
    }

}
