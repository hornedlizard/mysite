package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mysite.vo.BoardVo;

public class ReadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();

	}

}

/*계층형 게시판

no, title, groupNo, orderNo, depth

groupNo :
새글 작성시: 답글이 아닌 새글 작성시 groupNo가 다음 번호로 가야함(max(no))
insert (... select max(groupNo) from board)+1 from board)
이때 grouNo가 null일 때 0으로 처리해야함

답글:
gno는 유지
ono = ono+1
depth = depth+1
1. update 쿼리 : ono+1 where gno = ?gno and ono >= ?ono
2. insert 쿼리 : 


페이징 
current 페이지, begin 페이지를 action에서 계산해서 넘김
next 페이지 boolean 값으로
*/
