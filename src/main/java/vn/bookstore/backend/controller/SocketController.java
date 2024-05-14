package vn.bookstore.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import vn.bookstore.backend.model.Book;

@Controller
public class SocketController {

    @MessageMapping("/updateBook")
    @SendTo("/topic/product")
    public Book sendMessage(@Payload Book book) {
        return book;
    }
}

