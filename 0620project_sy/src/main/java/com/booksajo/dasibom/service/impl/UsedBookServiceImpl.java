package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.service.UsedBookService;
import com.booksajo.dasibom.service.dao.UsedBookDAO;
import com.booksajo.dasibom.vo.UsedBookVO;


@Service
public class UsedBookServiceImpl implements UsedBookService {

    @Autowired
    private UsedBookDAO usedBookDAO;
    
    @Override
    public int getUsedBookCount() {
        return usedBookDAO.selectUsedBookCount();
    }
    
    @Override
    public ArrayList<UsedBookVO> getUsedBooksByPage(int startRow, int endRow) {
        
        return usedBookDAO.selectUsedBooksByPage(startRow, endRow);
    }

    @Override
    public List<UsedBookVO> getAllUsedBooks() {
        return usedBookDAO.selectAll(); // ?Üê MapperÎ•? ?Üµ?ï¥ DB ?ò∏Ï∂?
    }

    @Override
    public UsedBookVO getUsedBook(int post_Id) {
        return usedBookDAO.selectId(post_Id);
    }

    @Override
    public int insertUsedBook(UsedBookVO vo) {
        return usedBookDAO.insert(vo);
    }

    @Override
    public int updateUsedBook(UsedBookVO vo) {
        return usedBookDAO.update(vo);
    }

    @Override
    public int deleteUsedBook(int post_Id) {
        return usedBookDAO.delete(post_Id);
    }
    
}
