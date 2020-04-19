package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.mapper.ReplyMapper;
import com.marcinwo.youtubeapi.demo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ReplyController {


    private ReplyService replyService;
    private ReplyMapper replyMapper;

    @Autowired
    public ReplyController(ReplyService replyService, ReplyMapper replyMapper) {
        this.replyService = replyService;
        this.replyMapper = replyMapper;
    }


    @GetMapping("/replies")
    public Set<ReplyDTO> getReplies(){
        return replyMapper.toReplyDTO(replyService.getReplies());
    }

    //TODO  TO DO
    //@GetMapping("/comments/{id}/replies")

    @PostMapping("/replies")
    public ReplyDTO postReply(@RequestBody ReplyDTO replyDTO){
        return replyMapper.toReplyDTO(replyService.postReply(replyMapper.toReplyEntity(replyDTO)));
    }


}
