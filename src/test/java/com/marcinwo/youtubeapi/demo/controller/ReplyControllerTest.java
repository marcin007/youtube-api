package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.mapper.ReplyMapper;
import com.marcinwo.youtubeapi.demo.service.ReplyService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyService replyService;

    @MockBean
    private ReplyMapper replyMapper;

    @Test
    public void getRepliesTest() throws Exception {
        //given
        List<Reply> replies = List.of(
                new Reply("asd", 1),
                new Reply("ert", 2),
                new Reply("hgj", 5)
        );

        Set<ReplyDTO> replyDTOS = Set.of(
                new ReplyDTO(2L, 2L, "uiop"),
                new ReplyDTO(1L, 1L, "qwer"),
                new ReplyDTO(3L, 3L, "asd")
        );
        //when
        when(replyService.getReplies()).thenReturn(replies);


        when(replyMapper.toReplyDTO(anyCollection())).thenReturn(replyDTOS);

        //then
        mockMvc.perform(get("/replies"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(CoreMatchers.containsString("asd")))
                .andExpect(content().json(JsonUtils.toJsonString(replyDTOS)));
    }


}
