package com.spring.client.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.client.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	private SqlSession session;

	// �۸�� ����
	@Override
	public List<ReplyVO> replyList(Integer b_num) {
		return session.selectList("replyList", b_num);
	}

	// ���Է� ����
	@Override
	public int replyInsert(ReplyVO rvo) {
		return session.insert("replyInsert");
	}

	@Override
	public int pwdConfirm(ReplyVO rvo) {
		return (Integer) session.selectOne("pwdConfirm");
	}

	// �ۼ��� ����
	@Override
	public int replyUpdate(ReplyVO rvo) {
		return session.update("replyUpdate");
	}

	// �ۻ��� ���� ( ��� ��ȣ�� ����)
	@Override
	public int replyDelete(int r_num) {
		return session.delete("replyDelete", r_num);
	}
	
	/*
	 * �ι��� ��� 
	 * //�ۻ��� ����(�Խñ� ��ȣ�� �ش� �Խñ� ��ü ��� ����)
	 * @Override public int replyChoiceDelete(int b_num) { return
	 * session.delete("replyChoiceDelete", b_num); }
	 */
}