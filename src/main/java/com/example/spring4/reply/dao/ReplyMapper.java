package com.example.spring4.reply.dao;

import com.example.spring4.reply.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ReplyMapper {
    //댓글 리스트
    List<ReplyVO> getReplyByBbsNO(int no);
    //댓글 추가
    int insertReply(ReplyVO replyVO);
    //댓글 수정
    int updateReply(ReplyVO replyVO);
    //댓글 삭제
    int deleteReply(int id);
}
