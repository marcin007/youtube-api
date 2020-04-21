package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ReplyService {

    private ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public List<Reply> getReplies(){
        return replyRepository.findAll();
    }

    public List<Reply> getReply(Long id){
        return replyRepository.findAllByCommentId(id);
    }

    public Reply postReply(Reply reply){
        return replyRepository.save(reply);
    }
}
