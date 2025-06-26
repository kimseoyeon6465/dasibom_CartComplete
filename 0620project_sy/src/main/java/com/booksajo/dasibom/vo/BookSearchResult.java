package com.booksajo.dasibom.vo;

import java.util.List;

public class BookSearchResult {
    private List<BookVO> books;
    private int total;

    public BookSearchResult(List<BookVO> books, int total) {
        this.books = books;
        this.total = total;
    }

    public List<BookVO> getBooks() { return books; }
    public int getTotal() { return total; }
}