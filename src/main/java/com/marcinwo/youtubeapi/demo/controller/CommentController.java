package com.marcinwo.youtubeapi.demo.controller;


import com.marcinwo.youtubeapi.demo.ApiInformation;
import com.marcinwo.youtubeapi.demo.dto.CommentDTO;
import com.marcinwo.youtubeapi.demo.dto.PatchCommentDTO;
import com.marcinwo.youtubeapi.demo.mapper.CommentMapper;
import com.marcinwo.youtubeapi.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;
    private CommentMapper commentMapper;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/films/{id}/comments")
    public List<CommentDTO> getCommentsFilmsById(@PathVariable Long id){
        return commentMapper.toCommentDTO(commentService.getCommentsByFilmId(id));
    }

    @GetMapping("/comments")
    public List<CommentDTO> getComments(){
        return commentMapper.toCommentDTO(commentService.getComments());
    }

    @PostMapping("/comments")
    public CommentDTO postComment (@RequestBody CommentDTO commentDTO){
        return commentMapper.toCommentDTO(commentService.save(commentMapper.toCommentEntity(commentDTO)));
    }

    @PatchMapping("/comments/{id}")
    public CommentDTO updateCommentById(@PathVariable Long id, @Valid @RequestBody PatchCommentDTO patchCommentDTO){
        return commentMapper.toCommentDTO(commentService.updateCommentById(id, patchCommentDTO));
    }

    @DeleteMapping("/comments/{id}")
    public ApiInformation deleteCommentById(@PathVariable Long id){
        commentService.deleteCommentById(id);
        return new ApiInformation("Delete ok", HttpStatus.OK.value());
    }

}
